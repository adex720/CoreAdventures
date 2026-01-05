package com.adex.advancement.criterion;

import com.adex.CoreAdventures;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModCriterionTriggers {

    public static final LightCorePortalTrigger LIGHT_CORE_PORTAL = register("light_core_portal", new LightCorePortalTrigger());
    public static final CoolWithIceTrigger COOL_WITH_ICE = register("cool_with_ice", new CoolWithIceTrigger());

    public static <T extends CriterionTrigger<?>> T register(String name, T criterionTrigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), criterionTrigger);
    }

    public static void initialize() {
    }
}
