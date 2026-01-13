package com.adex.mixin;

import com.adex.item.armor.ProtectiveArmor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(at = @At("RETURN"), method = "getDamageProtection", cancellable = true)
    private static void getDamageProtection(ServerLevel level, LivingEntity entity, DamageSource damageSource, CallbackInfoReturnable<Float> cir) {
        float added = 0.0f;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.VALUES) {
            Item item = entity.getItemBySlot(equipmentSlot).getItem();
            if (item instanceof ProtectiveArmor protectiveArmor) {
                added += protectiveArmor.getExtraProtection(damageSource);
            }
        }

        if (added > 0.0f) {
            cir.setReturnValue(cir.getReturnValueF() + added);
        }
    }
}
