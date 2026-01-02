package com.adex.datagen;

import com.adex.block.ModBlocks;
import com.adex.data.dimension.ModDimensions;
import com.adex.data.tag.ModTags;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModCoreAdvancementProvider extends FabricAdvancementProvider {

    public ModCoreAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        HolderLookup<Item> itemHolderLookup = provider.lookupOrThrow(Registries.ITEM);

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

        AdvancementHolder getCoreArmor = Advancement.Builder.advancement()
                .parent(root)
                .display(ModItems.JADE_CHESTPLATE,
                        Component.translatable("advancements.coread.core.get_core_armor.title"),
                        Component.translatable("advancements.coread.core.get_core_armor.description"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false)
                .addCriterion("got_core_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(itemHolderLookup, ModTags.CORE_ARMOR)))
                .save(consumer, ModDataGenerator.getIdentifierString("get_core_armor"));

        AdvancementHolder getFullCoreArmor = Advancement.Builder.advancement()
                .parent(getCoreArmor)
                .display(ModItems.RUBY_CHESTPLATE,
                        Component.translatable("advancements.coread.core.get_full_core_armor.title"),
                        Component.translatable("advancements.coread.core.get_full_core_armor.description"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false)
                .addCriterion("got_gem_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GEM_HELMET, ModItems.GEM_CHESTPLATE, ModItems.GEM_LEGGINGS, ModItems.GEM_BOOTS))
                .addCriterion("got_chalcedony_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CHALCEDONY_HELMET, ModItems.CHALCEDONY_CHESTPLATE, ModItems.CHALCEDONY_LEGGINGS, ModItems.CHALCEDONY_BOOTS))
                .addCriterion("got_garnet_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GARNET_HELMET, ModItems.GARNET_CHESTPLATE, ModItems.GARNET_LEGGINGS, ModItems.GARNET_BOOTS))
                .addCriterion("got_jasper_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.JASPER_HELMET, ModItems.JASPER_CHESTPLATE, ModItems.JASPER_LEGGINGS, ModItems.JASPER_BOOTS))
                .addCriterion("got_jade_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.JADE_HELMET, ModItems.JADE_CHESTPLATE, ModItems.JADE_LEGGINGS, ModItems.JADE_BOOTS))
                .addCriterion("got_onyx_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ONYX_HELMET, ModItems.ONYX_CHESTPLATE, ModItems.ONYX_LEGGINGS, ModItems.ONYX_BOOTS))
                .addCriterion("got_opal_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OPAL_HELMET, ModItems.OPAL_CHESTPLATE, ModItems.OPAL_LEGGINGS, ModItems.OPAL_BOOTS))
                .addCriterion("got_ruby_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS))
                .addCriterion("got_sapphire_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE_BOOTS))
                .addCriterion("got_spinel_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPINEL_HELMET, ModItems.SPINEL_CHESTPLATE, ModItems.SPINEL_LEGGINGS, ModItems.SPINEL_BOOTS))
                .addCriterion("got_tigers_eye_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIGERS_EYE_HELMET, ModItems.TIGERS_EYE_CHESTPLATE, ModItems.TIGERS_EYE_LEGGINGS, ModItems.TIGERS_EYE_BOOTS))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, ModDataGenerator.getIdentifierString("get_full_core_armor"));

        AdvancementHolder getFullGemArmor = Advancement.Builder.advancement()
                .parent(getFullCoreArmor)
                .display(ModItems.GEM_CHESTPLATE,
                        Component.translatable("advancements.coread.core.get_full_gem_armor.title"),
                        Component.translatable("advancements.coread.core.get_full_gem_armor.description"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false)
                .addCriterion("got_full_gem_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GEM_HELMET, ModItems.GEM_CHESTPLATE, ModItems.GEM_LEGGINGS, ModItems.GEM_BOOTS))
                .save(consumer, ModDataGenerator.getIdentifierString("get_full_gem_armor"));

    }
}
