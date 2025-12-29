package com.adex.mixin;

import com.adex.entity.attribute.HeatManager;
import com.adex.item.ModDataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Properties.class)
public class ItemPropertiesMixin {

    @Inject(at = @At("RETURN"), method = "humanoidArmor", cancellable = true)
    private void addHeatResistanceToHumanoidArmor(ArmorMaterial material, ArmorType type, CallbackInfoReturnable<Item.Properties> cir) {
        cir.setReturnValue(cir.getReturnValue().component(ModDataComponents.HEAT_RESISTANCE, HeatManager.getHeatResistance(material)));
    }
}
