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

    protected final PathfinderMob golem;
    private Path path;
    private final double speedModifier;
    private final float distance;

    public MoveAwayFromCorePortalGoal(PathfinderMob golem, double speedModifier, float distance) {
        this.golem = golem;
        this.speedModifier = speedModifier;
        this.distance = distance;
    }

    @Override
    public boolean canUse() {
        if (golem.level().dimension() != ModDimensions.CORE) return false;

        return findClosestSafeSpot(distance);
    }

    private boolean findClosestSafeSpot(float maxDistance) {
        Level level = golem.level();
        if (level.isClientSide()) return false;

        ServerLevel serverLevel = (ServerLevel) level;
        Optional<BlockPos> result = CustomPortalForcer.CORE_PORTAL_FORCER.findClosestPortalPosition(golem.blockPosition(), (int) maxDistance, serverLevel);
        if (result.isEmpty()) return false;

        // findClosestPortalPosition searches for a square with a side length double of maxDistance.
        // Therefore, a portal found in a corner is far enough.
        if (result.get().distManhattan(golem.blockPosition()) >= distance) return false;

        MoveTowardsCorePortalGoal.IS_GOLEM_PATHFINDING = true;
        path = golem.getNavigation().createPath(Util.getBlocksNAway(result.get(), (int) distance, level, Util::isFullBlockWithAirAbove), (int) (distance * 2));
        MoveTowardsCorePortalGoal.IS_GOLEM_PATHFINDING = false;
        return path != null;
    }

    @Override
    public boolean canContinueToUse() {
        return !golem.getNavigation().isDone() && path != null && path.getEndNode() != null
                && golem.distanceToSqr(path.getEndNode().asBlockPos().getBottomCenter()) <= distance * distance;
    }

    @Override
    public void start() {
        golem.getNavigation().moveTo(path, speedModifier);
    }

    @Override
    public void stop() {
        golem.getNavigation().stop();
        super.stop();
    }

}
