package com.adex;

import com.adex.advancement.criterion.ModCriterionTriggers;
import com.adex.block.ModBlocks;
import com.adex.data.damagetype.ModDamageTypes;
import com.adex.data.dimension.ModDimensions;
import com.adex.enchantment.effect.ModEnchantmentEffectComponents;
import com.adex.enchantment.effect.ModEnchantmentEffects;
import com.adex.entity.attribute.ModAttributes;
import com.adex.entity.poi.ModPoiTypes;
import com.adex.event.ModEvents;
import com.adex.data.feature.ModFeatures;
import com.adex.item.ModDataComponents;
import com.adex.item.ModItems;
import com.adex.data.tag.ModTags;
import com.adex.item.armor.ModArmorMaterials;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreAdventures implements ModInitializer {
    public static final String MOD_ID = "coread";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Starting initializing {}", MOD_ID);

        ModTags.initialize();

        ModArmorMaterials.initialize();
        ModDataComponents.initialize();

        ModItems.initialize();
        ModBlocks.initialize();

        ModAttributes.initialize();
        ModPoiTypes.initialize();

        ModEnchantmentEffectComponents.initialize();
        ModEnchantmentEffects.initialize();

        ModCriterionTriggers.initialize();

        ModDimensions.initialize();
        ModFeatures.initialize();

        ModDamageTypes.initialize();

        ModEvents.initialize();

        LOGGER.info("Finished initializing {}", MOD_ID);
    }
}