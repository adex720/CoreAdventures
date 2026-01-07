package com.adex.entity.ai;

import com.adex.data.dimension.CustomPortalForcer;
import com.adex.data.dimension.ModDimensions;
import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;

import java.util.Optional;

public class MoveAwayFromCorePortalGoal extends Goal {

    private final PathfinderMob mob;
    private Path path;
    private final double speedModifier;
    private final float distance;

    public MoveAwayFromCorePortalGoal(PathfinderMob mob, double speedModifier, float distance) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.distance = distance;
    }

    @Override
    public boolean canUse() {
        if (mob.level().dimension() != ModDimensions.CORE) return false;

        return findClosestSafeSpot(distance);
    }

    private boolean findClosestSafeSpot(float maxDistance) {
        Level level = mob.level();
        if (level.isClientSide()) return false;

        ServerLevel serverLevel = (ServerLevel) level;
        Optional<BlockPos> result = CustomPortalForcer.CORE_PORTAL_FORCER.findClosestPortalPosition(mob.blockPosition(), (int) maxDistance, serverLevel);
        if (result.isEmpty()) return false;

        // findClosestPortalPosition searches for a square with a side length double of maxDistance.
        // Therefore, a portal found in a corner is far enough.
        if (result.get().distManhattan(mob.blockPosition()) >= distance) return false;

        MoveTowardsCorePortalGoal.IS_GOLEM_PATHFINDING = true;
        path = mob.getNavigation().createPath(Util.getBlocksNAway(result.get(), (int) distance, level, Util::isFullBlockWithAirAbove), (int) (distance * 2));
        MoveTowardsCorePortalGoal.IS_GOLEM_PATHFINDING = false;
        return path != null;
    }

    @Override
    public boolean canContinueToUse() {
        return !mob.getNavigation().isDone() && path != null && path.getEndNode() != null
                && mob.distanceToSqr(path.getEndNode().asBlockPos().getBottomCenter()) <= distance * distance;
    }

    @Override
    public void start() {
        mob.getNavigation().moveTo(path, speedModifier);
    }

}
