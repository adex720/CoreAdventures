package com.adex.mixin;

import com.adex.data.dimension.ModDimensions;
import com.adex.entity.attribute.HeatManager;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @Inject(at = @At("HEAD"), method = "onPlace")
    private void onPlaceIce(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl, CallbackInfo ci) {
        if (!blockState.is(BlockTags.ICE) || level.dimension() != ModDimensions.CORE) return;

        HeatManager.applyCooling(level, blockPos, blockState);
    }
}
