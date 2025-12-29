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

    public static final ResourceKey<PlacedFeature> RUBY_ORE = createKey("ruby_ore");

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.CORE_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, RUBY_ORE);
    }

    /*public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext) {
        RuleTest ruleTest = new TagMatchTest(ModTags.CORE_STONE);

        register(bootstrapContext, ruleTest, RUBY_ORE, ModBlocks.RUBY_ORE, 8, 0.0f);
    }

    private static void register(BootstrapContext<ConfiguredFeature<?, ?>> context, RuleTest ruleTest, ResourceKey<ConfiguredFeature<?, ?>> key, Block block, int size, float airExposure) {
        List<OreConfiguration.TargetBlockState> list = List.of(OreConfiguration.target(ruleTest, block.defaultBlockState()));
        FeatureUtils.register(context, key, Feature.ORE, new OreConfiguration(list, size, airExposure));
    }*/

}
