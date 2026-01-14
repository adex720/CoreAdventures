package com.adex.block;

import com.adex.entity.golem.Golem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

public class GolemSpawningBlock extends Block {

    public final Block below;
    public final EntityType<? extends Golem> golemType;

    public GolemSpawningBlock(Properties properties, Block below, EntityType<? extends Golem> golemType) {
        super(properties);
        this.below = below;
        this.golemType = golemType;
    }

    public static Function<Properties, Block> with(Block below, EntityType<? extends Golem> golemType) {
        return (properties) -> new GolemSpawningBlock(properties, below, golemType);
    }

    @Override
    protected void onPlace(BlockState state, @NonNull Level level, @NonNull BlockPos pos, BlockState oldState, boolean update) {
        if (!state.is(oldState.getBlock()) && shouldSpawn(level, pos, state)) {
            spawn((ServerLevel) level, pos);
        }
    }

    public boolean shouldSpawn(Level level, BlockPos pos, BlockState state) {
        if (state.getBlock() != this) return false;
        if (level.getBlockState(pos.below()).getBlock() != below) return false;

        for (BlockPos.MutableBlockPos aroundPos : BlockPos.spiralAround(pos, 1, Direction.NORTH, Direction.EAST)) {
            if (aroundPos.equals(pos)) continue;

            if (!level.getBlockState(aroundPos).getCollisionShape(level, aroundPos).isEmpty() ||
                    !level.getBlockState(aroundPos.below()).getCollisionShape(level, aroundPos).isEmpty())
                return false;
        }

        return true;
    }

    public void spawn(ServerLevel level, BlockPos pos) {
        removeBlocks(level, pos);

        Golem golem = golemType.create(level, EntitySpawnReason.TRIGGERED);
        if (golem == null) return;

        golem.snapTo(pos.getX() + 0.5d, pos.getY() - 1.0d, pos.getZ() + 0.5d, 0.0f, 0.0f);
        level.addFreshEntity(golem);

        for (ServerPlayer serverPlayer : level.getEntitiesOfClass(ServerPlayer.class, golem.getBoundingBox().inflate(5.0))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, golem);
        }
    }

    /**
     * Deletes the 2 blocks used for summoning the golem.
     *
     * @param level Level
     * @param pos   {@link BlockPos} of upper block
     */
    public void removeBlocks(Level level, BlockPos pos) {
        BlockState oldState = level.getBlockState(pos);
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        level.levelEvent(2001, pos, Block.getId(oldState));

        BlockPos belowPos = pos.below();
        BlockState oldStateBelow = level.getBlockState(belowPos);
        level.setBlock(belowPos, Blocks.AIR.defaultBlockState(), 2);
        level.levelEvent(2001, belowPos, Block.getId(oldStateBelow));

        level.updateNeighborsAt(pos, Blocks.AIR);
        level.updateNeighborsAt(belowPos, Blocks.AIR);
    }
}
