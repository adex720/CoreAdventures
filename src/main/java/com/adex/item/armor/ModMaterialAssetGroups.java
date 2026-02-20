package com.adex.item.armor;

import com.adex.CoreAdventures;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;

import java.util.Map;

public class ModMaterialAssetGroups {

    public static final MaterialAssetGroup CHALCEDONY = create("chalcedony");
    public static final MaterialAssetGroup GARNET = create("garnet");
    public static final MaterialAssetGroup JADE = create("jade");
    public static final MaterialAssetGroup JASPER = create("jasper");
    public static final MaterialAssetGroup ONYX = create("onyx");
    public static final MaterialAssetGroup OPAL = create("opal");
    public static final MaterialAssetGroup RUBY = create("ruby");
    public static final MaterialAssetGroup SAPPHIRE = create("sapphire");
    public static final MaterialAssetGroup SPINEL = create("spinel");
    public static final MaterialAssetGroup TIGERS_EYE = create("tigers_eye");
    public static final MaterialAssetGroup BLUE_GEM_MIXTURE = create("blue_gem_mixture");
    public static final MaterialAssetGroup RED_GEM_MIXTURE = create("red_gem_mixture");
    public static final MaterialAssetGroup SHINY_GEM_MIXTURE = create("shiny_gem_mixture");

    public static MaterialAssetGroup create(String string) {
        if (!CoreAdventures.GENERATE_ARMOR_TRIMS) return null;

        return new MaterialAssetGroup(new MaterialAssetGroup.AssetInfo(string), Map.of());
    }

    public static void initialize() {
    }

}
