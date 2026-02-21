package com.adex.item.armor;

import com.adex.CoreAdventures;
import com.adex.mixin.SpawnArmorTrimsCommandAccessor;
import com.adex.util.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModTrimMaterials {

    private static final ArrayList<ResourceKey<TrimMaterial>> TRIM_MATERIALS = new ArrayList<>();
    public static final HashMap<String, Identifier> BY_NAME = new HashMap<>();

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
        if (!CoreAdventures.GENERATE_ARMOR_TRIMS) return null;

        Identifier identifier = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name);
        BY_NAME.put(name, identifier.withPrefix("trims/color_palettes/"));

        ResourceKey<TrimMaterial> key = ResourceKey.create(Registries.TRIM_MATERIAL, identifier);
        TRIM_MATERIALS.add(key);
        return key;
    }

    public static void initialize() {
        List<ResourceKey<TrimMaterial>> materials = Util.combine(SpawnArmorTrimsCommandAccessor.coread$getTrimMaterials(), TRIM_MATERIALS);
        SpawnArmorTrimsCommandAccessor.coread$setTrimMaterials(materials);
        SpawnArmorTrimsCommandAccessor.coread$setTrimMaterialOrder(net.minecraft.util.Util.createIndexLookup(materials));
    }

}
