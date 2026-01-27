package com.adex.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class LightCorePortalTrigger extends SimpleCriterionTrigger<LightCorePortalTrigger.TriggerInstance> {

    @Override
    public @NonNull Codec<LightCorePortalTrigger.TriggerInstance> codec() {
        return LightCorePortalTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer serverPlayer) {
        this.trigger(serverPlayer, TriggerInstance::matches);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<LightCorePortalTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(LightCorePortalTrigger.TriggerInstance::player))
                        .apply(instance, LightCorePortalTrigger.TriggerInstance::new));

        public boolean matches() {
            return true;
        }
    }
}
