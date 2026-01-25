package com.adex.entity;

import com.adex.block.ModBlocks;
import com.adex.block.StrongTntBlock;
import com.adex.mixin.PrimedTntAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class PrimedStrongTnt extends PrimedTnt {

    public static final int DEFAULT_FUSE_TIME = 80;
    private final StrongTntBlock block;

    public PrimedStrongTnt(EntityType<PrimedStrongTnt> type, Level level, StrongTntBlock tnt) {
        // block needs to be set before calling super,
        // because the value is needed in defineSynchedData(),
        // which is called from the super
        this.block = tnt;
        super(type, level);
    }

    private PrimedStrongTnt(Level level, double x, double y, double z, int fuseTimer, StrongTntBlock tnt) {
        // block needs to be set before calling super,
        // because the value is needed in defineSynchedData(),
        // which is called from the super
        this.block = tnt;
        super(getType(tnt), level);

        setPos(x, y, z);
        double randomDegrees = level.random.nextDouble() * (Math.PI * 2);
        setDeltaMovement(-Math.sin(randomDegrees) * 0.02d, 0.2f, -Math.cos(randomDegrees) * 0.02d);
        setFuse(fuseTimer);
        xo = x;
        yo = y;
        zo = z;
    }

    public static EntityType.EntityFactory<PrimedStrongTnt> of(StrongTntBlock tnt) {
        return ((type, level) -> new PrimedStrongTnt(type, level, tnt));
    }

    public static EntityType<PrimedStrongTnt> getType(StrongTntBlock tnt) {
        if (tnt == ModBlocks.RED_TNT) return ModEntities.PRIMED_RED_TNT;
        if (tnt == ModBlocks.ORANGE_TNT) return ModEntities.PRIMED_ORANGE_TNT;
        if (tnt == ModBlocks.YELLOW_TNT) return ModEntities.PRIMED_YELLOW_TNT;
        if (tnt == ModBlocks.GREEN_TNT) return ModEntities.PRIMED_GREEN_TNT;
        if (tnt == ModBlocks.BLUE_TNT) return ModEntities.PRIMED_BLUE_TNT;

        throw new IllegalArgumentException("Expected primed strong tnt block, got: " + tnt);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(PrimedTntAccessor.coread$getDataFuseID(), 80);
        builder.define(PrimedTntAccessor.coread$getBlockStateID(), block.defaultBlockState());
    }

    public static PrimedStrongTnt create(StrongTntBlock tnt, Level level, BlockPos pos, float explosionPower, @Nullable LivingEntity owner) {
        return create(tnt, level, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, explosionPower, DEFAULT_FUSE_TIME, owner);
    }

    public static PrimedStrongTnt create(StrongTntBlock tnt, Level level, BlockPos pos, float explosionPower, int fuseTimer, @Nullable LivingEntity owner) {
        return create(tnt, level, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, explosionPower, fuseTimer, owner);
    }

    public static PrimedStrongTnt create(StrongTntBlock tntBlock, Level level, double x, double y, double z, float explosionPower, int fuseTimer, @Nullable LivingEntity owner) {
        PrimedStrongTnt tnt = new PrimedStrongTnt(level, x, y, z, fuseTimer, tntBlock);

        PrimedTntAccessor accessor = ((PrimedTntAccessor) tnt);
        accessor.coread$setExplosionPower(explosionPower);
        accessor.coread$setOwner(EntityReference.of(owner));

        return tnt;
    }
}
