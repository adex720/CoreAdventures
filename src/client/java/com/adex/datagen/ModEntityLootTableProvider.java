package com.adex.datagen;

import com.adex.entity.ModEntities;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEntityLootTableProvider extends SimpleFabricLootTableProvider {

    public ModEntityLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.ENTITY);
    }

    @Override
    public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> generator) {
        generator.accept(getLootTable(ModEntities.TRADER_SENTRY), LootTable.lootTable()
                .withPool(withCoreGems(UniformGenerator.between(1, 2), UniformGenerator.between(1, 4))));

        generator.accept(getLootTable(ModEntities.WARRIOR_SENTRY), LootTable.lootTable()
                .withPool(withCoreGems((ConstantValue.exactly(1)), UniformGenerator.between(1, 3))));
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

    private ResourceKey<LootTable> getLootTable(EntityType<?> entityType) {
        return entityType.getDefaultLootTable().orElseThrow(() -> new IllegalStateException("Entity " + entityType + " has no loot table"));
    }

}
