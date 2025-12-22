package com.adex.dimension;

import com.adex.CoreAdventures;
import com.adex.mixin.PortalShapeAccessor;
import com.adex.tag.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class ModDimensions {

    public static final ResourceKey<Level> CORE = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "core"));

    public static void initialize() {
        PortalShapeAccessor.setFrame((state, getter, pos) -> state.is(ModTags.PORTAL_BLOCKS));
    }

}
