package com.adex.tag;

import com.adex.CoreAdventures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static final TagKey<Block> PORTAL_BLOCKS = registerBlock("portal_blocks");

    private static TagKey<Block> registerBlock(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
    }
}
