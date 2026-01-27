package com.adex.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class BecomeHeatImmuneTrigger extends SimpleCriterionTrigger<BecomeHeatImmuneTrigger.TriggerInstance> {

    @Override
    public @NonNull Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer serverPlayer) {
        this.trigger(serverPlayer, BecomeHeatImmuneTrigger.TriggerInstance::matches);
    }

    public record TriggerInstance(
            Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<BecomeHeatImmuneTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(BecomeHeatImmuneTrigger.TriggerInstance::player))
                        .apply(instance, BecomeHeatImmuneTrigger.TriggerInstance::new));

        public static Criterion<BecomeHeatImmuneTrigger.TriggerInstance> becomeHeatImmune() {
            return ModCriterionTriggers.BECOME_HEAT_IMMUNE.createCriterion(new BecomeHeatImmuneTrigger.TriggerInstance(Optional.empty()));
        }

        public boolean matches() {
            return true;
        }
    }
}
