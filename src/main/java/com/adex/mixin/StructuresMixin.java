package com.adex.mixin;

import com.adex.data.structure.refuge.Refuge;
import com.adex.data.tag.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Structures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Structures.class)
public class StructuresMixin {

    @Inject(at = @At("HEAD"), method = "bootstrap")
    private static void bootstrapRefuge(BootstrapContext<Structure> bootstrapContext, CallbackInfo ci) {
        bootstrapContext.register(Refuge.REFUGE_KEY, new Refuge(new Structure.StructureSettings.Builder(bootstrapContext.lookup(Registries.BIOME).getOrThrow(ModTags.CORE_BIOMES)).terrainAdapation(TerrainAdjustment.BURY).build()));
    }
}
