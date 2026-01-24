package com.adex.entity;

import com.adex.mixin.PrimedTntAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class PrimedStrongTnt extends PrimedTnt {

    public static final int DEFAULT_FUSE_TIME = 80;

    public PrimedStrongTnt(EntityType<PrimedStrongTnt> type, Level level) {
        super(type, level);
    }

    private PrimedStrongTnt(EntityType<PrimedStrongTnt> type, Level level, double x, double y, double z, int fuseTimer) {
        super(type, level);
        setPos(x, y, z);
        double randomDegrees = level.random.nextDouble() * (Math.PI * 2);
        setDeltaMovement(-Math.sin(randomDegrees) * 0.02d, 0.2f, -Math.cos(randomDegrees) * 0.02d);
        setFuse(fuseTimer);
        xo = x;
        yo = y;
        zo = z;
    }

    public static PrimedStrongTnt create(Level level, BlockPos pos, float explosionPower, @Nullable LivingEntity owner) {
        return create(level, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, explosionPower, DEFAULT_FUSE_TIME, owner);
    }

    public static PrimedStrongTnt create(Level level, BlockPos pos, float explosionPower, int fuseTimer, @Nullable LivingEntity owner) {
        return create(level, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, explosionPower, fuseTimer, owner);
    }

    public static PrimedStrongTnt create(Level level, double x, double y, double z, float explosionPower, @Nullable LivingEntity owner) {
        return create(level, x, y, z, explosionPower, DEFAULT_FUSE_TIME, owner);
    }

    public static PrimedStrongTnt create(Level level, double x, double y, double z, float explosionPower, int fuseTimer, @Nullable LivingEntity owner) {
        PrimedStrongTnt tnt = new PrimedStrongTnt(ModEntities.PRIMED_STRONG_TNT, level, x, y, z, fuseTimer);

        PrimedTntAccessor accessor = ((PrimedTntAccessor) tnt);
        accessor.coread$setExplosionPower(explosionPower);
        accessor.coread$setOwner(EntityReference.of(owner));

        return tnt;
    }
}
