package com.adex.datagen;

import com.adex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> FOOT_ARMOR = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("foot_armor"));
    public static final TagKey<Item> LEG_ARMOR = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("leg_armor"));
    public static final TagKey<Item> CHEST_ARMOR = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("chest_armor"));
    public static final TagKey<Item> HEAD_ARMOR = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("head_armor"));

    public static final TagKey<Item> FOOT_ARMOR_ENCHANTABLE = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("enchantable/foot_armor"));
    public static final TagKey<Item> LEG_ARMOR_ENCHANTABLE = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("enchantable/leg_armor"));
    public static final TagKey<Item> CHEST_ARMOR_ENCHANTABLE = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("enchantable/chest_armor"));
    public static final TagKey<Item> HEAD_ARMOR_ENCHANTABLE = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("enchantable/head_armor"));

    public static final TagKey<Item> ARMOR_ENCHANTABLE = TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("enchantable/armor"));

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        valueLookupBuilder(FOOT_ARMOR)
                .add(ModItems.CHALCEDONY_BOOTS, ModItems.GARNET_BOOTS, ModItems.JADE_BOOTS, ModItems.JASPER_BOOTS,
                        ModItems.ONYX_BOOTS, ModItems.OPAL_BOOTS, ModItems.RUBY_BOOTS, ModItems.SAPPHIRE_BOOTS,
                        ModItems.SPINEL_BOOTS, ModItems.TIGERS_EYE_BOOTS, ModItems.GEM_BOOTS)
                .setReplace(false);

        valueLookupBuilder(LEG_ARMOR)
                .add(ModItems.CHALCEDONY_LEGGINGS, ModItems.GARNET_LEGGINGS, ModItems.JADE_LEGGINGS, ModItems.JASPER_LEGGINGS,
                        ModItems.ONYX_LEGGINGS, ModItems.OPAL_LEGGINGS, ModItems.RUBY_LEGGINGS, ModItems.SAPPHIRE_LEGGINGS,
                        ModItems.SPINEL_LEGGINGS, ModItems.TIGERS_EYE_LEGGINGS, ModItems.GEM_LEGGINGS)
                .setReplace(false);

        valueLookupBuilder(CHEST_ARMOR)
                .add(ModItems.CHALCEDONY_CHESTPLATE, ModItems.GARNET_CHESTPLATE, ModItems.JADE_CHESTPLATE, ModItems.JASPER_CHESTPLATE,
                        ModItems.ONYX_CHESTPLATE, ModItems.OPAL_CHESTPLATE, ModItems.RUBY_CHESTPLATE, ModItems.SAPPHIRE_CHESTPLATE,
                        ModItems.SPINEL_CHESTPLATE, ModItems.TIGERS_EYE_CHESTPLATE, ModItems.GEM_CHESTPLATE)
                .setReplace(false);

        valueLookupBuilder(HEAD_ARMOR)
                .add(ModItems.CHALCEDONY_HELMET, ModItems.GARNET_HELMET, ModItems.JADE_HELMET, ModItems.JASPER_HELMET,
                        ModItems.ONYX_HELMET, ModItems.OPAL_HELMET, ModItems.RUBY_HELMET, ModItems.SAPPHIRE_HELMET,
                        ModItems.SPINEL_HELMET, ModItems.TIGERS_EYE_HELMET, ModItems.GEM_HELMET, ModItems.LAVA_GOGGLES)
                .setReplace(false);

        valueLookupBuilder(FOOT_ARMOR_ENCHANTABLE)
                .addTag(FOOT_ARMOR)
                .setReplace(false);

        valueLookupBuilder(LEG_ARMOR_ENCHANTABLE)
                .addTag(LEG_ARMOR)
                .setReplace(false);

        valueLookupBuilder(CHEST_ARMOR_ENCHANTABLE)
                .addTag(CHEST_ARMOR)
                .setReplace(false);

        valueLookupBuilder(HEAD_ARMOR_ENCHANTABLE)
                .addTag(HEAD_ARMOR)
                .setReplace(false);

        valueLookupBuilder(ARMOR_ENCHANTABLE)
                .addTag(FOOT_ARMOR_ENCHANTABLE)
                .addTag(LEG_ARMOR_ENCHANTABLE)
                .addTag(CHEST_ARMOR_ENCHANTABLE)
                .addTag(HEAD_ARMOR_ENCHANTABLE)
                .setReplace(false);
    }
}
