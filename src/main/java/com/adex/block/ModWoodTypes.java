package com.adex.block;

import com.adex.CoreAdventures;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {

    public static final WoodType JUNIPER = register(new WoodTypeBuilder(), "juniper", ModBlockSetTypes.JUNIPER);

    private static WoodType register(WoodTypeBuilder builder, String name, BlockSetType type) {
        return builder.register(Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name), type);
    }

    public static void initialize() {
    }
}
