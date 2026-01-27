package com.adex.effect;

import com.adex.CoreAdventures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {

    public static final Holder<MobEffect> HEAT_IMMUNITY = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "heat_immunity"), new HeatImmunity());

    public static void initialize() {
    }
}
