package com.adex.mixin;

import com.adex.item.ModDataComponents;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Arrow.class)
public class ArrowMixin {

    @ModifyVariable(at = @At("STORE"), method = "doPostHurtEffects")
    private float checkForPotionResistance(float value, @Local(argsOnly = true) LivingEntity target) {
        float total = 0.0f;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.VALUES) {
            Item item = target.getItemBySlot(equipmentSlot).getItem();
            Float resistance = item.components().get(ModDataComponents.POTION_RESISTANCE);
            if (resistance != null) total += resistance;
        }

        return value / (1 + total);
    }
}
