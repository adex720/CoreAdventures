package com.adex.datagen;

import com.adex.datagen.model.ModEquipmentModelProvider;
import com.adex.datagen.model.ModModelProvider;
import com.adex.datagen.texture.ModHumanoidTextureProvider;
import com.adex.datagen.texture.ModItemTextureProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModDataGenerator implements DataGeneratorEntrypoint {

    public static final Logger LOGGER = LoggerFactory.getLogger("CoreAdventures DataGenerator");

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModEquipmentModelProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        pack.addProvider(ModItemTextureProvider::new);
        pack.addProvider(ModHumanoidTextureProvider::new);
    }

}