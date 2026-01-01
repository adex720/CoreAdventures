package com.adex.datagen;

import com.adex.block.ModBlocks;
import com.adex.data.dimension.ModDimensions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModCoreAdvancementProvider extends FabricAdvancementProvider {

    public ModCoreAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(ModBlocks.REINFORCED_ANCIENT_DEBRIS.asItem(),
                        Component.translatable("advancements.coread.core.root.title"),
                        Component.translatable("advancements.coread.core.root.description"),
                        Identifier.fromNamespaceAndPath(ModDataGenerator.MOD_ID, "gui/advancements/backgrounds/core"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false)
                .addCriterion("entered_root", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.CORE))
                .save(consumer, ModDataGenerator.getIdentifierString("root"));
    }
}
