package com.adex.entity.attribute;

import com.adex.CoreAdventures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ModAttributes {

    public static final Holder<Attribute> HEAT = register("heat", 0.0d, 0.0d, 200.0d, true);
    public static final Holder<Attribute> HEAT_PROTECTION = register("heat_protection", 0.0d, 0.0d, 1024.0d, true);

    private static Holder<Attribute> register(String name, double defaultValue, double minValue, double maxValue, boolean syncedWithClient) {
        Identifier identifier = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name);
        Attribute entityAttribute = new RangedAttribute(identifier.toLanguageKey(), defaultValue, minValue, maxValue)
                .setSyncable(syncedWithClient);

        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, identifier, entityAttribute);
    }

    public static void initialize() {
    }

}
