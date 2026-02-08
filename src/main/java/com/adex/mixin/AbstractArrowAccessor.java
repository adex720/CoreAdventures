package com.adex.mixin;

import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractArrow.class)
public interface AbstractArrowAccessor {

    @Accessor("firedFromWeapon")
    void coread$setFiredFromWeapon(ItemStack firedFromWeapon);

    @Invoker("setPierceLevel")
    void coread$setPierceLevel(byte level);
}
