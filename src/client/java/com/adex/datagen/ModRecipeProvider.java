package com.adex.datagen;

import com.adex.block.ModBlocks;
import com.adex.data.tag.ModTags;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registryLookup, @NonNull RecipeOutput exporter) {
        return new ModRecipeCreator(registryLookup, exporter);
    }

    @Override
    public @NonNull String getName() {
        return "Recipe provider";
    }

    public static class ModRecipeCreator extends RecipeProvider {
        public ModRecipeCreator(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            super(provider, recipeOutput);
        }

        @Override
        public void buildRecipes() {
            slabCraftingAndStonecutting(ModBlocks.HARDENED_STONE_SLAB, ModBlocks.HARDENED_STONE);
            stairsCraftingAndStonecutting(ModBlocks.HARDENED_STONE_STAIRS, ModBlocks.HARDENED_STONE);
            wallCraftingAndStonecutting(ModBlocks.HARDENED_STONE_WALL, ModBlocks.HARDENED_STONE);

            twoByTwo(RecipeCategory.BUILDING_BLOCKS, ModBlocks.HARDENED_STONE_BRICKS, ModBlocks.HARDENED_STONE, 4);
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.HARDENED_STONE_BRICKS, ModBlocks.HARDENED_STONE);
            slabCraftingAndStonecutting(ModBlocks.HARDENED_STONE_BRICKS_SLAB, ModBlocks.HARDENED_STONE_BRICKS, ModBlocks.HARDENED_STONE);
            stairsCraftingAndStonecutting(ModBlocks.HARDENED_STONE_BRICKS_STAIRS, ModBlocks.HARDENED_STONE_BRICKS, ModBlocks.HARDENED_STONE);
            wallCraftingAndStonecutting(ModBlocks.HARDENED_STONE_BRICKS_WALL, ModBlocks.HARDENED_STONE_BRICKS, ModBlocks.HARDENED_STONE);

            slabCraftingAndStonecutting(ModBlocks.GABBRO_SLAB, ModBlocks.GABBRO);
            stairsCraftingAndStonecutting(ModBlocks.GABBRO_STAIRS, ModBlocks.GABBRO);
            wallCraftingAndStonecutting(ModBlocks.GABBRO_WALL, ModBlocks.GABBRO);

            polishedCraftingAndStonecutting(ModBlocks.POLISHED_GABBRO, ModBlocks.GABBRO);
            slabCraftingAndStonecutting(ModBlocks.POLISHED_GABBRO_SLAB, ModBlocks.POLISHED_GABBRO, ModBlocks.GABBRO);
            stairsCraftingAndStonecutting(ModBlocks.POLISHED_GABBRO_STAIRS, ModBlocks.POLISHED_GABBRO, ModBlocks.GABBRO);
            wallCraftingAndStonecutting(ModBlocks.POLISHED_GABBRO_WALL, ModBlocks.POLISHED_GABBRO, ModBlocks.GABBRO);

            slabCraftingAndStonecutting(ModBlocks.LARVIKITE_SLAB, ModBlocks.LARVIKITE);
            stairsCraftingAndStonecutting(ModBlocks.LARVIKITE_STAIRS, ModBlocks.LARVIKITE);
            wallCraftingAndStonecutting(ModBlocks.LARVIKITE_WALL, ModBlocks.LARVIKITE);

            polishedCraftingAndStonecutting(ModBlocks.POLISHED_LARVIKITE, ModBlocks.LARVIKITE);
            slabCraftingAndStonecutting(ModBlocks.POLISHED_LARVIKITE_SLAB, ModBlocks.POLISHED_LARVIKITE, ModBlocks.LARVIKITE);
            stairsCraftingAndStonecutting(ModBlocks.POLISHED_LARVIKITE_STAIRS, ModBlocks.POLISHED_LARVIKITE, ModBlocks.LARVIKITE);
            wallCraftingAndStonecutting(ModBlocks.POLISHED_LARVIKITE_WALL, ModBlocks.POLISHED_LARVIKITE, ModBlocks.LARVIKITE);

            slabCraftingAndStonecutting(ModBlocks.SERPENTINITE_SLAB, ModBlocks.SERPENTINITE);
            stairsCraftingAndStonecutting(ModBlocks.SERPENTINITE_STAIRS, ModBlocks.SERPENTINITE);
            wallCraftingAndStonecutting(ModBlocks.SERPENTINITE_WALL, ModBlocks.SERPENTINITE);

            polishedCraftingAndStonecutting(ModBlocks.POLISHED_SERPENTINITE, ModBlocks.SERPENTINITE);
            slabCraftingAndStonecutting(ModBlocks.POLISHED_SERPENTINITE_SLAB, ModBlocks.POLISHED_SERPENTINITE, ModBlocks.SERPENTINITE);
            stairsCraftingAndStonecutting(ModBlocks.POLISHED_SERPENTINITE_STAIRS, ModBlocks.POLISHED_SERPENTINITE, ModBlocks.SERPENTINITE);
            wallCraftingAndStonecutting(ModBlocks.POLISHED_SERPENTINITE_WALL, ModBlocks.POLISHED_SERPENTINITE, ModBlocks.SERPENTINITE);

            slabCraftingAndStonecutting(ModBlocks.SLATE_SLAB, ModBlocks.SLATE);
            stairsCraftingAndStonecutting(ModBlocks.SLATE_STAIRS, ModBlocks.SLATE);
            wallCraftingAndStonecutting(ModBlocks.SLATE_WALL, ModBlocks.SLATE);

            polishedCraftingAndStonecutting(ModBlocks.POLISHED_SLATE, ModBlocks.SLATE);
            slabCraftingAndStonecutting(ModBlocks.POLISHED_SLATE_SLAB, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE);
            stairsCraftingAndStonecutting(ModBlocks.POLISHED_SLATE_STAIRS, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE);
            wallCraftingAndStonecutting(ModBlocks.POLISHED_SLATE_WALL, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE);

            slabCraftingAndStonecutting(ModBlocks.TRAVERTINE_SLAB, ModBlocks.TRAVERTINE);
            stairsCraftingAndStonecutting(ModBlocks.TRAVERTINE_STAIRS, ModBlocks.TRAVERTINE);
            wallCraftingAndStonecutting(ModBlocks.TRAVERTINE_WALL, ModBlocks.TRAVERTINE);

            polishedCraftingAndStonecutting(ModBlocks.POLISHED_TRAVERTINE, ModBlocks.TRAVERTINE);
            slabCraftingAndStonecutting(ModBlocks.POLISHED_TRAVERTINE_SLAB, ModBlocks.POLISHED_TRAVERTINE, ModBlocks.TRAVERTINE);
            stairsCraftingAndStonecutting(ModBlocks.POLISHED_TRAVERTINE_STAIRS, ModBlocks.POLISHED_TRAVERTINE, ModBlocks.TRAVERTINE);
            wallCraftingAndStonecutting(ModBlocks.POLISHED_TRAVERTINE_WALL, ModBlocks.POLISHED_TRAVERTINE, ModBlocks.TRAVERTINE);

            oreSmeltingAndBlasting(ModBlocks.HARDENED_STONE_BRICKS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_HARDENED_STONE_BRICKS, 1.0f, 200, "hardened_stone_bricks_smelting");
            twoByTwo(RecipeCategory.BUILDING_BLOCKS, ModBlocks.HEAVY_HARDENED_STONE_BRICKS, ModBlocks.HARDENED_STONE_BRICKS, 1);

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
            armor(ModItems.SHINY_GEM_MIXTURE, ModItems.GEM_HELMET, ModItems.GEM_CHESTPLATE, ModItems.GEM_LEGGINGS, ModItems.GEM_BOOTS);

            woodFromLogs(ModBlocks.JUNIPER_WOOD, ModBlocks.JUNIPER_LOG);
            woodFromLogs(ModBlocks.STRIPPED_JUNIPER_WOOD, ModBlocks.STRIPPED_JUNIPER_LOG);
            planksFromLog(ModBlocks.JUNIPER_PLANKS, ModTags.JUNIPER_LOGS_ITEM, 4);
            shelf(ModBlocks.JUNIPER_SHELF, ModBlocks.STRIPPED_JUNIPER_LOG);
            slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNIPER_SLAB, ModBlocks.JUNIPER_PLANKS);
            stairs(ModBlocks.JUNIPER_STAIRS, ModBlocks.JUNIPER_PLANKS);
            fence(ModBlocks.JUNIPER_FENCE, ModBlocks.JUNIPER_PLANKS);
            fenceGate(ModBlocks.JUNIPER_FENCE_GATE, ModBlocks.JUNIPER_PLANKS);
            pressurePlate(ModBlocks.JUNIPER_PRESSURE_PLATE, ModBlocks.JUNIPER_PLANKS);
            trapdoor(ModBlocks.JUNIPER_TRAPDOOR, ModBlocks.JUNIPER_PLANKS);
            door(ModBlocks.JUNIPER_DOOR, ModBlocks.JUNIPER_PLANKS);
            button(ModBlocks.JUNIPER_BUTTON, ModBlocks.JUNIPER_PLANKS);
            sign(ModBlocks.JUNIPER_SIGN, ModBlocks.JUNIPER_PLANKS);
            hangingSign(ModBlocks.JUNIPER_HANGING_SIGN, ModBlocks.STRIPPED_JUNIPER_LOG);
            woodenBoat(ModItems.JUNIPER_BOAT, ModBlocks.JUNIPER_PLANKS);
            chestBoat(ModItems.JUNIPER_CHEST_BOAT, ModItems.JUNIPER_BOAT);

            surroundBy4(ModBlocks.RED_TNT, Blocks.TNT, ModItems.DYNAMITE, RecipeCategory.REDSTONE, UnlockStyle.SECOND);
            nToOne(ModBlocks.ORANGE_TNT, ModBlocks.RED_TNT, RecipeCategory.REDSTONE, 2);
            nToOne(ModBlocks.YELLOW_TNT, ModBlocks.ORANGE_TNT, RecipeCategory.REDSTONE, 2);
            nToOne(ModBlocks.GREEN_TNT, ModBlocks.YELLOW_TNT, RecipeCategory.REDSTONE, 2);
            nToOne(ModBlocks.BLUE_TNT, ModBlocks.GREEN_TNT, RecipeCategory.REDSTONE, 2);
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

        public void surroundBy4(ItemLike result, ItemLike inner, ItemLike outer, RecipeCategory recipeCategory, UnlockStyle unlockStyle) {
            ShapedRecipeBuilder builder = shaped(recipeCategory, result)
                    .define('#', outer)
                    .define('X', inner)
                    .pattern(" # ")
                    .pattern("#X#")
                    .pattern(" # ");

            if (unlockStyle == UnlockStyle.ANY || unlockStyle == UnlockStyle.FIRST)
                builder.unlockedBy(getHasName(inner), has(inner));
            if (unlockStyle == UnlockStyle.ANY || unlockStyle == UnlockStyle.SECOND)
                builder.unlockedBy(getHasName(outer), has(outer));

            builder.save(output);
        }

        public void nToOne(ItemLike result, ItemLike ingredient, RecipeCategory recipeCategory, int n) {
            shapeless(recipeCategory, result)
                    .requires(ingredient, n)
                    .unlockedBy(getHasName(ingredient), has(ingredient))
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

        /**
         * Creates a polished block recipe for crafting table and stonecutter
         *
         * @param polished   Polished item
         * @param ingredient Item to use in recipe
         */
        public void polishedCraftingAndStonecutting(ItemLike polished, ItemLike ingredient) {
            polished(RecipeCategory.BUILDING_BLOCKS, polished, ingredient);
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, polished, ingredient);
        }

        /**
         * Generates a recipe requiring exactly one of each provided ingredient
         *
         * @param category Recipe category
         * @param result   Reward item
         * @param items    Ingredients
         */
        public void fromItems(RecipeCategory category, Item result, ItemLike... items) {
            ShapelessRecipeBuilder builder = shapeless(category, result);
            for (ItemLike item : items) {
                builder.requires(item);
                builder.unlockedBy(getHasName(item), has(item));
            }
            builder.save(output);
        }

        /**
         * Creates a stairs recipe for crafting table and stonecutter.
         * A crafting recipe is generated only for the first given ingredient.
         * A unique stonecutter recipe is generated for each given ingredient.
         *
         * @param slab        Slab item
         * @param ingredients Items to use as ingredient
         */
        public void slabCraftingAndStonecutting(ItemLike slab, ItemLike... ingredients) {
            slab(RecipeCategory.BUILDING_BLOCKS, slab, ingredients[0]);
            for (ItemLike ingredient : ingredients) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slab, ingredient, 2);
            }
        }

        /**
         * Creates a stairs recipe for crafting table and stonecutter.
         * A crafting recipe is generated only for the first given ingredient.
         * A unique recipe is generated for each given ingredient.
         *
         * @param wall        Wall item
         * @param ingredients Items to use as ingredient
         */
        public void wallCraftingAndStonecutting(ItemLike wall, ItemLike... ingredients) {
            wall(RecipeCategory.BUILDING_BLOCKS, wall, ingredients[0]);
            for (ItemLike ingredient : ingredients) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wall, ingredient);
            }
        }

        /**
         * Creates a stairs recipe for crafting table.
         * A crafting recipe is generated only for the first given ingredient.
         * A unique recipe is generated for each given ingredient.
         *
         * @param stairs      Stairs item
         * @param ingredients Items to use as ingredient
         */
        public void stairsCraftingAndStonecutting(ItemLike stairs, ItemLike... ingredients) {
            stairBuilder(stairs, Ingredient.of(ingredients[0])).unlockedBy(getHasName(ingredients[0]), has(ingredients[0])).save(output);
            for (ItemLike ingredient : ingredients) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairs, ingredient);
            }
        }

        /**
         * Creates a stairs recipe for crafting table
         *
         * @param stairs     Stairs item
         * @param ingredient Item to use in recipe
         */
        public void stairs(ItemLike stairs, ItemLike ingredient) {
            stairBuilder(stairs, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
        }

        /**
         * Creates a fence recipe for crafting table
         *
         * @param fence      Fence item
         * @param ingredient Item to use in recipe
         */
        public void fence(ItemLike fence, ItemLike ingredient) {
            fenceBuilder(fence, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
        }

        /**
         * Creates a fence gate recipe for crafting table
         *
         * @param fenceGate  Fence gate item
         * @param ingredient Item to use in recipe
         */
        public void fenceGate(ItemLike fenceGate, ItemLike ingredient) {
            fenceGateBuilder(fenceGate, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
        }

        /**
         * Creates a trapdoor recipe for crafting table
         *
         * @param trapdoor   Trapdoor item
         * @param ingredient Item to use in recipe
         */
        public void trapdoor(ItemLike trapdoor, ItemLike ingredient) {
            trapdoorBuilder(trapdoor, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
        }

        /**
         * Creates a door recipe for crafting table
         *
         * @param door       Door item
         * @param ingredient Item to use in recipe
         */
        public void door(ItemLike door, ItemLike ingredient) {
            doorBuilder(door, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
        }

        /**
         * Creates a button recipe for crafting table
         *
         * @param button     Button item
         * @param ingredient Item to use in recipe
         */
        public void button(ItemLike button, ItemLike ingredient) {
            buttonBuilder(button, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
        }

        /**
         * Creates a sign recipe for crafting table
         *
         * @param sign       Sign item
         * @param ingredient Item to use in recipe
         */
        public void sign(ItemLike sign, ItemLike ingredient) {
            signBuilder(sign, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(output);
        }

        public void twoByTwo(RecipeCategory recipeCategory, ItemLike result, ItemLike ingredient, int count) {
            shaped(recipeCategory, result, count)
                    .define('#', ingredient)
                    .pattern("##")
                    .pattern("##")
                    .unlockedBy(getHasName(ingredient), has(ingredient))
                    .save(output);
        }
    }

    public enum UnlockStyle {
        ANY, FIRST, SECOND
    }
}
