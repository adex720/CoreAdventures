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
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

            fromItems(RecipeCategory.MISC, ModItems.BLUE_GEM_MIXTURE, ModItems.CHALCEDONY, ModItems.JADE, ModItems.OPAL, ModItems.SAPPHIRE, Items.DIAMOND, Items.EMERALD, Items.LAPIS_LAZULI);
            fromItems(RecipeCategory.MISC, ModItems.RED_GEM_MIXTURE, ModItems.GARNET, ModItems.JASPER, ModItems.ONYX, ModItems.RUBY, ModItems.SPINEL, ModItems.TIGERS_EYE, Items.NETHERITE_INGOT);
            fromItems(RecipeCategory.MISC, ModItems.SHINY_GEM_MIXTURE, ModItems.BLUE_GEM_MIXTURE, ModItems.RED_GEM_MIXTURE);

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

            armor(ModItems.CHALCEDONY, ModItems.CHALCEDONY_HELMET, ModItems.CHALCEDONY_CHESTPLATE, ModItems.CHALCEDONY_LEGGINGS, ModItems.CHALCEDONY_BOOTS);
            armor(ModItems.GARNET, ModItems.GARNET_HELMET, ModItems.GARNET_CHESTPLATE, ModItems.GARNET_LEGGINGS, ModItems.GARNET_BOOTS);
            armor(ModItems.JADE, ModItems.JADE_HELMET, ModItems.JADE_CHESTPLATE, ModItems.JADE_LEGGINGS, ModItems.JADE_BOOTS);
            armor(ModItems.JASPER, ModItems.JASPER_HELMET, ModItems.JASPER_CHESTPLATE, ModItems.JASPER_LEGGINGS, ModItems.JASPER_BOOTS);
            armor(ModItems.ONYX, ModItems.ONYX_HELMET, ModItems.ONYX_CHESTPLATE, ModItems.ONYX_LEGGINGS, ModItems.ONYX_BOOTS);
            armor(ModItems.OPAL, ModItems.OPAL_HELMET, ModItems.OPAL_CHESTPLATE, ModItems.OPAL_LEGGINGS, ModItems.OPAL_BOOTS);
            armor(ModItems.RUBY, ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS);
            armor(ModItems.SAPPHIRE, ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE_BOOTS);
            armor(ModItems.SPINEL, ModItems.SPINEL_HELMET, ModItems.SPINEL_CHESTPLATE, ModItems.SPINEL_LEGGINGS, ModItems.SPINEL_BOOTS);
            armor(ModItems.TIGERS_EYE, ModItems.TIGERS_EYE_HELMET, ModItems.TIGERS_EYE_CHESTPLATE, ModItems.TIGERS_EYE_LEGGINGS, ModItems.TIGERS_EYE_BOOTS);
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

        public void armor(Item item, Item helmet, Item chestplate, Item leggings, Item boots) {
            helmet(item, helmet);
            chestplate(item, chestplate);
            leggings(item, leggings);
            boots(item, boots);
        }

        public void helmet(Item item, Item helmet) {
            shaped(RecipeCategory.COMBAT, helmet)
                    .pattern("###")
                    .pattern("# #")
                    .define('#', item)
                    .unlockedBy(getHasName(item), has(item))
                    .save(output);
        }

        public void chestplate(Item item, Item chestplate) {
            shaped(RecipeCategory.COMBAT, chestplate)
                    .pattern("# #")
                    .pattern("###")
                    .pattern("###")
                    .define('#', item)
                    .unlockedBy(getHasName(item), has(item))
                    .save(output);
        }

        public void leggings(Item item, Item leggings) {
            shaped(RecipeCategory.COMBAT, leggings)
                    .pattern("###")
                    .pattern("# #")
                    .pattern("# #")
                    .define('#', item)
                    .unlockedBy(getHasName(item), has(item))
                    .save(output);
        }

        public void boots(Item item, Item boots) {
            shaped(RecipeCategory.COMBAT, boots)
                    .pattern("# #")
                    .pattern("# #")
                    .define('#', item)
                    .unlockedBy(getHasName(item), has(item))
                    .save(output);
        }

        public void fromItems(RecipeCategory category, Item result, ItemLike... items) {
            ShapelessRecipeBuilder builder = shapeless(category, result);
            for (ItemLike item : items) {
                builder.requires(item);
                builder.unlockedBy(getHasName(item), has(item));
            }
            builder.save(output);
        }
    }
}
