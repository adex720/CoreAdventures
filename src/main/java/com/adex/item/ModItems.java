package com.adex.item;

import com.adex.CoreAdventures;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

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


    public static Item register(String name, Item.Properties settings) {
        return register(name, Item::new, settings);
    }

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
        Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
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
        });
    }

}
