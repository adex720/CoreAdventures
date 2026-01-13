package com.adex.data.tag;

import com.adex.CoreAdventures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class ModTags {

    public static final TagKey<Block> CORE_STONE = registerBlock("core_stone");
    public static final TagKey<Block> PORTAL_BLOCKS = registerBlock("portal_blocks");
    public static final TagKey<Block> CHAINABLE_BLOCKS = registerBlock("chainable_blocks");
    public static final TagKey<Block> GOLEM_UNBREAKABLE_BLOCKS = registerBlock("golem_unbreakable_blocks");

    public static final TagKey<Item> REPAIRS_CHALCEDONY_ARMOR = registerItem("repairs_chalcedony_armor");
    public static final TagKey<Item> REPAIRS_GARNET_ARMOR = registerItem("repairs_garnet_armor");
    public static final TagKey<Item> REPAIRS_JADE_ARMOR = registerItem("repairs_jade_armor");
    public static final TagKey<Item> REPAIRS_JASPER_ARMOR = registerItem("repairs_jasper_armor");
    public static final TagKey<Item> REPAIRS_ONYX_ARMOR = registerItem("repairs_onyx_armor");
    public static final TagKey<Item> REPAIRS_OPAL_ARMOR = registerItem("repairs_opal_armor");
    public static final TagKey<Item> REPAIRS_RUBY_ARMOR = registerItem("repairs_ruby_armor");
    public static final TagKey<Item> REPAIRS_SAPPHIRE_ARMOR = registerItem("repairs_sapphire_armor");
    public static final TagKey<Item> REPAIRS_SPINEL_ARMOR = registerItem("repairs_spinel_armor");
    public static final TagKey<Item> REPAIRS_TIGERS_EYE_ARMOR = registerItem("repairs_tigers_eye_armor");
    public static final TagKey<Item> REPAIRS_GEM_ARMOR = registerItem("repairs_gem_armor");

    public static final TagKey<Item> CORE_ARMOR = registerItem("core_armor");

    public static final TagKey<Biome> CORE_BIOMES = registerBiome("core");

    public static final TagKey<DamageType> CHALCEDONY_PROTECTIVE_ARMOR = registerDamageType("chalcedony_protective_armor");
    public static final TagKey<DamageType> JASPER_PROTECTIVE_ARMOR = registerDamageType("jasper_protective_armor");
    public static final TagKey<DamageType> SPINEL_PROTECTIVE_ARMOR = registerDamageType("spinel_protective_armor");
    public static final TagKey<DamageType> TIGERS_EYE_PROTECTIVE_ARMOR = registerDamageType("tigers_eye_protective_armor");
    public static final TagKey<DamageType> GEM_PROTECTIVE_ARMOR = registerDamageType("gem_protective_armor");

    private static TagKey<Block> registerBlock(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    private static TagKey<Item> registerItem(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    @SuppressWarnings("SameParameterValue")
    private static TagKey<Biome> registerBiome(String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    private static TagKey<DamageType> registerDamageType(String name) {
        return TagKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
    }
}
