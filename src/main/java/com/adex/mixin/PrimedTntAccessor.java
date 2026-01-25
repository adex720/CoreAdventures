package com.adex.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PrimedTnt.class)
public interface PrimedTntAccessor {

    @Accessor("DATA_FUSE_ID")
    static EntityDataAccessor<Integer> coread$getDataFuseID() {
        throw new AssertionError();
    }

    @Accessor("DATA_BLOCK_STATE_ID")
    static EntityDataAccessor<BlockState> coread$getBlockStateID() {
        throw new AssertionError();
    }

    @Accessor("explosionPower")
    void coread$setExplosionPower(float explosionPower);

    @Accessor("owner")
    void coread$setOwner(EntityReference<LivingEntity> entity);

}
