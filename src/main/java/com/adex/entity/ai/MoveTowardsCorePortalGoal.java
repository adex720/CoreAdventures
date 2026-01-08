package com.adex.entity.ai;

import com.adex.data.dimension.CustomPortalForcer;
import com.adex.data.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.Optional;

public class MoveTowardsCorePortalGoal extends Goal {

    public static boolean IS_GOLEM_PATHFINDING = false;

    protected final PathfinderMob golem;
    private double wantedX;
    private double wantedY;
    private double wantedZ;
    private final double speedModifier;
    private final float maxDistance;

    public MoveTowardsCorePortalGoal(PathfinderMob golem, double speedModifier, float maxDistance) {
        this.golem = golem;
        this.speedModifier = speedModifier;
        this.maxDistance = maxDistance;

        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (golem.level().dimension() == ModDimensions.CORE) return false;

        return findClosest(maxDistance);
    }

    private boolean findClosest(float maxDistance) {
        Level level = golem.level();
        if (level.isClientSide()) return false;

        ServerLevel serverLevel = (ServerLevel) level;
        Optional<BlockPos> result = CustomPortalForcer.CORE_PORTAL_FORCER.findClosestPortalPosition(golem.blockPosition(), (int) maxDistance, serverLevel);
        if (result.isEmpty()) return false;

        wantedX = result.get().getX();
        wantedY = result.get().getY();
        wantedZ = result.get().getZ();
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return !golem.getNavigation().isDone() && golem.distanceToSqr(wantedX, wantedY, wantedZ) <= maxDistance * maxDistance;
    }

    @Override
    public void start() {
        IS_GOLEM_PATHFINDING = true;
        golem.getNavigation().moveTo(wantedX, wantedY, wantedZ, speedModifier);
        IS_GOLEM_PATHFINDING = false;
    }

    @Override
    public void stop() {
        golem.getNavigation().stop();
        super.stop();
    }

}
