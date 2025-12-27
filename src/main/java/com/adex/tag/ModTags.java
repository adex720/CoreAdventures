package com.adex.tag;

import com.adex.CoreAdventures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static final TagKey<Block> CORE_STONE = registerBlock("core_stone");
    public static final TagKey<Block> PORTAL_BLOCKS = registerBlock("portal_blocks");

    public static final TagKey<Biome> CORE_BIOMES = registerBiome("core");

    private static TagKey<Block> registerBlock(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    private static TagKey<Biome> registerBiome(String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
    }
}
