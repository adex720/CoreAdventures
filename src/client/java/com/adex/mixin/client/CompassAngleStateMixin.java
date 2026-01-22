package com.adex.mixin.client;

import com.adex.data.dimension.ModDimensions;
import com.adex.item.ModItems;
import com.adex.item.RefugeCompassHelper;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.CompassAngleState;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CompassAngleState.class)
public class CompassAngleStateMixin {

    /**
     * Finds GlobalPos for refuge compass target.
     * Value of old is always null because refuge compass has {@link CompassAngleState.CompassTarget#NONE} as target.
     * Returning a value of null makes the compass spin randomly.
     */
    @ModifyVariable(at = @At("STORE"), method = "calculate")
    private GlobalPos getRefuge(GlobalPos old, @Local(argsOnly = true) ItemStack itemStack, @Local(argsOnly = true) ClientLevel level, @Local(argsOnly = true) ItemOwner owner) {
        if (level.dimension() != ModDimensions.CORE) return old;
        if (!itemStack.is(ModItems.REFUGE_COMPASS)) return old;
        if (!(owner instanceof Player)) return old;

        GlobalPos found = RefugeCompassHelper.locate(level, (Player) owner);
        return found != null ? found : old;
    }
}
