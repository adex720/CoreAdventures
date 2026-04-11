package com.adex.item;

import com.adex.CoreAdventures;
import com.adex.entity.ModEntities;
import com.adex.item.armor.ModArmorMaterials;
import com.adex.item.armor.ModTrimMaterials;
import com.adex.item.tool.ModToolMaterials;
import com.adex.mixin.DataComponentMapBuilderAccessor;
import com.adex.mixin.ItemPropertiesAccessor;
import com.adex.sound.ModJukeboxSounds;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.function.Function;

public class ModItems {

    public static final Item CHALCEDONY = registerWithTrimMaterial("chalcedony", new Item.Properties(), ModTrimMaterials.CHALCEDONY);
    public static final Item GARNET = registerWithTrimMaterial("garnet", new Item.Properties(), ModTrimMaterials.GARNET);
    public static final Item JADE = registerWithTrimMaterial("jade", new Item.Properties(), ModTrimMaterials.JADE);
    public static final Item JASPER = registerWithTrimMaterial("jasper", new Item.Properties(), ModTrimMaterials.JASPER);
    public static final Item ONYX = registerWithTrimMaterial("onyx", new Item.Properties(), ModTrimMaterials.ONYX);
    public static final Item OPAL = registerWithTrimMaterial("opal", new Item.Properties(), ModTrimMaterials.OPAL);
    public static final Item RUBY = registerWithTrimMaterial("ruby", new Item.Properties(), ModTrimMaterials.RUBY);
    public static final Item SAPPHIRE = registerWithTrimMaterial("sapphire", new Item.Properties(), ModTrimMaterials.SAPPHIRE);
    public static final Item SPINEL = registerWithTrimMaterial("spinel", new Item.Properties(), ModTrimMaterials.SPINEL);
    public static final Item TIGERS_EYE = registerWithTrimMaterial("tigers_eye", new Item.Properties(), ModTrimMaterials.TIGERS_EYE);


    public static final Item BLUE_GEM_MIXTURE = registerWithTrimMaterial("blue_gem_mixture", new Item.Properties(), ModTrimMaterials.BLUE_GEM_MIXTURE);
    public static final Item RED_GEM_MIXTURE = registerWithTrimMaterial("red_gem_mixture", new Item.Properties(), ModTrimMaterials.RED_GEM_MIXTURE);
    public static final Item SHINY_GEM_MIXTURE = registerWithTrimMaterial("shiny_gem_mixture", new Item.Properties(), ModTrimMaterials.SHINY_GEM_MIXTURE);


    public static final Item GEM_SWORD = register("gem_sword", new Item.Properties().sword(ModToolMaterials.GEM_TOOL_MATERIAL, 3.0f, -2.4f));
    public static final Item GEM_SPEAR = register("gem_spear", new Item.Properties().spear(ModToolMaterials.GEM_TOOL_MATERIAL, 1.15f, 1.35f, 0.35f, 2.0f, 6.5f, 5.1f, 5.1f, 7.5f, 4.6f));
    public static final Item GEM_SHOVEL = register("gem_shovel", new Item.Properties().shovel(ModToolMaterials.GEM_TOOL_MATERIAL, 1.5f, -3.0f));
    public static final Item GEM_PICKAXE = register("gem_pickaxe", new Item.Properties().pickaxe(ModToolMaterials.GEM_TOOL_MATERIAL, 1.0f, -2.8f));
    public static final Item GEM_AXE = register("gem_axe", new Item.Properties().axe(ModToolMaterials.GEM_TOOL_MATERIAL, 5.0f, -3.0f));
    public static final Item GEM_HOE = register("gem_hoe", new Item.Properties().hoe(ModToolMaterials.GEM_TOOL_MATERIAL, -3.0f, 0.0f));


    public static final Item CHALCEDONY_HELMET = registerHelmet("chalcedony_helmet", ModArmorMaterials.chalcedonyProtectiveArmor(), ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);
    public static final Item CHALCEDONY_CHESTPLATE = registerChestplate("chalcedony_chestplate", ModArmorMaterials.chalcedonyProtectiveArmor(), ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);
    public static final Item CHALCEDONY_LEGGINGS = registerLeggings("chalcedony_leggings", ModArmorMaterials.chalcedonyProtectiveArmor(), ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);
    public static final Item CHALCEDONY_BOOTS = registerBoots("chalcedony_boots", ModArmorMaterials.chalcedonyProtectiveArmor(), ModArmorMaterials.CHALCEDONY_ARMOR_MATERIAL);

    public static final Item GARNET_HELMET = registerHelmet("garnet_helmet", ModArmorMaterials.GARNET_ARMOR_MATERIAL);
    public static final Item GARNET_CHESTPLATE = registerChestplate("garnet_chestplate", ModArmorMaterials.GARNET_ARMOR_MATERIAL);
    public static final Item GARNET_LEGGINGS = registerLeggings("garnet_leggings", ModArmorMaterials.GARNET_ARMOR_MATERIAL);
    public static final Item GARNET_BOOTS = registerBoots("garnet_boots", ModArmorMaterials.GARNET_ARMOR_MATERIAL);

    public static final Item JADE_HELMET = registerHelmet("jade_helmet", ModArmorMaterials.JADE_ARMOR_MATERIAL, ModArmorMaterials::jadeArmorAttributes);
    public static final Item JADE_CHESTPLATE = registerChestplate("jade_chestplate", ModArmorMaterials.JADE_ARMOR_MATERIAL, ModArmorMaterials::jadeArmorAttributes);
    public static final Item JADE_LEGGINGS = registerLeggings("jade_leggings", ModArmorMaterials.JADE_ARMOR_MATERIAL, ModArmorMaterials::jadeArmorAttributes);
    public static final Item JADE_BOOTS = registerBoots("jade_boots", ModArmorMaterials.JADE_ARMOR_MATERIAL, ModArmorMaterials::jadeArmorAttributes);

    public static final Item JASPER_HELMET = registerHelmet("jasper_helmet", ModArmorMaterials.jasperProtectiveArmor(), ModArmorMaterials.JASPER_ARMOR_MATERIAL);
    public static final Item JASPER_CHESTPLATE = registerChestplate("jasper_chestplate", ModArmorMaterials.jasperProtectiveArmor(), ModArmorMaterials.JASPER_ARMOR_MATERIAL);
    public static final Item JASPER_LEGGINGS = registerLeggings("jasper_leggings", ModArmorMaterials.jasperProtectiveArmor(), ModArmorMaterials.JASPER_ARMOR_MATERIAL);
    public static final Item JASPER_BOOTS = registerBoots("jasper_boots", ModArmorMaterials.jasperProtectiveArmor(), ModArmorMaterials.JASPER_ARMOR_MATERIAL);

    public static final Item ONYX_HELMET = registerHelmet("onyx_helmet", ModArmorMaterials.ONYX_ARMOR_MATERIAL, ModArmorMaterials::onyxArmorAttributes);
    public static final Item ONYX_CHESTPLATE = registerChestplate("onyx_chestplate", ModArmorMaterials.ONYX_ARMOR_MATERIAL, ModArmorMaterials::onyxArmorAttributes);
    public static final Item ONYX_LEGGINGS = registerLeggings("onyx_leggings", ModArmorMaterials.ONYX_ARMOR_MATERIAL, ModArmorMaterials::onyxArmorAttributes);
    public static final Item ONYX_BOOTS = registerBoots("onyx_boots", ModArmorMaterials.ONYX_ARMOR_MATERIAL, ModArmorMaterials::onyxArmorAttributes);

    public static final Item OPAL_HELMET = registerHelmet("opal_helmet", ModArmorMaterials.opalPotionResistance(), ModArmorMaterials.OPAL_ARMOR_MATERIAL);
    public static final Item OPAL_CHESTPLATE = registerChestplate("opal_chestplate", ModArmorMaterials.opalPotionResistance(), ModArmorMaterials.OPAL_ARMOR_MATERIAL);
    public static final Item OPAL_LEGGINGS = registerLeggings("opal_leggings", ModArmorMaterials.opalPotionResistance(), ModArmorMaterials.OPAL_ARMOR_MATERIAL);
    public static final Item OPAL_BOOTS = registerBoots("opal_boots", ModArmorMaterials.opalPotionResistance(), ModArmorMaterials.OPAL_ARMOR_MATERIAL);

    public static final Item RUBY_HELMET = registerHelmet("ruby_helmet", ModArmorMaterials.rubyHealthRegenerationBoost(), ModArmorMaterials.RUBY_ARMOR_MATERIAL);
    public static final Item RUBY_CHESTPLATE = registerChestplate("ruby_chestplate", ModArmorMaterials.rubyHealthRegenerationBoost(), ModArmorMaterials.RUBY_ARMOR_MATERIAL);
    public static final Item RUBY_LEGGINGS = registerLeggings("ruby_leggings", ModArmorMaterials.rubyHealthRegenerationBoost(), ModArmorMaterials.RUBY_ARMOR_MATERIAL);
    public static final Item RUBY_BOOTS = registerBoots("ruby_boots", ModArmorMaterials.rubyHealthRegenerationBoost(), ModArmorMaterials.RUBY_ARMOR_MATERIAL);

    public static final Item SAPPHIRE_HELMET = registerHelmet("sapphire_helmet", ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ModArmorMaterials::sapphireArmorAttributes);
    public static final Item SAPPHIRE_CHESTPLATE = registerChestplate("sapphire_chestplate", ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ModArmorMaterials::sapphireArmorAttributes);
    public static final Item SAPPHIRE_LEGGINGS = registerLeggings("sapphire_leggings", ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ModArmorMaterials::sapphireArmorAttributes);
    public static final Item SAPPHIRE_BOOTS = registerBoots("sapphire_boots", ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ModArmorMaterials::sapphireArmorAttributes);

    public static final Item SPINEL_HELMET = registerHelmet("spinel_helmet", ModArmorMaterials.spinelProtectiveArmor(), ModArmorMaterials.SPINEL_ARMOR_MATERIAL);
    public static final Item SPINEL_CHESTPLATE = registerChestplate("spinel_chestplate", ModArmorMaterials.spinelProtectiveArmor(), ModArmorMaterials.SPINEL_ARMOR_MATERIAL);
    public static final Item SPINEL_LEGGINGS = registerLeggings("spinel_leggings", ModArmorMaterials.spinelProtectiveArmor(), ModArmorMaterials.SPINEL_ARMOR_MATERIAL);
    public static final Item SPINEL_BOOTS = registerBoots("spinel_boots", ModArmorMaterials.spinelProtectiveArmor(), ModArmorMaterials.SPINEL_ARMOR_MATERIAL);

    public static final Item TIGERS_EYE_HELMET = registerHelmet("tigers_eye_helmet", ModArmorMaterials.tigersEyeProtectiveArmor(), ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);
    public static final Item TIGERS_EYE_CHESTPLATE = registerChestplate("tigers_eye_chestplate", ModArmorMaterials.tigersEyeProtectiveArmor(), ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);
    public static final Item TIGERS_EYE_LEGGINGS = registerLeggings("tigers_eye_leggings", ModArmorMaterials.tigersEyeProtectiveArmor(), ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);
    public static final Item TIGERS_EYE_BOOTS = registerBoots("tigers_eye_boots", ModArmorMaterials.tigersEyeProtectiveArmor(), ModArmorMaterials.TIGERS_EYE_ARMOR_MATERIAL);

    public static final Item GEM_HELMET = registerGemArmor("gem_helmet", ArmorType.HELMET);
    public static final Item GEM_CHESTPLATE = registerGemArmor("gem_chestplate", ArmorType.CHESTPLATE);
    public static final Item GEM_LEGGINGS = registerGemArmor("gem_leggings", ArmorType.LEGGINGS);
    public static final Item GEM_BOOTS = registerGemArmor("gem_boots", ArmorType.BOOTS);

    public static final Item LAVA_GOGGLES = registerHelmet("lava_goggles", ModArmorMaterials.LAVA_GOGGLES_ARMOR_MATERIAL);


    public static final Item JUNIPER_BOAT = register("juniper_boat", properties -> new BoatItem(ModEntities.JUNIPER_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item JUNIPER_CHEST_BOAT = register("juniper_chest_boat", properties -> new BoatItem(ModEntities.JUNIPER_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item SPEED_BOAT = register("speed_boat", properties -> new BoatItem(ModEntities.SPEED_BOAT, properties), new Item.Properties().stacksTo(1));
    public static final Item SPEED_CHEST_BOAT = register("speed_chest_boat", properties -> new BoatItem(ModEntities.SPEED_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));


    public static final Item CHALCEDONY_SHARD = register("chalcedony_shard", new Item.Properties());
    public static final Item GARNET_FRAGMENT = register("garnet_fragment", new Item.Properties());
    public static final Item JASPER_FRAGMENT = register("jasper_fragment", new Item.Properties());
    public static final Item ONYX_FRAGMENT = register("onyx_fragment", new Item.Properties());
    public static final Item OPAL_SHARD = register("opal_shard", new Item.Properties());
    public static final Item SAPPHIRE_FRAGMENT = register("sapphire_fragment", new Item.Properties());
    public static final Item DYNAMITE = register("dynamite", new Item.Properties());


    public static final Item REFUGE_COMPASS = register("refuge_compass", new Item.Properties());


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

    public static final Item TRADER_SENTRY_SPAWN_EGG = registerSpawnEgg(ModEntities.TRADER_SENTRY);
    public static final Item WARRIOR_SENTRY_SPAWN_EGG = registerSpawnEgg(ModEntities.WARRIOR_SENTRY);


    public static final Item MUSIC_DISC_TRUMPET1 = register("music_disc_trumpet1", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(ModJukeboxSounds.TRUMPET1_KEY));
    public static final Item MUSIC_DISC_TROMBONE1 = register("music_disc_trombone1", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(ModJukeboxSounds.TROMBONE1_KEY));
    public static final Item MUSIC_DISC_TUBA1 = register("music_disc_tuba1", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(ModJukeboxSounds.TUBA1_KEY));
    public static final Item MUSIC_DISC_ALTO_SAXOPHONE1 = register("music_disc_alto_saxophone1", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(ModJukeboxSounds.ALTO_SAXOPHONE1_KEY));
    public static final Item MUSIC_DISC_BASS_SAXOPHONE1 = register("music_disc_bass_saxophone1", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(ModJukeboxSounds.BASS_SAXOPHONE1_KEY));
    public static final Item MUSIC_DISC_CONTRABASS1 = register("music_disc_contrabass1", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(ModJukeboxSounds.CONTRABASS1_KEY));
    public static final Item MUSIC_DISC_TIMPANI1 = register("music_disc_timpani1", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(ModJukeboxSounds.TIMPANI1_KEY));

    public static final SplashArrowItem SPLASH_ARROW = (SplashArrowItem) register("splash_arrow", SplashArrowItem::new, new Item.Properties().component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).component(DataComponents.POTION_DURATION_SCALE, 0.125F));

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

    /**
     * Registers the item with a trim material if {@link CoreAdventures#GENERATE_ARMOR_TRIMS} is {@code true},
     * and without if {@code false}.
     */
    public static Item registerWithTrimMaterial(String name, Item.Properties settings, ResourceKey<TrimMaterial> trimMaterial) {
        return CoreAdventures.GENERATE_ARMOR_TRIMS ? register(name, settings.trimMaterial(trimMaterial)) : register(name, settings);
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

    public static Item registerHelmet(String name, Function<Item.Properties, Item> itemFactory, ArmorMaterial material) {
        return register(name, itemFactory, new Item.Properties().humanoidArmor(material, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(material.durability())));
    }

    public static Item registerChestplate(String name, Function<Item.Properties, Item> itemFactory, ArmorMaterial material) {
        return register(name, itemFactory, new Item.Properties().humanoidArmor(material, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(material.durability())));
    }

    public static Item registerLeggings(String name, Function<Item.Properties, Item> itemFactory, ArmorMaterial material) {
        return register(name, itemFactory, new Item.Properties().humanoidArmor(material, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(material.durability())));
    }

    public static Item registerBoots(String name, Function<Item.Properties, Item> itemFactory, ArmorMaterial material) {
        return register(name, itemFactory, new Item.Properties().humanoidArmor(material, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(material.durability())));
    }

    public static Item registerHelmet(String name, ArmorMaterial material, Function<ArmorType, ItemAttributeModifiers> modifiers) {
        return register(name, addAttributeModifiers(new Item.Properties().humanoidArmor(material, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(material.durability())), modifiers.apply(ArmorType.HELMET)));
    }

    public static Item registerChestplate(String name, ArmorMaterial material, Function<ArmorType, ItemAttributeModifiers> modifiers) {
        return register(name, addAttributeModifiers(new Item.Properties().humanoidArmor(material, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(material.durability())), modifiers.apply(ArmorType.CHESTPLATE)));
    }

    public static Item registerLeggings(String name, ArmorMaterial material, Function<ArmorType, ItemAttributeModifiers> modifiers) {
        return register(name, addAttributeModifiers(new Item.Properties().humanoidArmor(material, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(material.durability())), modifiers.apply(ArmorType.LEGGINGS)));
    }

    public static Item registerBoots(String name, ArmorMaterial material, Function<ArmorType, ItemAttributeModifiers> modifiers) {
        return register(name, addAttributeModifiers(new Item.Properties().humanoidArmor(material, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(material.durability())), modifiers.apply(ArmorType.BOOTS)));
    }

    public static Item registerGemArmor(String name, ArmorType armorType) {
        return register(name, ModArmorMaterials.gemProtectiveArmor(), addAttributeModifiers(
                new Item.Properties().humanoidArmor(ModArmorMaterials.GEM_ARMOR_MATERIAL, armorType)
                        .durability(armorType.getDurability(ModArmorMaterials.GEM_ARMOR_MATERIAL.durability()))
                        .component(ModDataComponents.POTION_RESISTANCE, 0.05f)
                        .component(ModDataComponents.REGENERATION_BOOST, 0.05f),
                ModArmorMaterials.gemArmorAttributes(armorType)));
    }

    public static Item.Properties addAttributeModifiers(Item.Properties properties, final ItemAttributeModifiers modifiers) {
        DataComponentInitializers.Initializer<Item> components = ((ItemPropertiesAccessor) properties).coread$getComponents();
        components.andThen((oldComponents, context, key) ->
                properties.attributes(combine(getAttributeModifiers(oldComponents), modifiers)));

        return properties;
    }

    public static ItemAttributeModifiers combine(ItemAttributeModifiers modifiers1, ItemAttributeModifiers modifiers2) {
        for (ItemAttributeModifiers.Entry modifier : modifiers2.modifiers()) {
            modifiers1 = modifiers1.withModifierAdded(modifier.attribute(), modifier.modifier(), modifier.slot());
        }

        return modifiers1;
    }

    public static ItemAttributeModifiers getAttributeModifiers(DataComponentMap.Builder components) {
        Reference2ObjectMap<DataComponentType<?>, Object> map = ((DataComponentMapBuilderAccessor) components).coread$getMap();
        return (ItemAttributeModifiers) map.get(DataComponents.ATTRIBUTE_MODIFIERS);
    }

    public static void initialize() {
        addToItemGroups();
        registerDispenseBehaviors();
    }

    private static void registerDispenseBehaviors() {
        DispenserBlock.registerBehavior(JUNIPER_BOAT, new BoatDispenseItemBehavior(ModEntities.JUNIPER_BOAT));
        DispenserBlock.registerBehavior(JUNIPER_CHEST_BOAT, new BoatDispenseItemBehavior(ModEntities.JUNIPER_CHEST_BOAT));
        DispenserBlock.registerBehavior(SPEED_BOAT, new BoatDispenseItemBehavior(ModEntities.SPEED_BOAT));
        DispenserBlock.registerBehavior(SPEED_CHEST_BOAT, new BoatDispenseItemBehavior(ModEntities.SPEED_CHEST_BOAT));

        DispenserBlock.registerProjectileBehavior(SPLASH_ARROW);
    }

    private static void addToItemGroups() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register((itemGroup) -> {
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

            itemGroup.accept(CHALCEDONY_SHARD);
            itemGroup.accept(GARNET_FRAGMENT);
            itemGroup.accept(JASPER_FRAGMENT);
            itemGroup.accept(ONYX_FRAGMENT);
            itemGroup.accept(OPAL_SHARD);
            itemGroup.accept(SAPPHIRE_FRAGMENT);
            itemGroup.accept(DYNAMITE);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register((itemGroup) -> {
            itemGroup.accept(GEM_SWORD);
            itemGroup.accept(GEM_SPEAR);

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

            itemGroup.accept(LAVA_GOGGLES);

            SplashArrowItem.addToItemGroup(itemGroup);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((itemGroup) -> {
            itemGroup.accept(GEM_SHOVEL);
            itemGroup.accept(GEM_PICKAXE);
            itemGroup.accept(GEM_AXE);
            itemGroup.accept(GEM_HOE);

            itemGroup.accept(JUNIPER_BOAT);
            itemGroup.accept(JUNIPER_CHEST_BOAT);
            itemGroup.accept(SPEED_BOAT);
            itemGroup.accept(SPEED_CHEST_BOAT);

            itemGroup.accept(REFUGE_COMPASS);

            itemGroup.accept(MUSIC_DISC_TRUMPET1);
            itemGroup.accept(MUSIC_DISC_TROMBONE1);
            itemGroup.accept(MUSIC_DISC_TUBA1);
            itemGroup.accept(MUSIC_DISC_ALTO_SAXOPHONE1);
            itemGroup.accept(MUSIC_DISC_BASS_SAXOPHONE1);
            itemGroup.accept(MUSIC_DISC_CONTRABASS1);
            itemGroup.accept(MUSIC_DISC_TIMPANI1);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register((itemGroup) -> {
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

            itemGroup.accept(TRADER_SENTRY_SPAWN_EGG);
            itemGroup.accept(WARRIOR_SENTRY_SPAWN_EGG);
        });
    }

}
