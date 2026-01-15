package com.adex.mixin;

import com.adex.block.ModBlocks;
import com.adex.data.dimension.ModDimensions;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {

    @Inject(at = @At("HEAD"), method = "inPortalDimension", cancellable = true)
    private static void inCoreDimension(Level level, CallbackInfoReturnable<Boolean> cir) {
        if (level.dimension() == ModDimensions.CORE) cir.setReturnValue(true);
    }

    @ModifyVariable(at = @At("STORE"), method = "isPortal", ordinal = 0)
    private static boolean checkForReinforcedAncientDebris(boolean value, @Local(argsOnly = true) Level level, @Local(argsOnly = true) BlockPos blockPos) {
        for (Direction direction : Direction.values()) {
            if (level.getBlockState(blockPos.relative(direction)).getBlock() == ModBlocks.REINFORCED_ANCIENT_DEBRIS) {
                return true;
            }
        }

        return value;
    }
}
