package com.adex.advancement.criterion;

import com.adex.util.Util;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class HitWithSplashArrowTrigger extends SimpleCriterionTrigger<HitWithSplashArrowTrigger.TriggerInstance> {

    @Override
    public @NonNull Codec<HitWithSplashArrowTrigger.TriggerInstance> codec() {
        return HitWithSplashArrowTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack arrow, int count) {
        trigger(player, trigger -> trigger.matches(arrow, count));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<Holder<MobEffect>> effect,
                                  MinMaxBounds.Ints count) implements SimpleInstance {

        public static final Codec<HitWithSplashArrowTrigger.TriggerInstance> CODEC = RecordCodecBuilder.<HitWithSplashArrowTrigger.TriggerInstance>create(
                instance -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
                                        .forGetter(HitWithSplashArrowTrigger.TriggerInstance::player), BuiltInRegistries.MOB_EFFECT.holderByNameCodec()
                                        .optionalFieldOf("effect").forGetter(HitWithSplashArrowTrigger.TriggerInstance::effect),
                                MinMaxBounds.Ints.CODEC.optionalFieldOf("hit_count", MinMaxBounds.Ints.ANY).forGetter(HitWithSplashArrowTrigger.TriggerInstance::count))
                        .apply(instance, HitWithSplashArrowTrigger.TriggerInstance::new)).validate(HitWithSplashArrowTrigger.TriggerInstance::validate);

        private static DataResult<HitWithSplashArrowTrigger.TriggerInstance> validate(HitWithSplashArrowTrigger.TriggerInstance triggerInstance) {
            return DataResult.success(triggerInstance);
        }

        public static Criterion<HitWithSplashArrowTrigger.TriggerInstance> hitAny() {
            return ModCriterionTriggers.HIT_WITH_SPLASH_ARROW.createCriterion(new HitWithSplashArrowTrigger.TriggerInstance(Optional.empty(), Optional.empty(), MinMaxBounds.Ints.ANY));
        }

        public static Criterion<HitWithSplashArrowTrigger.TriggerInstance> hitCount(int minCount) {
            return ModCriterionTriggers.HIT_WITH_SPLASH_ARROW.createCriterion(new HitWithSplashArrowTrigger.TriggerInstance(Optional.empty(), Optional.empty(), MinMaxBounds.Ints.atLeast(minCount)));
        }

        public static Criterion<HitWithSplashArrowTrigger.TriggerInstance> hitWith(Holder<MobEffect> effect) {
            return ModCriterionTriggers.HIT_WITH_SPLASH_ARROW.createCriterion(new HitWithSplashArrowTrigger.TriggerInstance(Optional.empty(), Optional.of(effect), MinMaxBounds.Ints.ANY));
        }

        public static Criterion<HitWithSplashArrowTrigger.TriggerInstance> hitWithCount(Holder<MobEffect> effect, int minCount) {
            return ModCriterionTriggers.HIT_WITH_SPLASH_ARROW.createCriterion(new HitWithSplashArrowTrigger.TriggerInstance(Optional.empty(), Optional.of(effect), MinMaxBounds.Ints.atLeast(minCount)));
        }

        public boolean matches(ItemStack arrow, int count) {
            return hasCorrectEffect(arrow) && count().matches(count);
        }

        private boolean hasCorrectEffect(ItemStack arrow) {
            if (hasNoEffects(arrow)) return false;

            if (effect.isEmpty()) return true; // no required effects

            Holder<MobEffect> wanted = effect.get();
            PotionContents potionContents = arrow.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            return Util.contains(potionContents.getAllEffects(), e -> e.getEffect() == wanted);
        }

        private boolean hasNoEffects(ItemStack arrow) {
            PotionContents potionContents = arrow.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            return !potionContents.hasEffects();
        }
    }
}
