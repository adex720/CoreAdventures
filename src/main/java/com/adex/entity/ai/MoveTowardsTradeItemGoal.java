package com.adex.entity.ai;

import com.adex.data.tag.ModTags;
import com.adex.entity.sentry.Sentry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Optional;

public class MoveTowardsTradeItemGoal extends Goal {

    private final Sentry sentry;
    private final float searchDistance;
    private final float minDistance;

    private double wantedX;
    private double wantedY;
    private double wantedZ;

    public MoveTowardsTradeItemGoal(Sentry sentry, float searchDistance, float minDistance) {
        this.sentry = sentry;
        this.searchDistance = searchDistance;
        this.minDistance = minDistance;

        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public MoveTowardsTradeItemGoal(Sentry sentry, float searchDistance) {
        this(sentry, searchDistance, 1.0f);
    }

    @Override
    public boolean canUse() {
        return search();
    }

    @Override
    public boolean canContinueToUse() {
        return !sentry.getNavigation().isDone() && sentry.distanceToSqr(wantedX, wantedY, wantedZ) <= searchDistance * searchDistance;
    }

    private boolean search() {
        Level level = sentry.level();
        if (level.isClientSide()) return false;

        Optional<Vec3> result = findClosestItemEntity((ServerLevel) level);
        if (result.isEmpty()) return false;

        if (sentry.distanceToSqr(result.get()) < minDistance * minDistance) return false;

        Vec3 wantedPos = result.get();

        wantedX = wantedPos.x;
        wantedY = wantedPos.y;
        wantedZ = wantedPos.z;
        return true;
    }

    private Optional<Vec3> findClosestItemEntity(ServerLevel level) {
        Optional<Vec3> closest = Optional.empty();
        float closestDistanceSqr = Float.MAX_VALUE;

        for (ItemEntity itemEntity : level.getEntitiesOfClass(ItemEntity.class, sentry.getBoundingBox().inflate(searchDistance))) {
            if (itemEntity.isRemoved() || itemEntity.getItem().isEmpty() || !itemEntity.getItem().is(ModTags.TRADER_SENTRY_LIKES)) continue;

            float distanceSqr = (float) sentry.distanceToSqr(itemEntity);
            if (distanceSqr < closestDistanceSqr) {
                closestDistanceSqr = distanceSqr;
                closest = Optional.of(itemEntity.position());
            }
        }

        return closest;
    }

    @Override
    public void start() {
        sentry.getNavigation().moveTo(wantedX, wantedY, wantedZ, 0, 1.0d);
    }

    @Override
    public void stop() {
        sentry.getNavigation().stop();
    }
}
