package com.adex.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class CoolInCoreTrigger extends SimpleCriterionTrigger<CoolInCoreTrigger.TriggerInstance> {

    @Override
    public @NonNull Codec<CoolInCoreTrigger.TriggerInstance> codec() {
        return CoolInCoreTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer serverPlayer, BlockState state) {
        trigger(serverPlayer, trigger -> trigger.matches(state));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<Holder<Block>> block,
                                  Optional<StatePropertiesPredicate> state) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<CoolInCoreTrigger.TriggerInstance> CODEC = RecordCodecBuilder.<CoolInCoreTrigger.TriggerInstance>create(
                instance -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
                                        .forGetter(CoolInCoreTrigger.TriggerInstance::player), BuiltInRegistries.BLOCK.holderByNameCodec()
                                        .optionalFieldOf("block").forGetter(CoolInCoreTrigger.TriggerInstance::block),
                                StatePropertiesPredicate.CODEC.optionalFieldOf("state").forGetter(CoolInCoreTrigger.TriggerInstance::state))
                        .apply(instance, CoolInCoreTrigger.TriggerInstance::new)).validate(CoolInCoreTrigger.TriggerInstance::validate);

        private static DataResult<CoolInCoreTrigger.TriggerInstance> validate(CoolInCoreTrigger.TriggerInstance triggerInstance) {
            Optional<DataResult<CoolInCoreTrigger.TriggerInstance>> trigger = triggerInstance.block.flatMap(
                    holder -> triggerInstance.state.flatMap(predicate -> predicate.checkState((holder.value()).getStateDefinition()))
                            .map(string -> DataResult.error(() -> "Block" + holder + " has no property " + string)));
            return trigger.orElseGet(() -> DataResult.success(triggerInstance));
        }

        @SuppressWarnings("deprecation")
        public static Criterion<CoolInCoreTrigger.TriggerInstance> coolsWith(Block block) {
            return ModCriterionTriggers.COOL_IN_CORE.createCriterion(new CoolInCoreTrigger.TriggerInstance(Optional.empty(), Optional.of(block.builtInRegistryHolder()), Optional.empty()));
        }

        public static Criterion<CoolInCoreTrigger.TriggerInstance> coolsWithAny() {
            return ModCriterionTriggers.COOL_IN_CORE.createCriterion(new CoolInCoreTrigger.TriggerInstance(Optional.empty(), Optional.empty(), Optional.empty()));
        }

        public boolean matches(BlockState blockState) {
            return (this.block.isEmpty() || blockState.is(this.block.get())) && (this.state.isEmpty() || this.state.get().matches(blockState));
        }
    }
}
