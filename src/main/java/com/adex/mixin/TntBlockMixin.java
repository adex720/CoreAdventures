package com.adex.mixin;

import com.adex.block.StrongTntBlock;
import com.adex.entity.PrimedStrongTnt;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TntBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin extends Block {

    public TntBlockMixin(Properties properties) {
        super(properties);
    }

    @ModifyVariable(at = @At("STORE"), method = "prime(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/LivingEntity;)Z")
    private static PrimedTnt modifyExplosionPower(PrimedTnt tnt, @Local(argsOnly = true) Level level, @Local(argsOnly = true) BlockPos pos, @Local(argsOnly = true) LivingEntity primer) {
        Block block = level.getBlockState(pos).getBlock();
        if (block instanceof StrongTntBlock strongTnt) {
            return PrimedStrongTnt.create(strongTnt, level, pos, strongTnt.getExplosionPower(), primer);
        }

        // If fused tnt is vanilla tnt, return unmodified value
        return tnt;
    }

}
