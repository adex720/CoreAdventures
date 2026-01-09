package com.adex.entity.ai;

import com.adex.data.tag.ModTags;
import com.adex.entity.golem.Golem;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DestroyNeighbourBlocksGoal extends Goal {

    protected final Golem golem;
    private final int tickCount;
    private final int destroyCount;
    private final float maxDifferenceSqr;
    private final float maxDistanceSqr;
    private Vec3 pos;
    private int ticksInSamePlace;

    public DestroyNeighbourBlocksGoal(Golem golem, int tickCount, int destroyCount, float maxDifference, float maxDistance) {
        this.golem = golem;
        this.tickCount = tickCount;
        this.destroyCount = destroyCount;
        this.maxDistanceSqr = maxDistance * maxDistance;
        this.maxDifferenceSqr = maxDifference * maxDifference;

        pos = golem.position();
        ticksInSamePlace = 0;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = golem.getTarget();
        if (target == null) {
            reset();
            return false;
        }

        if (golem.ticksSinceLastAttack() < ticksInSamePlace) return false;
        double d = golem.distanceToSqr(target);
        if (d > maxDistanceSqr) {
            reset();
            return false;
        }

        if (pos == null || golem.distanceToSqr(pos) >= maxDifferenceSqr) {
            reset();
            return false;
        }

        ticksInSamePlace++;
        return ticksInSamePlace >= this.tickCount;
    }

    public void reset() {
        pos = golem.position();
        ticksInSamePlace = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    public void destroy() {
        Level level = golem.level();
        int x1 = (int) (golem.position().x - 0.5f);
        int y1 = (int) golem.position().y;
        int z1 = (int) (golem.position().z - 0.5f);
        int x2 = x1 + 1;
        int y2 = y1 + 1;
        int z2 = z1 + 1;

        Set<BlockPos> closeBlocks = new HashSet<>();
        closeBlocks.add(new BlockPos(x1, y1, z1));
        closeBlocks.add(new BlockPos(x1, y1, z2));
        closeBlocks.add(new BlockPos(x1, y2, z1));
        closeBlocks.add(new BlockPos(x1, y2, z2));
        closeBlocks.add(new BlockPos(x2, y1, z1));
        closeBlocks.add(new BlockPos(x2, y1, z2));
        closeBlocks.add(new BlockPos(x2, y2, z1));
        closeBlocks.add(new BlockPos(x2, y2, z2));

        Set<BlockPos> validBlocks = new HashSet<>();
        for (BlockPos p : closeBlocks) {
            if (canDestroy(level.getBlockState(p), p)) validBlocks.add(p);
        }

        int left = destroy(validBlocks, destroyCount);
        if (left <= 0) return;

        closeBlocks.clear();
        validBlocks.clear();
        closeBlocks.add(new BlockPos(x1 - 1, y1, z1));
        closeBlocks.add(new BlockPos(x1 - 1, y1, z2));
        closeBlocks.add(new BlockPos(x1 - 1, y2, z1));
        closeBlocks.add(new BlockPos(x1 - 1, y2, z2));

        closeBlocks.add(new BlockPos(x2 + 1, y1, z1));
        closeBlocks.add(new BlockPos(x2 + 1, y1, z2));
        closeBlocks.add(new BlockPos(x2 + 1, y2, z1));
        closeBlocks.add(new BlockPos(x2 + 1, y2, z2));

        closeBlocks.add(new BlockPos(x1, y1 - 1, z1));
        closeBlocks.add(new BlockPos(x1, y1 - 1, z2));
        closeBlocks.add(new BlockPos(x2, y1 - 1, z1));
        closeBlocks.add(new BlockPos(x2, y1 - 1, z2));

        closeBlocks.add(new BlockPos(x1, y2 + 1, z1));
        closeBlocks.add(new BlockPos(x1, y2 + 1, z2));
        closeBlocks.add(new BlockPos(x2, y2 + 1, z1));
        closeBlocks.add(new BlockPos(x2, y2 + 1, z2));

        closeBlocks.add(new BlockPos(x1, y1, z1 - 1));
        closeBlocks.add(new BlockPos(x1, y2, z1 - 1));
        closeBlocks.add(new BlockPos(x2, y1, z1 - 1));
        closeBlocks.add(new BlockPos(x2, y2, z1 - 1));

        closeBlocks.add(new BlockPos(x1, y1, z2 + 1));
        closeBlocks.add(new BlockPos(x1, y2, z2 + 1));
        closeBlocks.add(new BlockPos(x2, y1, z2 + 1));
        closeBlocks.add(new BlockPos(x2, y2, z2 + 1));

        for (BlockPos p : closeBlocks) {
            if (canDestroy(level.getBlockState(p), p)) validBlocks.add(p);
        }

        if (destroy(validBlocks, left) != destroyCount) golem.attacked(); // false only if no blocks have been destroyed
        // closeBlocks.forEach(b -> level.setBlock(b, Blocks.RED_WOOL.defaultBlockState(), 18)); // for debug
    }

    /**
     * Returns how many blocks still need to be broken for count to be reached.
     */
    public int destroy(Set<BlockPos> positions, int count) {
        int left = count - positions.size();
        if (left >= 0) {
            positions.forEach(p -> golem.level().destroyBlock(p, true, golem));
            return left;
        }

        Level level = golem.level();
        ArrayList<BlockPos> posList = new ArrayList<>(positions);
        ArrayList<Float> weights = new ArrayList<>(posList.size());
        for (BlockPos pos : posList) {
            weights.add(1 / level.getBlockState(pos).getBlock().getExplosionResistance());
        }

        for (int i = 0; i < count; i++) {
            int index = Util.getRandomWeightedIndex(weights, level.getRandom());
            BlockPos pos = posList.remove(index);
            weights.remove(index);

            level.destroyBlock(pos, true, golem);
            //TODO: add explosion particles
        }

        return 0;
    }

    public boolean canDestroy(BlockState state, BlockPos pos) {
        if (state.getFluidState() != Fluids.EMPTY.defaultFluidState()) return false;
        if (state.getCollisionShape(golem.level(), pos).isEmpty()) return false;
        if (state.getBlock().defaultDestroyTime() < 0.0f) return false;
        if (state.getBlock().getExplosionResistance() < 0.0f) return false;

        return !state.is(ModTags.GOLEM_UNBREAKABLE_BLOCKS);
    }

    @Override
    public void start() {
        destroy();
        reset();
    }
}
