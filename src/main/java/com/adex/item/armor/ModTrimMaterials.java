package com.adex.item.armor;

import com.adex.CoreAdventures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class ModTrimMaterials {

    public static final ResourceKey<TrimMaterial> CHALCEDONY = createKey("chalcedony");
    public static final ResourceKey<TrimMaterial> GARNET = createKey("garnet");
    public static final ResourceKey<TrimMaterial> JADE = createKey("jade");
    public static final ResourceKey<TrimMaterial> JASPER = createKey("jasper");
    public static final ResourceKey<TrimMaterial> ONYX = createKey("onyx");
    public static final ResourceKey<TrimMaterial> OPAL = createKey("opal");
    public static final ResourceKey<TrimMaterial> RUBY = createKey("ruby");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = createKey("sapphire");
    public static final ResourceKey<TrimMaterial> SPINEL = createKey("spinel");
    public static final ResourceKey<TrimMaterial> TIGERS_EYE = createKey("tigers_eye");
    public static final ResourceKey<TrimMaterial> BLUE_GEM_MIXTURE = createKey("blue_gem_mixture");
    public static final ResourceKey<TrimMaterial> RED_GEM_MIXTURE = createKey("red_gem_mixture");
    public static final ResourceKey<TrimMaterial> SHINY_GEM_MIXTURE = createKey("shiny_gem_mixture");

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
    }
}
