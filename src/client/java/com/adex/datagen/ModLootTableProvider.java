package com.adex.datagen;

import com.adex.block.ModBlocks;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.HARDENED_STONE);

        dropSelf(ModBlocks.GABBRO);
        dropSelf(ModBlocks.LARVIKITE);
        dropSelf(ModBlocks.SERPENTINITE);
        dropSelf(ModBlocks.SLATE);
        dropSelf(ModBlocks.TRAVERTINE);

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

        dropSelf(ModBlocks.REINFORCED_ANCIENT_DEBRIS);
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
