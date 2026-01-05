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

public class CoolWithIceTrigger extends SimpleCriterionTrigger<CoolWithIceTrigger.TriggerInstance> {

    @Override
    public @NonNull Codec<CoolWithIceTrigger.TriggerInstance> codec() {
        return CoolWithIceTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer serverPlayer, BlockState state) {
        trigger(serverPlayer, trigger -> trigger.matches(state));
    }

    public record TriggerInstance(
            Optional<ContextAwarePredicate> player,
            Optional<Holder<Block>> block,
            Optional<StatePropertiesPredicate> state) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<CoolWithIceTrigger.TriggerInstance> CODEC = RecordCodecBuilder.<CoolWithIceTrigger.TriggerInstance>create(
                        instance -> instance.group(
                                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(CoolWithIceTrigger.TriggerInstance::player),
                                        BuiltInRegistries.BLOCK.holderByNameCodec().optionalFieldOf("block").forGetter(CoolWithIceTrigger.TriggerInstance::block),
                                        StatePropertiesPredicate.CODEC.optionalFieldOf("state").forGetter(CoolWithIceTrigger.TriggerInstance::state)
                                )
                                .apply(instance, CoolWithIceTrigger.TriggerInstance::new)
                )
                .validate(CoolWithIceTrigger.TriggerInstance::validate);

        private static DataResult<CoolWithIceTrigger.TriggerInstance> validate(CoolWithIceTrigger.TriggerInstance triggerInstance) {
            Optional<DataResult<CoolWithIceTrigger.TriggerInstance>> optional = triggerInstance.block
                    .flatMap(
                            holder -> triggerInstance.state
                                    .flatMap(statePropertiesPredicate -> statePropertiesPredicate.checkState((holder.value()).getStateDefinition()))
                                    .map(string -> DataResult.error(() -> "Block" + holder + " has no property " + string))
                    );
            return optional.orElseGet(() -> DataResult.success(triggerInstance));
        }

        @SuppressWarnings("deprecation")
        public static Criterion<CoolWithIceTrigger.TriggerInstance> coolsWith(Block block) {
            return ModCriterionTriggers.COOL_WITH_ICE.createCriterion(new CoolWithIceTrigger.TriggerInstance(Optional.empty(), Optional.of(block.builtInRegistryHolder()), Optional.empty()));
        }

        public static Criterion<CoolWithIceTrigger.TriggerInstance> coolsWithAny() {
            return ModCriterionTriggers.COOL_WITH_ICE.createCriterion(new CoolWithIceTrigger.TriggerInstance(Optional.empty(), Optional.empty(), Optional.empty()));
        }

        public boolean matches(BlockState blockState) {
            return (this.block.isEmpty() || blockState.is(this.block.get())) && (this.state.isEmpty() || this.state.get().matches(blockState));
        }
    }
}
