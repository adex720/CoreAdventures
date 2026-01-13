package com.adex.mixin;

import com.adex.entity.attribute.HeatManager;
import com.adex.item.ModDataComponents;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @Shadow
    private int foodLevel;

    /**
     * Prevent natural health generation when overheating.
     */
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getGameRules()Lnet/minecraft/world/level/gamerules/GameRules;"), method = "tick", cancellable = true)
    private void handleTick(ServerPlayer player, CallbackInfo ci) {
        // Cancelling earlier would skip hunger and saturation updates

        // Cancelling when foodLevel is less than 18 would skip starvation damage calculation and
        // not reset tickTimer when it should be reset
        if (foodLevel >= 18 && HeatManager.preventsNaturalRegeneration(player)) {
            ci.cancel();
        }
    }

    /**
     * Check for increased food regeneration
     */
    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/world/food/FoodData;tickTimer:I", opcode = Opcodes.PUTFIELD), method = "tick")
    private void handleTick2(FoodData foodData, int base, @Local(argsOnly = true) ServerPlayer player) {
        if (base == 0) return;

        float total = 0.0f;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.VALUES) {
            Item item = player.getItemBySlot(equipmentSlot).getItem();
            Float resistance = item.components().get(ModDataComponents.POTION_RESISTANCE);
            if (resistance != null) total += resistance;
        }

        if (total > 0.0f) {
            ((FoodDataAccessor) foodData).coread$setTickTimer(base + (int) (total + player.getRandom().nextFloat()));
        }
    }
}
