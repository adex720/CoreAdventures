package com.adex.datagen;

import com.adex.block.ModBlocks;
import com.adex.data.loottable.ModLootTables;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModChestLootTableProvider extends SimpleFabricLootTableProvider {

    public ModChestLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> generator) {
        generator.accept(ModLootTables.REFUGE_BASIC, LootTable.lootTable()
                .withPool(withCoreGems(ConstantValue.exactly(5), UniformGenerator.between(3, 5))));

        generator.accept(ModLootTables.REFUGE_TREASURE, LootTable.lootTable()
                .withPool(withCoreGems(ConstantValue.exactly(4), UniformGenerator.between(4, 6)))
                .withPool(withCoreArmors(ConstantValue.exactly(1), ConstantValue.exactly(1))));

        generator.accept(ModLootTables.REFUGE_PORTAL_ROOM, LootTable.lootTable()
                .withPool(withCoreGems(ConstantValue.exactly(4), UniformGenerator.between(4, 6)))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModBlocks.REINFORCED_ANCIENT_DEBRIS)).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.FIRE_CHARGE)).apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 3)))));
    }

    private LootPool.Builder withCoreGems(NumberProvider rolls, NumberProvider stackSize) {
        return LootPool.lootPool()
                .setRolls(rolls)
                .add(LootItem.lootTableItem(ModItems.CHALCEDONY)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.GARNET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JADE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JASPER)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.ONYX)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.OPAL)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.RUBY)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SPINEL)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.TIGERS_EYE)).apply(SetItemCountFunction.setCount(stackSize));
    }

    private LootPool.Builder withCoreArmors(NumberProvider rolls, NumberProvider stackSize) {
        return LootPool.lootPool()
                .setRolls(rolls)
                .add(LootItem.lootTableItem(ModItems.CHALCEDONY_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.CHALCEDONY_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.CHALCEDONY_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.CHALCEDONY_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.GARNET_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.GARNET_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.GARNET_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.GARNET_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JADE_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JADE_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JADE_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JADE_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JASPER_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JASPER_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JASPER_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.JASPER_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.ONYX_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.ONYX_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.ONYX_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.ONYX_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.OPAL_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.OPAL_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.OPAL_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.OPAL_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.RUBY_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.RUBY_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.RUBY_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.RUBY_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SPINEL_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SPINEL_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SPINEL_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.SPINEL_BOOTS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.TIGERS_EYE_HELMET)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.TIGERS_EYE_CHESTPLATE)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.TIGERS_EYE_LEGGINGS)).apply(SetItemCountFunction.setCount(stackSize))
                .add(LootItem.lootTableItem(ModItems.TIGERS_EYE_BOOTS)).apply(SetItemCountFunction.setCount(stackSize));
    }
}
