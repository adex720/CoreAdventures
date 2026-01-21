package com.adex.datagen;

import com.adex.advancement.criterion.CoolWithIceTrigger;
import com.adex.block.ModBlocks;
import com.adex.data.dimension.ModDimensions;
import com.adex.data.structure.ModStructures;
import com.adex.data.tag.ModTags;
import com.adex.entity.ModEntities;
import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModCoreAdvancementProvider extends FabricAdvancementProvider {

    public ModCoreAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @SuppressWarnings("unused")
    @Override
    public void generateAdvancement(HolderLookup.Provider provider, @NonNull Consumer<AdvancementHolder> consumer) {
        HolderLookup<Item> itemHolderLookup = provider.lookupOrThrow(Registries.ITEM);
        HolderLookup<EntityType<?>> entityHolderLookup = provider.lookupOrThrow(Registries.ENTITY_TYPE);
        HolderLookup<Structure> structureHolderLookup = provider.lookupOrThrow(Registries.STRUCTURE);

        ContextAwarePredicate fullChalcedonyArmor = hasArmor(itemHolderLookup, ModItems.CHALCEDONY_HELMET, ModItems.CHALCEDONY_CHESTPLATE, ModItems.CHALCEDONY_LEGGINGS, ModItems.CHALCEDONY_BOOTS);
        ContextAwarePredicate fullGarnetArmor = hasArmor(itemHolderLookup, ModItems.GARNET_HELMET, ModItems.GARNET_CHESTPLATE, ModItems.GARNET_LEGGINGS, ModItems.GARNET_BOOTS);
        ContextAwarePredicate fullJadeArmor = hasArmor(itemHolderLookup, ModItems.JADE_HELMET, ModItems.JADE_CHESTPLATE, ModItems.JADE_LEGGINGS, ModItems.JADE_BOOTS);
        ContextAwarePredicate fullJasperArmor = hasArmor(itemHolderLookup, ModItems.JASPER_HELMET, ModItems.JASPER_CHESTPLATE, ModItems.JASPER_LEGGINGS, ModItems.JASPER_BOOTS);
        ContextAwarePredicate fullOnyxArmor = hasArmor(itemHolderLookup, ModItems.ONYX_HELMET, ModItems.ONYX_CHESTPLATE, ModItems.ONYX_LEGGINGS, ModItems.ONYX_BOOTS);
        ContextAwarePredicate fullOpalArmor = hasArmor(itemHolderLookup, ModItems.OPAL_HELMET, ModItems.OPAL_CHESTPLATE, ModItems.OPAL_LEGGINGS, ModItems.OPAL_BOOTS);
        ContextAwarePredicate fullRubyArmor = hasArmor(itemHolderLookup, ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS);
        ContextAwarePredicate fullSapphireArmor = hasArmor(itemHolderLookup, ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE_BOOTS);
        ContextAwarePredicate fullSpinelArmor = hasArmor(itemHolderLookup, ModItems.SPINEL_HELMET, ModItems.SPINEL_CHESTPLATE, ModItems.SPINEL_LEGGINGS, ModItems.SPINEL_BOOTS);
        ContextAwarePredicate fullTigersEyeArmor = hasArmor(itemHolderLookup, ModItems.TIGERS_EYE_HELMET, ModItems.TIGERS_EYE_CHESTPLATE, ModItems.TIGERS_EYE_LEGGINGS, ModItems.TIGERS_EYE_BOOTS);

        AdvancementHolder root = Advancement.Builder.advancement()
                .display(ModBlocks.REINFORCED_ANCIENT_DEBRIS.asItem(),
                        Component.translatable("advancements.coread.core.root.title"),
                        Component.translatable("advancements.coread.core.root.description"),
                        Identifier.fromNamespaceAndPath(ModDataGenerator.MOD_ID, "gui/advancements/backgrounds/core"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false)
                .addCriterion("entered_core", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.CORE))
                .save(consumer, ModDataGenerator.getIdentifierString("root"));

        AdvancementHolder getCoreArmor = Advancement.Builder.advancement()
                .parent(root)
                .display(ModItems.JADE_CHESTPLATE,
                        Component.translatable("advancements.coread.core.get_core_armor.title"),
                        Component.translatable("advancements.coread.core.get_core_armor.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
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
                        true,
                        true,
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
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false)
                .addCriterion("got_full_gem_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GEM_HELMET, ModItems.GEM_CHESTPLATE, ModItems.GEM_LEGGINGS, ModItems.GEM_BOOTS))
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(consumer, ModDataGenerator.getIdentifierString("get_full_gem_armor"));

        AdvancementHolder coolWithIce = Advancement.Builder.advancement()
                .parent(root)
                .display(Items.ICE,
                        Component.translatable("advancements.coread.core.cool_with_any.title"),
                        Component.translatable("advancements.coread.core.cool_with_any.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("cool_with_any", CoolWithIceTrigger.TriggerInstance.coolsWithAny())
                .save(consumer, ModDataGenerator.getIdentifierString("cool_with_any"));

        AdvancementHolder coolWithBlueIce = Advancement.Builder.advancement()
                .parent(coolWithIce)
                .display(Items.ICE,
                        Component.translatable("advancements.coread.core.cool_with_blue_ice.title"),
                        Component.translatable("advancements.coread.core.cool_with_blue_ice.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false)
                .addCriterion("cool_with_blue_ice", CoolWithIceTrigger.TriggerInstance.coolsWith(Blocks.BLUE_ICE))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(consumer, ModDataGenerator.getIdentifierString("cool_with_blue_ice"));

        AdvancementHolder enterRefuge = Advancement.Builder.advancement()
                .parent(root)
                .display(ModBlocks.HARDENED_STONE_BRICKS,
                        Component.translatable("advancements.coread.core.find_refuge.title"),
                        Component.translatable("advancements.coread.core.find_refuge.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("entered_refuge", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structureHolderLookup.getOrThrow(ModStructures.REFUGE_KEY))))
                .save(consumer, ModDataGenerator.getIdentifierString("find_refuge"));

        AdvancementHolder summonGolem = Advancement.Builder.advancement()
                .parent(enterRefuge)
                .display(ModBlocks.GARNET_GOLEM_BLOCK,
                        Component.translatable("advancements.coread.core.summon_golem.title"),
                        Component.translatable("advancements.coread.core.summon_golem.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("summoned_chalcedony_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.CHALCEDONY_GOLEM)))
                .addCriterion("summoned_garnet_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.GARNET_GOLEM)))
                .addCriterion("summoned_jade_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.JADE_GOLEM)))
                .addCriterion("summoned_jasper_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.JASPER_GOLEM)))
                .addCriterion("summoned_onyx_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.ONYX_GOLEM)))
                .addCriterion("summoned_opal_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.OPAL_GOLEM)))
                .addCriterion("summoned_ruby_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.RUBY_GOLEM)))
                .addCriterion("summoned_sapphire_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.SAPPHIRE_GOLEM)))
                .addCriterion("summoned_spinel_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.SPINEL_GOLEM)))
                .addCriterion("summoned_tigers_eye_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.TIGERS_EYE_GOLEM)))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, ModDataGenerator.getIdentifierString("summon_golem"));

        AdvancementHolder summonGolemWithArmor = Advancement.Builder.advancement()
                .parent(summonGolem)
                .display(ModBlocks.SPINEL_GOLEM_BLOCK,
                        Component.translatable("advancements.coread.core.summon_golem_with_armor.title"),
                        Component.translatable("advancements.coread.core.summon_golem_with_armor.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("summoned_chalcedony_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.CHALCEDONY_GOLEM), fullChalcedonyArmor))
                .addCriterion("summoned_garnet_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.GARNET_GOLEM), fullGarnetArmor))
                .addCriterion("summoned_jade_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.JADE_GOLEM), fullJadeArmor))
                .addCriterion("summoned_jasper_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.JASPER_GOLEM), fullJasperArmor))
                .addCriterion("summoned_onyx_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.ONYX_GOLEM), fullOnyxArmor))
                .addCriterion("summoned_opal_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.OPAL_GOLEM), fullOpalArmor))
                .addCriterion("summoned_ruby_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.RUBY_GOLEM), fullRubyArmor))
                .addCriterion("summoned_sapphire_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.SAPPHIRE_GOLEM), fullSapphireArmor))
                .addCriterion("summoned_spinel_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.SPINEL_GOLEM), fullSpinelArmor))
                .addCriterion("summoned_tigers_eye_golem", TriggerHelper.summonedEntityWithItems(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.TIGERS_EYE_GOLEM), fullTigersEyeArmor))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, ModDataGenerator.getIdentifierString("summon_golem_with_armor"));

        AdvancementHolder killGolem = Advancement.Builder.advancement()
                .parent(summonGolem)
                .display(ModItems.TIGERS_EYE_GOLEM_SPAWN_EGG,
                        Component.translatable("advancements.coread.core.kill_golem.title"),
                        Component.translatable("advancements.coread.core.kill_golem.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("killed_chalcedony_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.CHALCEDONY_GOLEM)))
                .addCriterion("killed_garnet_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.GARNET_GOLEM)))
                .addCriterion("killed_jade_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.JADE_GOLEM)))
                .addCriterion("killed_jasper_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.JASPER_GOLEM)))
                .addCriterion("killed_onyx_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.ONYX_GOLEM)))
                .addCriterion("killed_opal_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.OPAL_GOLEM)))
                .addCriterion("killed_ruby_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.RUBY_GOLEM)))
                .addCriterion("killed_sapphire_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.SAPPHIRE_GOLEM)))
                .addCriterion("killed_spinel_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.SPINEL_GOLEM)))
                .addCriterion("killed_tigers_eye_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.TIGERS_EYE_GOLEM)))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, ModDataGenerator.getIdentifierString("kill_golem"));

        AdvancementHolder killAllGolems = Advancement.Builder.advancement()
                .parent(killGolem)
                .display(ModItems.ONYX_GOLEM_SPAWN_EGG,
                        Component.translatable("advancements.coread.core.kill_all_golems.title"),
                        Component.translatable("advancements.coread.core.kill_all_golems.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false)
                .addCriterion("killed_chalcedony_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.CHALCEDONY_GOLEM)))
                .addCriterion("killed_garnet_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.GARNET_GOLEM)))
                .addCriterion("killed_jade_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.JADE_GOLEM)))
                .addCriterion("killed_jasper_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.JASPER_GOLEM)))
                .addCriterion("killed_onyx_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.ONYX_GOLEM)))
                .addCriterion("killed_opal_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.OPAL_GOLEM)))
                .addCriterion("killed_ruby_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.RUBY_GOLEM)))
                .addCriterion("killed_sapphire_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.SAPPHIRE_GOLEM)))
                .addCriterion("killed_spinel_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.SPINEL_GOLEM)))
                .addCriterion("killed_tigers_eye_golem", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityHolderLookup, ModEntities.TIGERS_EYE_GOLEM)))
                .requirements(AdvancementRequirements.Strategy.AND)
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(consumer, ModDataGenerator.getIdentifierString("kill_all_golems"));
    }

    private static ContextAwarePredicate hasArmor(HolderGetter<Item> itemHolderLookup, ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots) {
        return hasArmor(new ItemPredicate.Builder().of(itemHolderLookup, helmet),
                new ItemPredicate.Builder().of(itemHolderLookup, chestplate),
                new ItemPredicate.Builder().of(itemHolderLookup, leggings),
                new ItemPredicate.Builder().of(itemHolderLookup, boots));
    }

    private static ContextAwarePredicate hasArmor(ItemPredicate.Builder helmet, ItemPredicate.Builder chestplate, ItemPredicate.Builder leggings, ItemPredicate.Builder boots) {
        return ContextAwarePredicate.create(
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().head(helmet))).build(),
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().chest(chestplate))).build(),
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().legs(leggings))).build(),
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().feet(boots))).build()
        );
    }
}
