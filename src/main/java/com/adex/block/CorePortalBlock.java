package com.adex.block;

import com.adex.CoreAdventures;
import com.adex.dimension.CustomPortalForcer;
import com.adex.dimension.ModDimensions;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class CorePortalBlock extends Block implements Portal {

    public static final MapCodec<CorePortalBlock> CODEC = simpleCodec(CorePortalBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private static final Map<Direction.Axis, VoxelShape> SHAPES = Shapes.rotateHorizontalAxis(Block.column(4.0d, 16.0d, 0.0d, 16.0d));

    @Override
    public MapCodec<CorePortalBlock> codec() {
        return CODEC;
    }

    public CorePortalBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPES.get(blockState.getValue(AXIS));
    }

    @Override
    protected BlockState updateShape(BlockState blockState, LevelReader reader, ScheduledTickAccess tickAccess, BlockPos pos, Direction direction, BlockPos newPos, BlockState newState, RandomSource randomSource) {
        Direction.Axis axis = direction.getAxis();
        Direction.Axis axis2 = blockState.getValue(AXIS);
        boolean bl = axis2 != axis && axis.isHorizontal();
        return !bl && !newState.is(this) && !PortalShape.findAnyShape(reader, pos, axis2).isComplete()
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(blockState, reader, tickAccess, pos, direction, newPos, newState, randomSource);
    }

    @Override
    protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean bl) {
        if (entity.canUsePortal(false)) entity.setAsInsidePortal(this, blockPos);
    }

    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        return entity instanceof Player player ? Math.max(0, level.getGameRules().get(player.getAbilities().invulnerable ? GameRules.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY : GameRules.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY)) : 0;
    }

    @Nullable
    @Override
    public TeleportTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos blockPos) {
        ServerLevel level2 = level.getServer().getLevel(level.dimension() == ModDimensions.CORE ? Level.OVERWORLD : ModDimensions.CORE);
        if (level2 == null) return null;

        boolean goingToCore = level2.dimension() == ModDimensions.CORE;
        WorldBorder worldBorder = level2.getWorldBorder();
        double scale = DimensionType.getTeleportationScale(level.dimensionType(), level2.dimensionType());
        BlockPos newPos = worldBorder.clampToBounds(entity.getX() * scale, entity.getY(), entity.getZ() * scale);
        return this.getExitPortal(level2, entity, blockPos, newPos, goingToCore);
    }

    @Nullable
    private TeleportTransition getExitPortal(ServerLevel level, Entity entity, BlockPos blockPos, BlockPos blockPos2, boolean goingToCore) {
        Optional<BlockPos> existingPortal = CustomPortalForcer.CORE_PORTAL_FORCER.findClosestPortalPosition(blockPos2, goingToCore, level);
        BlockUtil.FoundRectangle portal;
        TeleportTransition.PostTeleportTransition transition;
        if (existingPortal.isPresent()) {
            BlockPos pos = existingPortal.get();
            BlockState state = level.getBlockState(pos);

            portal = BlockUtil.getLargestRectangleAround(pos, state.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, blockPosx -> level.getBlockState(blockPosx) == state);
            transition = TeleportTransition.PLAY_PORTAL_SOUND.then(entityx -> entityx.placePortalTicket(pos));
        } else {
            Direction.Axis axis = entity.level().getBlockState(blockPos).getOptionalValue(AXIS).orElse(Direction.Axis.X);
            Optional<BlockUtil.FoundRectangle> newPortal = CustomPortalForcer.CORE_PORTAL_FORCER.createPortal(blockPos2, axis, level);
            if (newPortal.isEmpty()) {
                CoreAdventures.LOGGER.error("Unable to create a portal, likely target out of worldborder");
                return null;
            }

            portal = newPortal.get();
            transition = TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET);
        }

        return getDimensionTransitionFromExit(entity, blockPos, portal, level, transition);
    }

    private static TeleportTransition getDimensionTransitionFromExit(Entity entity, BlockPos pos, BlockUtil.FoundRectangle portalPos, ServerLevel level, TeleportTransition.PostTeleportTransition transition) {
        BlockState blockState = entity.level().getBlockState(pos);
        Direction.Axis axis;
        Vec3 relativePos;

        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle foundRectangle2 = BlockUtil.getLargestRectangleAround(pos, axis, 21, Direction.Axis.Y, 21, blockPosx -> entity.level().getBlockState(blockPosx) == blockState);
            relativePos = entity.getRelativePortalPosition(axis, foundRectangle2);
        } else {
            axis = Direction.Axis.X;
            relativePos = new Vec3(0.5, 0.0, 0.0);
        }

        return createDimensionTransition(level, portalPos, axis, relativePos, entity, transition);
    }


    private static TeleportTransition createDimensionTransition(ServerLevel level, BlockUtil.FoundRectangle portalPos, Direction.Axis axis, Vec3 relativePos, Entity entity, TeleportTransition.PostTeleportTransition transition) {
        BlockPos pos = portalPos.minCorner;
        Direction.Axis axis2 = level.getBlockState(pos).getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double size1 = portalPos.axis1Size;
        double size2 = portalPos.axis2Size;
        EntityDimensions dimension = entity.getDimensions(entity.getPose());

        int axisId = axis == axis2 ? 0 : 90;
        double axis1Pos = dimension.width() / 2.0d + (size1 - dimension.width()) * relativePos.x();
        double yPos = (size2 - dimension.height()) * relativePos.y();
        double axis2Pos = 0.5d + relativePos.z();
        boolean isHorizontal = axis2 == Direction.Axis.X;

        Vec3 targetPos = new Vec3(pos.getX() + (isHorizontal ? axis1Pos : axis2Pos), pos.getY() + yPos, pos.getZ() + (isHorizontal ? axis2Pos : axis1Pos));
        Vec3 avaiblePos = PortalShape.findCollisionFreePosition(targetPos, level, entity, dimension);
        return new TeleportTransition(level, avaiblePos, Vec3.ZERO, axisId, 0.0f, Relative.union(Relative.DELTA, Relative.ROTATION), transition);
    }

    @Override
    public Portal.Transition getLocalTransition() {
        return Portal.Transition.CONFUSION;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rs) {
        if (rs.nextInt(100) == 0) {
            level.playLocalSound(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5f, rs.nextFloat() * 0.4f + 0.8f, false);
        }

        for (int i = 0; i < 4; i++) {
            double x = pos.getX() + rs.nextDouble();
            double y = pos.getY() + rs.nextDouble();
            double z = pos.getZ() + rs.nextDouble();
            double xOffset = (rs.nextFloat() - 0.5d) * 0.5d;
            double yOffset = (rs.nextFloat() - 0.5d) * 0.5d;
            double zOffset = (rs.nextFloat() - 0.5d) * 0.5d;
            int dist = rs.nextInt(2) * 2 - 1;

            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = pos.getX() + 0.5d + 0.25d * dist;
                xOffset = rs.nextFloat() * 2.0d * dist;
            } else {
                z = pos.getZ() + 0.5d + 0.25d * dist;
                zOffset = rs.nextFloat() * 2.0d * dist;
            }

            level.addParticle(ParticleTypes.PORTAL, x, y, z, xOffset, yOffset, zOffset);
        }
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return ItemStack.EMPTY;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        if (rotation != Rotation.COUNTERCLOCKWISE_90 && rotation != Rotation.CLOCKWISE_90) return state;

        return switch (state.getValue(AXIS)) {
            case X -> state.setValue(AXIS, Direction.Axis.Z);
            case Z -> state.setValue(AXIS, Direction.Axis.X);
            default -> state;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }
}
