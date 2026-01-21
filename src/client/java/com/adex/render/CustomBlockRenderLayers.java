package com.adex.render;

import com.adex.block.ModBlocks;
import net.fabricmc.fabric.impl.client.rendering.BlockRenderLayerMapImpl;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class CustomBlockRenderLayers {

    public static void initialize() {
        BlockRenderLayerMapImpl.putBlock(ModBlocks.JUNIPER_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(ModBlocks.JUNIPER_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(ModBlocks.JUNIPER_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(ModBlocks.JUNIPER_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(ModBlocks.POTTED_JUNIPER_SAPLING, ChunkSectionLayer.CUTOUT);
    }
}
