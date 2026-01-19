package com.adex.datagen;

import com.adex.datagen.model.ModEquipmentModelProvider;
import com.adex.datagen.model.ModModelProvider;
import com.adex.datagen.texture.ModBlockTextureProvider;
import com.adex.datagen.texture.ModEntityTextureProvider;
import com.adex.datagen.texture.ModHumanoidTextureProvider;
import com.adex.datagen.texture.ModItemTextureProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModDataGenerator implements DataGeneratorEntrypoint {

    public static final Logger LOGGER = LoggerFactory.getLogger("CoreAdventures DataGenerator");

    public static final String MOD_ID = "coread";

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModEquipmentModelProvider::new);
        pack.addProvider(ModCoreAdvancementProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModEnchantmentProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModChestLootTableProvider::new);

        pack.addProvider(ModItemTextureProvider::new);
        pack.addProvider(ModBlockTextureProvider::new);
        pack.addProvider(ModHumanoidTextureProvider::new);
        pack.addProvider(ModEntityTextureProvider::new);
    }

    public static String getIdentifierString(String name) {
        return MOD_ID + ":" + name;
    }

}