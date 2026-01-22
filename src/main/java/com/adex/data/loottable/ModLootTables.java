package com.adex.data.loottable;

import com.adex.CoreAdventures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTables {

    public static final ResourceKey<LootTable> REFUGE_BASIC = register("chests/refuge_basic");
    public static final ResourceKey<LootTable> REFUGE_TREASURE = register("chests/refuge_treasure");
    public static final ResourceKey<LootTable> REFUGE_PORTAL_ROOM = register("chests/refuge_portal");

    private static ResourceKey<LootTable> register(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
    }

}
