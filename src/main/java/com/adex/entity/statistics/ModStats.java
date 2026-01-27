package com.adex.entity.statistics;

import com.adex.CoreAdventures;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ModStats {

    public static final Identifier INTERACT_WITH_HEAT_STABILIZER = register("interact_with_heat_stabilizer");
    public static final Identifier SUMMON_GOLEM = register("summon_golem");

    private static Identifier register(String name) {
        Identifier identifier = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name);
        return Registry.register(BuiltInRegistries.CUSTOM_STAT, identifier, identifier);
    }

    public static void initialize() {
        Stats.CUSTOM.get(INTERACT_WITH_HEAT_STABILIZER, StatFormatter.DEFAULT);
        Stats.CUSTOM.get(SUMMON_GOLEM, StatFormatter.DEFAULT);
    }
}
