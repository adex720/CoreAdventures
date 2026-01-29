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

public class TradeWithSentryTrigger extends SimpleCriterionTrigger<TradeWithSentryTrigger.TriggerInstance> {

    @Override
    public @NonNull Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer serverPlayer) {
        this.trigger(serverPlayer, TradeWithSentryTrigger.TriggerInstance::matches);
    }

    public record TriggerInstance(
            Optional<ContextAwarePredicate> player) implements SimpleInstance {

        public static final Codec<TradeWithSentryTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TradeWithSentryTrigger.TriggerInstance::player))
                        .apply(instance, TradeWithSentryTrigger.TriggerInstance::new));

        public static Criterion<TradeWithSentryTrigger.TriggerInstance> tradeWithSentry() {
            return ModCriterionTriggers.TRADE_WITH_SENTRY.createCriterion(new TradeWithSentryTrigger.TriggerInstance(Optional.empty()));
        }

        public boolean matches() {
            return true;
        }
    }
}
