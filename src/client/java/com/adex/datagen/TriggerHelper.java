package com.adex.datagen;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

import java.util.List;
import java.util.Optional;

public class TriggerHelper {

    public static Criterion<SummonedEntityTrigger.TriggerInstance> summonedEntityWithItems(EntityPredicate.Builder builder, ContextAwarePredicate items) {
        return CriteriaTriggers.SUMMONED_ENTITY.createCriterion(new SummonedEntityTrigger.TriggerInstance(Optional.of(items), Optional.of(EntityPredicate.wrap(builder))));
    }

    @SuppressWarnings("deprecation")
    public static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryChangedInDimension(ResourceKey<Level> dimension, ItemLike... items) {
        ItemPredicate[] itemPredicates = new ItemPredicate[items.length];
        for (int i = 0; i < items.length; i++) {
            itemPredicates[i] = new ItemPredicate(Optional.of(HolderSet.direct(items[i].asItem().builtInRegistryHolder())), MinMaxBounds.Ints.ANY, DataComponentMatchers.ANY);
        }

        return inventoryChangedInDimension(dimension, itemPredicates);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryChangedInDimension(ResourceKey<Level> dimension, ItemPredicate... items) {
        ContextAwarePredicate inDimension = ContextAwarePredicate.create(
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().located(LocationPredicate.Builder.inDimension(dimension))).build());

        return CriteriaTriggers.INVENTORY_CHANGED.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.of(inDimension), InventoryChangeTrigger.TriggerInstance.Slots.ANY, List.of(items)));
    }
}
