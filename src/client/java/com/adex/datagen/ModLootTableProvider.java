package com.adex.datagen;

import com.adex.block.ModBlocks;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.HARDENED_STONE);
        dropSelf(ModBlocks.HARDENED_STONE_SLAB);
        dropSelf(ModBlocks.HARDENED_STONE_STAIRS);
        dropSelf(ModBlocks.HARDENED_STONE_WALL);
        dropSelf(ModBlocks.HARDENED_STONE_BRICKS);
        dropSelf(ModBlocks.HARDENED_STONE_BRICKS_SLAB);
        dropSelf(ModBlocks.HARDENED_STONE_BRICKS_STAIRS);
        dropSelf(ModBlocks.HARDENED_STONE_BRICKS_WALL);

        dropSelf(ModBlocks.GABBRO);
        dropSelf(ModBlocks.GABBRO_SLAB);
        dropSelf(ModBlocks.GABBRO_STAIRS);
        dropSelf(ModBlocks.GABBRO_WALL);
        dropSelf(ModBlocks.POLISHED_GABBRO);
        dropSelf(ModBlocks.POLISHED_GABBRO_SLAB);
        dropSelf(ModBlocks.POLISHED_GABBRO_STAIRS);
        dropSelf(ModBlocks.POLISHED_GABBRO_WALL);

        dropSelf(ModBlocks.LARVIKITE);
        dropSelf(ModBlocks.LARVIKITE_SLAB);
        dropSelf(ModBlocks.LARVIKITE_STAIRS);
        dropSelf(ModBlocks.LARVIKITE_WALL);
        dropSelf(ModBlocks.POLISHED_LARVIKITE);
        dropSelf(ModBlocks.POLISHED_LARVIKITE_SLAB);
        dropSelf(ModBlocks.POLISHED_LARVIKITE_STAIRS);
        dropSelf(ModBlocks.POLISHED_LARVIKITE_WALL);

        dropSelf(ModBlocks.SERPENTINITE);
        dropSelf(ModBlocks.SERPENTINITE_SLAB);
        dropSelf(ModBlocks.SERPENTINITE_STAIRS);
        dropSelf(ModBlocks.SERPENTINITE_WALL);
        dropSelf(ModBlocks.POLISHED_SERPENTINITE);
        dropSelf(ModBlocks.POLISHED_SERPENTINITE_SLAB);
        dropSelf(ModBlocks.POLISHED_SERPENTINITE_STAIRS);
        dropSelf(ModBlocks.POLISHED_SERPENTINITE_WALL);

        dropSelf(ModBlocks.SLATE);
        dropSelf(ModBlocks.SLATE_SLAB);
        dropSelf(ModBlocks.SLATE_STAIRS);
        dropSelf(ModBlocks.SLATE_WALL);
        dropSelf(ModBlocks.POLISHED_SLATE);
        dropSelf(ModBlocks.POLISHED_SLATE_SLAB);
        dropSelf(ModBlocks.POLISHED_SLATE_STAIRS);
        dropSelf(ModBlocks.POLISHED_SLATE_WALL);

        dropSelf(ModBlocks.TRAVERTINE);
        dropSelf(ModBlocks.TRAVERTINE_SLAB);
        dropSelf(ModBlocks.TRAVERTINE_STAIRS);
        dropSelf(ModBlocks.TRAVERTINE_WALL);
        dropSelf(ModBlocks.POLISHED_TRAVERTINE);
        dropSelf(ModBlocks.POLISHED_TRAVERTINE_SLAB);
        dropSelf(ModBlocks.POLISHED_TRAVERTINE_STAIRS);
        dropSelf(ModBlocks.POLISHED_TRAVERTINE_WALL);

        dropSelf(ModBlocks.CRACKED_HARDENED_STONE_BRICKS);
        dropSelf(ModBlocks.HEAVY_HARDENED_STONE_BRICKS);


        dropSelf(ModBlocks.CHALCEDONY_BLOCK);
        dropSelf(ModBlocks.GARNET_BLOCK);
        dropSelf(ModBlocks.JADE_BLOCK);
        dropSelf(ModBlocks.JASPER_BLOCK);
        dropSelf(ModBlocks.ONYX_BLOCK);
        dropSelf(ModBlocks.OPAL_BLOCK);
        dropSelf(ModBlocks.RUBY_BLOCK);
        dropSelf(ModBlocks.SAPPHIRE_BLOCK);
        dropSelf(ModBlocks.SPINEL_BLOCK);
        dropSelf(ModBlocks.TIGERS_EYE_BLOCK);

        dropOre(ModBlocks.CHALCEDONY_ORE, ModItems.CHALCEDONY);
        dropOre(ModBlocks.GARNET_ORE, ModItems.GARNET);
        dropOre(ModBlocks.JADE_ORE, ModItems.JADE);
        dropOre(ModBlocks.JASPER_ORE, ModItems.JASPER);
        dropOre(ModBlocks.ONYX_ORE, ModItems.ONYX);
        dropOre(ModBlocks.OPAL_ORE, ModItems.OPAL);
        dropOre(ModBlocks.RUBY_ORE, ModItems.RUBY);
        dropOre(ModBlocks.SAPPHIRE_ORE, ModItems.SAPPHIRE);
        dropOre(ModBlocks.SPINEL_ORE, ModItems.SPINEL);
        dropOre(ModBlocks.TIGERS_EYE_ORE, ModItems.TIGERS_EYE);

        dropSelf(ModBlocks.JUNIPER_LOG);
        dropSelf(ModBlocks.JUNIPER_WOOD);
        dropSelf(ModBlocks.STRIPPED_JUNIPER_LOG);
        dropSelf(ModBlocks.STRIPPED_JUNIPER_WOOD);
        dropSelf(ModBlocks.JUNIPER_PLANKS);
        dropSelf(ModBlocks.JUNIPER_SHELF);
        dropSelf(ModBlocks.JUNIPER_SLAB);
        dropSelf(ModBlocks.JUNIPER_STAIRS);
        dropSelf(ModBlocks.JUNIPER_FENCE);
        dropSelf(ModBlocks.JUNIPER_FENCE_GATE);
        dropSelf(ModBlocks.JUNIPER_PRESSURE_PLATE);
        dropSelf(ModBlocks.JUNIPER_TRAPDOOR);
        add(ModBlocks.JUNIPER_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.JUNIPER_BUTTON);
        dropSelf(ModBlocks.JUNIPER_SIGN);
        dropSelf(ModBlocks.JUNIPER_HANGING_SIGN);
        dropSelf(ModBlocks.JUNIPER_SAPLING);
        dropPottedContents(ModBlocks.POTTED_JUNIPER_SAPLING);
        add(ModBlocks.JUNIPER_LEAVES, block -> createLeavesDrops(block, ModBlocks.JUNIPER_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));

        dropSelf(ModBlocks.REINFORCED_ANCIENT_DEBRIS);

        crateTnt(ModBlocks.RED_TNT);
        crateTnt(ModBlocks.ORANGE_TNT);
        crateTnt(ModBlocks.YELLOW_TNT);
        crateTnt(ModBlocks.GREEN_TNT);
        crateTnt(ModBlocks.BLUE_TNT);
    }

    public void crateTnt(Block block) {
        add(block, LootTable.lootTable().withPool(applyExplosionCondition(block,
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(block)
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TntBlock.UNSTABLE, false)))))));
    }

    public void dropOre(Block block, Item item) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        add(block, createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(item)
                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
    }

    @Override
    public @NonNull String getName() {
        return "Loot table provider";
    }
}
