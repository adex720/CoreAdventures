package com.adex.block;

import com.adex.CoreAdventures;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {

    public static final BlockSetType JUNIPER = register(new BlockSetTypeBuilder().pressurePlateActivationRule(BlockSetType.PressurePlateSensitivity.MOBS), "juniper");

    private static BlockSetType register(BlockSetTypeBuilder builder, String name) {
        return builder.register(Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize(){
    }
}
