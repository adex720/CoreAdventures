package com.adex.data.dimension;

import com.adex.block.ModBlocks;
import com.adex.entity.poi.ModPoiTypes;
import net.minecraft.core.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Comparator;
import java.util.Optional;

public class CustomPortalForcer {

    public static final CustomPortalForcer CORE_PORTAL_FORCER = new CustomPortalForcer(ModBlocks.REINFORCED_ANCIENT_DEBRIS, ModBlocks.CORE_PORTAL_BLOCK);

    public final Block frameBlock;
    public final Block portalBlock;

    public CustomPortalForcer(Block frameBlock, Block portalBlock) {
        this.frameBlock = frameBlock;
        this.portalBlock = portalBlock;
    }

    public Optional<BlockPos> findClosestPortalPosition(BlockPos pos, boolean goingToCore, ServerLevel level) {
        return findClosestPortalPosition(pos, goingToCore ? 16 : 256, level);
    }

    public Optional<BlockPos> findClosestPortalPosition(BlockPos pos, int maxDistance, ServerLevel level) {
        PoiManager poiManager = level.getPoiManager();
        poiManager.ensureLoadedAndValid(level, pos, maxDistance);

        return poiManager.getInSquare(holder -> holder.is(ModPoiTypes.CORE_PORTAL), pos, maxDistance, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(blockPos -> level.getWorldBorder().isWithinBounds(blockPos))
                .filter(blockPos -> level.getBlockState(blockPos).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .min(Comparator.comparingDouble(blockPos2 -> ((BlockPos) blockPos2).distSqr(pos)).thenComparingInt(blockPos -> ((BlockPos) blockPos).getY()));
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos center, Direction.Axis axis, ServerLevel level) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        WorldBorder border = level.getWorldBorder();

        double distance1 = -1.0d;
        double distance2 = -1.0d;
        int maxY = Math.min(level.getMaxY(), level.getMinY() + level.getLogicalHeight() - 1);

        BlockPos portalPos = null; // Best with frame
        BlockPos portalPos2 = null; // Best without frame
        BlockPos.MutableBlockPos pos = center.mutable();

        // Find valid position for portal
        for (BlockPos.MutableBlockPos searchPos : BlockPos.spiralAround(center, 16, Direction.EAST, Direction.SOUTH)) {
            int minY = Math.min(maxY, level.getHeight(Heightmap.Types.MOTION_BLOCKING, searchPos.getX(), searchPos.getZ()));

            if (!border.isWithinBounds(searchPos) || !border.isWithinBounds(searchPos.move(direction, 1))) continue;

            searchPos.move(direction.getOpposite(), 1);
            for (int y = minY; y >= level.getMinY(); y--) {
                searchPos.setY(y);

                if (!canPortalReplaceBlock(searchPos, level)) continue;

                int bottomY = y;
                while (y > level.getMinY() && canPortalReplaceBlock(searchPos.move(Direction.DOWN), level)) {
                    y--;
                }

                if (y + 4 > maxY) continue;
                int maxHeight = bottomY - y;
                if (maxHeight > 0 && maxHeight < 3) continue;

                searchPos.setY(y);
                if (!canHostFrame(searchPos, pos, direction, 0, level)) continue;

                double distanceToWanted = center.distSqr(searchPos);
                if (canHostFrame(searchPos, pos, direction, -1, level)
                        && canHostFrame(searchPos, pos, direction, 1, level)
                        && (distance1 == -1.0d || distance1 > distanceToWanted)) {

                    distance1 = distanceToWanted;
                    portalPos = searchPos.immutable();
                }

                if (distance1 == -1.0d && (distance2 == -1.0d || distance2 > distanceToWanted)) {
                    distance2 = distanceToWanted;
                    portalPos2 = searchPos.immutable();
                }
            }
        }

        // Prioritize locations without base
        if (distance1 == -1.0d && distance2 != -1.0d) {
            portalPos = portalPos2;
            distance1 = distance2;
        }

        // Create base and air around
        if (distance1 == -1.0d) {
            int minY = Math.max(level.getMinY() + 1, 70);
            maxY -= 9;
            if (maxY < minY) {
                return Optional.empty();
            }

            portalPos = new BlockPos(center.getX() - direction.getStepX(), Mth.clamp(center.getY(), minY, maxY), center.getZ() - direction.getStepZ()).immutable();
            portalPos = border.clampToBounds(portalPos);
            Direction opposite = direction.getClockWise();

            // Create standing platform and air around portal
            for (int dx = -1; dx < 2; dx++) {
                for (int dz = 0; dz < 2; dz++) {
                    for (int dy = -1; dy < 3; dy++) {
                        BlockState newState = dy < 0 ? frameBlock.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        pos.setWithOffset(portalPos, dz * direction.getStepX() + dx * opposite.getStepX(), dy, dz * direction.getStepZ() + dx * opposite.getStepZ());
                        level.setBlockAndUpdate(pos, newState);
                    }
                }
            }
        }

        // Create frame
        for (int dx = -1; dx < 3; dx++) {
            for (int dy = -1; dy < 4; dy++) {
                if (dx == -1 || dx == 2 || dy == -1 || dy == 3) {
                    pos.setWithOffset(portalPos, dx * direction.getStepX(), dy, dx * direction.getStepZ());
                    level.setBlock(pos, frameBlock.defaultBlockState(), 3);
                }
            }
        }

        // Create portal blocks
        BlockState portalState = portalBlock.defaultBlockState().setValue(NetherPortalBlock.AXIS, axis);
        for (int dx = 0; dx < 2; dx++) {
            for (int dy = 0; dy < 3; dy++) {
                pos.setWithOffset(portalPos, dx * direction.getStepX(), dy, dx * direction.getStepZ());
                level.setBlock(pos, portalState, 18);
            }
        }

        // Add poi for portal
        level.getPoiManager().add(portalPos, PoiTypes.forState(portalState).orElseThrow());
        return Optional.of(new BlockUtil.FoundRectangle(portalPos.immutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos, ServerLevel level) {
        BlockState blockState = level.getBlockState(pos);
        return blockState.canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos pos2, BlockPos.MutableBlockPos pos, Direction direction, int i, ServerLevel level) {
        Direction direction2 = direction.getClockWise();

        for (int xOffset = -1; xOffset < 3; xOffset++) {
            for (int yOffset = -1; yOffset < 4; yOffset++) {
                pos.setWithOffset(pos2, direction.getStepX() * xOffset + direction2.getStepX() * i, yOffset, direction.getStepZ() * xOffset + direction2.getStepZ() * i);

                if (yOffset < 0 && !level.getBlockState(pos).isCollisionShapeFullBlock(level, pos)) return false;
                if (yOffset >= 0 && !this.canPortalReplaceBlock(pos, level)) return false;
            }
        }

        return true;
    }
}
