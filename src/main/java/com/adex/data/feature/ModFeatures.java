package com.adex.data.feature;

import com.adex.CoreAdventures;
import com.adex.data.tag.ModTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModFeatures {

    public static final ResourceKey<PlacedFeature> CHALCEDONY_ORE = createKey("chalcedony_ore");
    public static final ResourceKey<PlacedFeature> GARNET_ORE = createKey("garnet_ore");
    public static final ResourceKey<PlacedFeature> JADE_ORE = createKey("jade_ore");
    public static final ResourceKey<PlacedFeature> JASPER_ORE = createKey("jasper_ore");
    public static final ResourceKey<PlacedFeature> ONYX_ORE = createKey("onyx_ore");
    public static final ResourceKey<PlacedFeature> OPAL_ORE = createKey("opal_ore");
    public static final ResourceKey<PlacedFeature> RUBY_ORE = createKey("ruby_ore");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE = createKey("sapphire_ore");
    public static final ResourceKey<PlacedFeature> SPINEL_ORE = createKey("spinel_ore");
    public static final ResourceKey<PlacedFeature> TIGERS_EYE_ORE = createKey("tigers_eye_ore");

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, CHALCEDONY_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, GARNET_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, JADE_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, JASPER_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, ONYX_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, OPAL_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, RUBY_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, SAPPHIRE_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, SPINEL_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, TIGERS_EYE_ORE);
    }
}
