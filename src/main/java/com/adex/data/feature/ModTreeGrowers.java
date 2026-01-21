package com.adex.data.feature;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {

    public static final TreeGrower JUNIPER = new TreeGrower("juniper", 0.0f, Optional.empty(), Optional.empty(), Optional.of(ModFeatures.JUNIPER), Optional.empty(), Optional.empty(), Optional.empty());

    public static void initialize() {
    }
}
