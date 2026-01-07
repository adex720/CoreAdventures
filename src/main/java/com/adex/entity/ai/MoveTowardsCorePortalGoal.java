package com.adex.entity.ai;

import com.adex.data.dimension.CustomPortalForcer;
import com.adex.data.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class MoveTowardsCorePortalGoal extends Goal {

    public static boolean IS_GOLEM_PATHFINDING = false;

    private final PathfinderMob mob;
    private double wantedX;
    private double wantedY;
    private double wantedZ;
    private final double speedModifier;
    private final float maxDistance;

    public MoveTowardsCorePortalGoal(PathfinderMob mob, double speedModifier, float maxDistance) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean canUse() {
        if (mob.level().dimension() == ModDimensions.CORE) return false;

        return findClosest(maxDistance);
    }

    private boolean findClosest(float maxDistance) {
        Level level = mob.level();
        if (level.isClientSide()) return false;

        ServerLevel serverLevel = (ServerLevel) level;
        Optional<BlockPos> result = CustomPortalForcer.CORE_PORTAL_FORCER.findClosestPortalPosition(mob.blockPosition(), (int) maxDistance, serverLevel);
        if (result.isEmpty()) return false;

        wantedX = result.get().getX();
        wantedY = result.get().getY();
        wantedZ = result.get().getZ();
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return !mob.getNavigation().isDone() && mob.distanceToSqr(wantedX, wantedY, wantedZ) <= maxDistance * maxDistance;
    }

    @Override
    public void start() {
        IS_GOLEM_PATHFINDING = true;
        mob.getNavigation().moveTo(wantedX, wantedY, wantedZ, speedModifier);
        IS_GOLEM_PATHFINDING = false;
    }

}
