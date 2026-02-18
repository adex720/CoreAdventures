package com.adex.mixin;

import com.adex.entity.EntityUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin extends AbstractVillager implements Consumable.OverrideConsumeSound {

    private WanderingTraderMixin(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("RETURN"), method = "registerGoals")
    private void addCustomGoals(CallbackInfo ci) {
        EntityUtils.addWanderingTraderGoals((WanderingTrader) (Object) this, goalSelector);
    }

}
