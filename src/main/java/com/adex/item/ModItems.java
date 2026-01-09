package com.adex.item;

import com.adex.CoreAdventures;
import com.adex.entity.ModEntities;
import com.adex.item.armor.ModArmorMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Function;

public class ModItems {

    public static final Item CHALCEDONY = register("chalcedony", new Item.Properties());
    public static final Item GARNET = register("garnet", new Item.Properties());
    public static final Item JADE = register("jade", new Item.Properties());
    public static final Item JASPER = register("jasper", new Item.Properties());
    public static final Item ONYX = register("onyx", new Item.Properties());
    public static final Item OPAL = register("opal", new Item.Properties());
    public static final Item RUBY = register("ruby", new Item.Properties());
    public static final Item SAPPHIRE = register("sapphire", new Item.Properties());
    public static final Item SPINEL = register("spinel", new Item.Properties());
    public static final Item TIGERS_EYE = register("tigers_eye", new Item.Properties());


    public static final Item BLUE_GEM_MIXTURE = register("blue_gem_mixture", new Item.Properties());
    public static final Item RED_GEM_MIXTURE = register("red_gem_mixture", new Item.Properties());
    public static final Item SHINY_GEM_MIXTURE = register("shiny_gem_mixture", new Item.Properties());


    public static final Item CHALCEDONY_HELMET = registerHelmet("chalcedony_helmet", ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);
    public static final Item CHALCEDONY_CHESTPLATE = registerChestplate("chalcedony_chestplate", ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);
    public static final Item CHALCEDONY_LEGGINGS = registerLeggings("chalcedony_leggings", ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);
    public static final Item CHALCEDONY_BOOTS = registerBoots("chalcedony_boots", ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);

    public static final Item GARNET_HELMET = registerHelmet("garnet_helmet", ModArmorMaterials.GARNET_ARMOR_MATERIAL);
    public static final Item GARNET_CHESTPLATE = registerChestplate("garnet_chestplate", ModArmorMaterials.GARNET_ARMOR_MATERIAL);
    public static final Item GARNET_LEGGINGS = registerLeggings("garnet_leggings", ModArmorMaterials.GARNET_ARMOR_MATERIAL);
    public static final Item GARNET_BOOTS = registerBoots("garnet_boots", ModArmorMaterials.GARNET_ARMOR_MATERIAL);

    public static final Item JADE_HELMET = registerHelmet("jade_helmet", ModArmorMaterials.JADE_ARMOR_MATERIAL);
    public static final Item JADE_CHESTPLATE = registerChestplate("jade_chestplate", ModArmorMaterials.JADE_ARMOR_MATERIAL);
    public static final Item JADE_LEGGINGS = registerLeggings("jade_leggings", ModArmorMaterials.JADE_ARMOR_MATERIAL);
    public static final Item JADE_BOOTS = registerBoots("jade_boots", ModArmorMaterials.JADE_ARMOR_MATERIAL);

    public static final Item JASPER_HELMET = registerHelmet("jasper_helmet", ModArmorMaterials.JASPER_ARMOR_MATERIAL);
    public static final Item JASPER_CHESTPLATE = registerChestplate("jasper_chestplate", ModArmorMaterials.JASPER_ARMOR_MATERIAL);
    public static final Item JASPER_LEGGINGS = registerLeggings("jasper_leggings", ModArmorMaterials.JASPER_ARMOR_MATERIAL);
    public static final Item JASPER_BOOTS = registerBoots("jasper_boots", ModArmorMaterials.JASPER_ARMOR_MATERIAL);

    public static final Item ONYX_HELMET = registerHelmet("onyx_helmet", ModArmorMaterials.ONYX_ARMOR_MATERIAL);
    public static final Item ONYX_CHESTPLATE = registerChestplate("onyx_chestplate", ModArmorMaterials.ONYX_ARMOR_MATERIAL);
    public static final Item ONYX_LEGGINGS = registerLeggings("onyx_leggings", ModArmorMaterials.ONYX_ARMOR_MATERIAL);
    public static final Item ONYX_BOOTS = registerBoots("onyx_boots", ModArmorMaterials.ONYX_ARMOR_MATERIAL);

    public static final Item OPAL_HELMET = registerHelmet("opal_helmet", ModArmorMaterials.OPAL_ARMOR_MATERIAL);
    public static final Item OPAL_CHESTPLATE = registerChestplate("opal_chestplate", ModArmorMaterials.OPAL_ARMOR_MATERIAL);
    public static final Item OPAL_LEGGINGS = registerLeggings("opal_leggings", ModArmorMaterials.OPAL_ARMOR_MATERIAL);
    public static final Item OPAL_BOOTS = registerBoots("opal_boots", ModArmorMaterials.OPAL_ARMOR_MATERIAL);

    public static final Item RUBY_HELMET = registerHelmet("ruby_helmet", ModArmorMaterials.RUBY_ARMOR_MATERIAL);
    public static final Item RUBY_CHESTPLATE = registerChestplate("ruby_chestplate", ModArmorMaterials.RUBY_ARMOR_MATERIAL);
    public static final Item RUBY_LEGGINGS = registerLeggings("ruby_leggings", ModArmorMaterials.RUBY_ARMOR_MATERIAL);
    public static final Item RUBY_BOOTS = registerBoots("ruby_boots", ModArmorMaterials.RUBY_ARMOR_MATERIAL);

    public static final Item SAPPHIRE_HELMET = registerHelmet("sapphire_helmet", ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL);
    public static final Item SAPPHIRE_CHESTPLATE = registerChestplate("sapphire_chestplate", ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL);
    public static final Item SAPPHIRE_LEGGINGS = registerLeggings("sapphire_leggings", ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL);
    public static final Item SAPPHIRE_BOOTS = registerBoots("sapphire_boots", ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL);

    public static final Item SPINEL_HELMET = registerHelmet("spinel_helmet", ModArmorMaterials.SPINEL_ARMOR_MATERIAL);
    public static final Item SPINEL_CHESTPLATE = registerChestplate("spinel_chestplate", ModArmorMaterials.SPINEL_ARMOR_MATERIAL);
    public static final Item SPINEL_LEGGINGS = registerLeggings("spinel_leggings", ModArmorMaterials.SPINEL_ARMOR_MATERIAL);
    public static final Item SPINEL_BOOTS = registerBoots("spinel_boots", ModArmorMaterials.SPINEL_ARMOR_MATERIAL);

    public static final Item TIGERS_EYE_HELMET = registerHelmet("tigers_eye_helmet", ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);
    public static final Item TIGERS_EYE_CHESTPLATE = registerChestplate("tigers_eye_chestplate", ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);
    public static final Item TIGERS_EYE_LEGGINGS = registerLeggings("tigers_eye_leggings", ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);
    public static final Item TIGERS_EYE_BOOTS = registerBoots("tigers_eye_boots", ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);

    public static final Item GEM_HELMET = registerHelmet("gem_helmet", ModArmorMaterials.GEM_ARMOR_MATERIAL);
    public static final Item GEM_CHESTPLATE = registerChestplate("gem_chestplate", ModArmorMaterials.GEM_ARMOR_MATERIAL);
    public static final Item GEM_LEGGINGS = registerLeggings("gem_leggings", ModArmorMaterials.GEM_ARMOR_MATERIAL);
    public static final Item GEM_BOOTS = registerBoots("gem_boots", ModArmorMaterials.GEM_ARMOR_MATERIAL);


    public static final Item CHALCEDONY_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.CHALCEDONY_GOLEM);
    public static final Item GARNET_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.GARNET_GOLEM);
    public static final Item JADE_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.JADE_GOLEM);
    public static final Item JASPER_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.JASPER_GOLEM);
    public static final Item ONYX_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.ONYX_GOLEM);
    public static final Item OPAL_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.OPAL_GOLEM);
    public static final Item RUBY_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.RUBY_GOLEM);
    public static final Item SAPPHIRE_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.SAPPHIRE_GOLEM);
    public static final Item SPINEL_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.SPINEL_GOLEM);
    public static final Item TIGERS_EYE_GOLEM_SPAWN_EGG = registerSpawnEgg(ModEntities.TIGERS_EYE_GOLEM);

    public static Item register(String name, Item.Properties settings) {
        return register(name, Item::new, settings);
    }

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
        Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static Item registerSpawnEgg(EntityType<?> entityType) {
        return register(EntityType.getKey(entityType).getPath() + "_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(entityType));
    }

    public static Item registerHelmet(String name, ArmorMaterial material) {
        return register(name, new Item.Properties().humanoidArmor(material, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(material.durability())));
    }

    public static Item registerChestplate(String name, ArmorMaterial material) {
        return register(name, new Item.Properties().humanoidArmor(material, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(material.durability())));
    }

    public static Item registerLeggings(String name, ArmorMaterial material) {
        return register(name, new Item.Properties().humanoidArmor(material, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(material.durability())));
    }

    public static Item registerBoots(String name, ArmorMaterial material) {
        return register(name, new Item.Properties().humanoidArmor(material, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(material.durability())));
    }

    public static void initialize() {
        addToItemGroups();
    }

    private static void addToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register((itemGroup) -> {
            itemGroup.accept(CHALCEDONY);
            itemGroup.accept(GARNET);
            itemGroup.accept(JADE);
            itemGroup.accept(JASPER);
            itemGroup.accept(ONYX);
            itemGroup.accept(OPAL);
            itemGroup.accept(RUBY);
            itemGroup.accept(SAPPHIRE);
            itemGroup.accept(SPINEL);
            itemGroup.accept(TIGERS_EYE);

            itemGroup.accept(BLUE_GEM_MIXTURE);
            itemGroup.accept(RED_GEM_MIXTURE);
            itemGroup.accept(SHINY_GEM_MIXTURE);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register((itemGroup) -> {
            itemGroup.accept(CHALCEDONY_HELMET);
            itemGroup.accept(CHALCEDONY_CHESTPLATE);
            itemGroup.accept(CHALCEDONY_LEGGINGS);
            itemGroup.accept(CHALCEDONY_BOOTS);

            itemGroup.accept(GARNET_HELMET);
            itemGroup.accept(GARNET_CHESTPLATE);
            itemGroup.accept(GARNET_LEGGINGS);
            itemGroup.accept(GARNET_BOOTS);

            itemGroup.accept(JADE_HELMET);
            itemGroup.accept(JADE_CHESTPLATE);
            itemGroup.accept(JADE_LEGGINGS);
            itemGroup.accept(JADE_BOOTS);

            itemGroup.accept(JASPER_HELMET);
            itemGroup.accept(JASPER_CHESTPLATE);
            itemGroup.accept(JASPER_LEGGINGS);
            itemGroup.accept(JASPER_BOOTS);

            itemGroup.accept(ONYX_HELMET);
            itemGroup.accept(ONYX_CHESTPLATE);
            itemGroup.accept(ONYX_LEGGINGS);
            itemGroup.accept(ONYX_BOOTS);

            itemGroup.accept(OPAL_HELMET);
            itemGroup.accept(OPAL_CHESTPLATE);
            itemGroup.accept(OPAL_LEGGINGS);
            itemGroup.accept(OPAL_BOOTS);

            itemGroup.accept(RUBY_HELMET);
            itemGroup.accept(RUBY_CHESTPLATE);
            itemGroup.accept(RUBY_LEGGINGS);
            itemGroup.accept(RUBY_BOOTS);

            itemGroup.accept(SAPPHIRE_HELMET);
            itemGroup.accept(SAPPHIRE_CHESTPLATE);
            itemGroup.accept(SAPPHIRE_LEGGINGS);
            itemGroup.accept(SAPPHIRE_BOOTS);

            itemGroup.accept(SPINEL_HELMET);
            itemGroup.accept(SPINEL_CHESTPLATE);
            itemGroup.accept(SPINEL_LEGGINGS);
            itemGroup.accept(SPINEL_BOOTS);

            itemGroup.accept(TIGERS_EYE_HELMET);
            itemGroup.accept(TIGERS_EYE_CHESTPLATE);
            itemGroup.accept(TIGERS_EYE_LEGGINGS);
            itemGroup.accept(TIGERS_EYE_BOOTS);

            itemGroup.accept(GEM_HELMET);
            itemGroup.accept(GEM_CHESTPLATE);
            itemGroup.accept(GEM_LEGGINGS);
            itemGroup.accept(GEM_BOOTS);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register((itemGroup) -> {
            itemGroup.accept(CHALCEDONY_GOLEM_SPAWN_EGG);
            itemGroup.accept(GARNET_GOLEM_SPAWN_EGG);
            itemGroup.accept(JADE_GOLEM_SPAWN_EGG);
            itemGroup.accept(JASPER_GOLEM_SPAWN_EGG);
            itemGroup.accept(ONYX_GOLEM_SPAWN_EGG);
            itemGroup.accept(OPAL_GOLEM_SPAWN_EGG);
            itemGroup.accept(RUBY_GOLEM_SPAWN_EGG);
            itemGroup.accept(SAPPHIRE_GOLEM_SPAWN_EGG);
            itemGroup.accept(SPINEL_GOLEM_SPAWN_EGG);
            itemGroup.accept(TIGERS_EYE_GOLEM_SPAWN_EGG);
        });
    }

}
