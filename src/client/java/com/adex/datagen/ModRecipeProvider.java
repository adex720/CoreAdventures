package com.adex.datagen;

import com.adex.block.ModBlocks;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new ModRecipeCreator(registryLookup, exporter);
    }

    @Override
    public String getName() {
        return "Recipe provider";
    }

    public static class ModRecipeCreator extends RecipeProvider {
        public ModRecipeCreator(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            super(provider, recipeOutput);
        }

        @Override
        public void buildRecipes() {
            HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

            itemsToBlock(ModItems.CHALCEDONY, ModBlocks.CHALCEDONY_BLOCK.asItem());
            itemsToBlock(ModItems.GARNET, ModBlocks.GARNET_BLOCK.asItem());
            itemsToBlock(ModItems.JADE, ModBlocks.JADE_BLOCK.asItem());
            itemsToBlock(ModItems.JASPER, ModBlocks.JASPER_BLOCK.asItem());
            itemsToBlock(ModItems.ONYX, ModBlocks.ONYX_BLOCK.asItem());
            itemsToBlock(ModItems.OPAL, ModBlocks.OPAL_BLOCK.asItem());
            itemsToBlock(ModItems.RUBY, ModBlocks.RUBY_ORE.asItem());
            itemsToBlock(ModItems.SAPPHIRE, ModBlocks.SAPPHIRE_BLOCK.asItem());
            itemsToBlock(ModItems.SPINEL, ModBlocks.SPINEL_BLOCK.asItem());
            itemsToBlock(ModItems.TIGERS_EYE, ModBlocks.TIGERS_EYE_BLOCK.asItem());

            blockToItems(ModBlocks.CHALCEDONY_BLOCK.asItem(), ModItems.CHALCEDONY);
            blockToItems(ModBlocks.GARNET_BLOCK.asItem(), ModItems.GARNET);
            blockToItems(ModBlocks.JADE_BLOCK.asItem(), ModItems.JADE);
            blockToItems(ModBlocks.JASPER_BLOCK.asItem(), ModItems.JASPER);
            blockToItems(ModBlocks.ONYX_BLOCK.asItem(), ModItems.ONYX);
            blockToItems(ModBlocks.OPAL_BLOCK.asItem(), ModItems.OPAL);
            blockToItems(ModBlocks.RUBY_ORE.asItem(), ModItems.RUBY);
            blockToItems(ModBlocks.SAPPHIRE_BLOCK.asItem(), ModItems.SAPPHIRE);
            blockToItems(ModBlocks.SPINEL_BLOCK.asItem(), ModItems.SPINEL);
            blockToItems(ModBlocks.TIGERS_EYE_BLOCK.asItem(), ModItems.TIGERS_EYE);

            oreSmeltingAndBlasting(ModBlocks.CHALCEDONY_ORE, RecipeCategory.MISC, ModItems.CHALCEDONY, 1.0f, 200, "chalcedony_smelting");
            oreSmeltingAndBlasting(ModBlocks.GARNET_ORE, RecipeCategory.MISC, ModItems.GARNET, 1.0f, 200, "garnet_smelting");
            oreSmeltingAndBlasting(ModBlocks.JADE_ORE, RecipeCategory.MISC, ModItems.JADE, 1.0f, 200, "jade_smelting");
            oreSmeltingAndBlasting(ModBlocks.JASPER_ORE, RecipeCategory.MISC, ModItems.JASPER, 1.0f, 200, "jasper_smelting");
            oreSmeltingAndBlasting(ModBlocks.ONYX_ORE, RecipeCategory.MISC, ModItems.ONYX, 1.0f, 200, "onyx_smelting");
            oreSmeltingAndBlasting(ModBlocks.OPAL_ORE, RecipeCategory.MISC, ModItems.OPAL, 1.0f, 200, "opal_smelting");
            oreSmeltingAndBlasting(ModBlocks.RUBY_ORE, RecipeCategory.MISC, ModItems.RUBY, 1.0f, 200, "ruby_smelting");
            oreSmeltingAndBlasting(ModBlocks.SAPPHIRE_ORE, RecipeCategory.MISC, ModItems.SAPPHIRE, 1.0f, 200, "sapphire_smelting");
            oreSmeltingAndBlasting(ModBlocks.SPINEL_ORE, RecipeCategory.MISC, ModItems.SPINEL, 1.0f, 200, "spinel_smelting");
            oreSmeltingAndBlasting(ModBlocks.TIGERS_EYE_ORE, RecipeCategory.MISC, ModItems.TIGERS_EYE, 1.0f, 200, "tigers_eye_smelting");
        }

        /**
         * Creates a recipe which turns 1 block into 9 items.
         */
        public void blockToItems(Item block, Item item) {
            shapeless(RecipeCategory.MISC, item, 9)
                    .requires(block)
                    .unlockedBy(getHasName(block), has(block))
                    .save(output);
        }

        /**
         * Creates a recipe which turns 9 items into 1 block.
         */
        public void itemsToBlock(Item item, Item block) {
            shapeless(RecipeCategory.BUILDING_BLOCKS, block)
                    .requires(item, 9)
                    .unlockedBy(getHasName(item), has(item))
                    .save(output);
        }


        /**
         * @param time Time for smelting, halved for blasting
         */
        public void oreSmeltingAndBlasting(ItemLike input, RecipeCategory recipeCategory, ItemLike output, float experience, int time, String group) {
            oreSmeltingAndBlasting(List.of(input), recipeCategory, output, experience, time, group);
        }

        /**
         * @param time Time for smelting, halved for blasting
         */
        public void oreSmeltingAndBlasting(List<ItemLike> input, RecipeCategory recipeCategory, ItemLike output, float experience, int time, String group) {
            oreSmelting(input, recipeCategory, output, experience, time, group);
            oreBlasting(input, recipeCategory, output, experience, time / 2, group);
        }
    }
}
