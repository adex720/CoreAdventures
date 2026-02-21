package com.adex;

import com.adex.advancement.criterion.ModCriterionTriggers;
import com.adex.block.ModBlockSetTypes;
import com.adex.block.ModBlocks;
import com.adex.block.ModWoodTypes;
import com.adex.block.entity.ModBlockEntities;
import com.adex.data.damagetype.ModDamageTypes;
import com.adex.data.dimension.ModDimensions;
import com.adex.data.feature.ModTreeGrowers;
import com.adex.data.loottable.ModLootTables;
import com.adex.data.recipe.ModRecipeSerializers;
import com.adex.data.structure.ModStructures;
import com.adex.data.structure.refuge.RefugePieces;
import com.adex.effect.ModEffects;
import com.adex.enchantment.effect.ModEnchantmentEffectComponents;
import com.adex.enchantment.effect.ModEnchantmentEffects;
import com.adex.entity.ModEntities;
import com.adex.entity.attribute.ModAttributes;
import com.adex.entity.poi.ModPoiTypes;
import com.adex.item.armor.ModMaterialAssetGroups;
import com.adex.item.armor.ModTrimMaterials;
import com.adex.item.tool.ModToolMaterials;
import com.adex.sound.ModJukeboxSounds;
import com.adex.sound.ModSoundEvents;
import com.adex.statistics.ModStats;
import com.adex.event.ModEvents;
import com.adex.data.feature.ModFeatures;
import com.adex.item.ModDataComponents;
import com.adex.item.ModItems;
import com.adex.data.tag.ModTags;
import com.adex.item.armor.ModArmorMaterials;
import com.adex.payload.ModPayloads;
import com.adex.payload.ModServerReceivers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreAdventures implements ModInitializer {

    public static final String MOD_ID = "coread";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final boolean ENABLE_DEV_COMMANDS = true;

    // Custom armor trim materials may cause compatibility issues with other mods adding armor trim materials
    // with the same names. In that case set this to false and rerun Data Generation.
    public static final boolean GENERATE_ARMOR_TRIMS = true;

    @Override
    public void onInitialize() {
        LOGGER.info("Starting initializing {}", MOD_ID);

        ModTags.initialize();

        ModArmorMaterials.initialize();
        ModToolMaterials.initialize();
        ModDataComponents.initialize();

        if (GENERATE_ARMOR_TRIMS) {
            ModMaterialAssetGroups.initialize();
            ModTrimMaterials.initialize();
        }

        ModSoundEvents.initialize();
        ModJukeboxSounds.initialize();

        ModBlockSetTypes.initialize();
        ModWoodTypes.initialize();

        ModFeatures.initialize();
        ModTreeGrowers.initialize();

        ModItems.initialize();
        ModBlocks.initialize();
        ModBlockEntities.initialize();

        ModAttributes.initialize();
        ModPoiTypes.initialize();
        ModEffects.initialize();
        ModStats.initialize();

        ModEntities.initialize();

        ModEnchantmentEffectComponents.initialize();
        ModEnchantmentEffects.initialize();

        ModCriterionTriggers.initialize();

        ModPayloads.initialize();
        ModServerReceivers.initialize();

        ModDimensions.initialize();
        ModStructures.initialize();
        RefugePieces.initialize();

        ModLootTables.initialize();
        ModDamageTypes.initialize();

        ModRecipeSerializers.initialize();

        ModEvents.initialize();

        LOGGER.info("Finished initializing {}", MOD_ID);
    }
}