package com.adex;

import com.adex.block.ModBlocks;
import com.adex.dimension.ModDimensions;
import com.adex.item.ModItems;
import com.adex.poi.ModPoiTypes;
import com.adex.tag.ModTags;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreAdventures implements ModInitializer {
    public static final String MOD_ID = "coread";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Starting initializing {}", MOD_ID);

        ModItems.initialize();
        ModBlocks.initialize();

        ModTags.initialize();
        ModDimensions.initialize();
        ModPoiTypes.initialize();

        LOGGER.info("Finished initializing {}", MOD_ID);
    }
}