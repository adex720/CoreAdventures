package com.adex.item.armor;

import com.adex.CoreAdventures;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.stream.Stream;

public class AttributeModifyingArmor extends Item {

    public static final Identifier ARMOR_MODIFIER = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "armor_modifier");

    private final List<Holder<Attribute>> attributes;
    private final List<Double> amounts;

    public AttributeModifyingArmor(Properties properties, Holder<Attribute> attribute, double amount) {
        this(properties, List.of(attribute), List.of(amount));
    }

    public AttributeModifyingArmor(Properties properties, List<Holder<Attribute>> attributes, List<Double> amounts) {
        super(properties);
        this.attributes = attributes;
        this.amounts = amounts;
    }

    public static void armorTick(ServerPlayer player) {
        removeArmorModifiers(player);

        Stream.of(player.getItemBySlot(EquipmentSlot.FEET).getItem(),
                        player.getItemBySlot(EquipmentSlot.LEGS).getItem(),
                        player.getItemBySlot(EquipmentSlot.CHEST).getItem(),
                        player.getItemBySlot(EquipmentSlot.HEAD).getItem())
                .filter(item -> item instanceof AttributeModifyingArmor)
                .forEach(item -> ((AttributeModifyingArmor) item).addModifier(player));
    }

    public void addModifier(ServerPlayer player) {
        for (int i = 0; i < attributes.size(); i++) {
            Holder<Attribute> attribute = attributes.get(i);
            double amount = amounts.get(i);

            AttributeInstance instance = player.getAttribute(attribute);
            if (instance == null) return;

            AttributeModifier modifier = instance.getModifier(ARMOR_MODIFIER);
            double newAmount;

            if (modifier == null) {
                newAmount = amount;
            } else {
                newAmount = amount + modifier.amount();
                instance.removeModifier(ARMOR_MODIFIER);
            }

            instance.addTransientModifier(new AttributeModifier(ARMOR_MODIFIER, newAmount, AttributeModifier.Operation.ADD_VALUE));
        }
    }

    public static void removeArmorModifiers(ServerPlayer player) {
        for (AttributeInstance instance : player.getAttributes().getSyncableAttributes()) {
            instance.removeModifier(ARMOR_MODIFIER);
        }
    }
}
