package com.adex.entity.poi;

import com.adex.CoreAdventures;
import com.adex.block.ModBlocks;
import com.adex.mixin.PoiTypesAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class ModPoiTypes {

    public static final ResourceKey<PoiType> CORE_PORTAL = register("core_portal", ModBlocks.CORE_PORTAL_BLOCK, 0, 1);

    private static ResourceKey<PoiType> register(String name, Block block, int maxTickets, int validRange) {
        return register(name, PoiTypesAccessor.getBlockStates(block), maxTickets, validRange);
    }

    private static ResourceKey<PoiType> register(String name, Set<BlockState> states, int maxTickets, int validRange) {
        ResourceKey<PoiType> key = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
        PoiTypesAccessor.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, key, states, maxTickets, validRange);
        return key;
    }

    public static void initialize() {
    }

}
