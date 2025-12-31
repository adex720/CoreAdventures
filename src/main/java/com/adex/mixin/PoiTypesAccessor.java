package com.adex.mixin;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(PoiTypes.class)
public interface PoiTypesAccessor {

    @Invoker("register")
    static PoiType register(Registry<PoiType> registry, ResourceKey<PoiType> resourceKey, Set<BlockState> states, int maxTickets, int validRange) {
        throw new AssertionError();
    }

    @Invoker("getBlockStates")
    static Set<BlockState> getBlockStates(Block block) {
        throw new AssertionError();
    }
}