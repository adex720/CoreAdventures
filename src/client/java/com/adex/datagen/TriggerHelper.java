package com.adex.datagen;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SummonedEntityTrigger;

import java.util.Optional;

public class TriggerHelper {

    public static Criterion<SummonedEntityTrigger.TriggerInstance> summonedEntityWithItems(EntityPredicate.Builder builder, ContextAwarePredicate items) {
        return CriteriaTriggers.SUMMONED_ENTITY.createCriterion(new SummonedEntityTrigger.TriggerInstance(Optional.of(items), Optional.of(EntityPredicate.wrap(builder))));
    }
}
