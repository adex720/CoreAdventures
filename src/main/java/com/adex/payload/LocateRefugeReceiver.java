package com.adex.payload;

import com.adex.data.structure.refuge.Refuge;
import com.google.common.base.Stopwatch;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Util;
import net.minecraft.world.level.levelgen.structure.Structure;

public class LocateRefugeReceiver {

    public static void received(LocateRefugeC2S payload, ServerPlayNetworking.Context context) {
        ServerPlayer player = context.player();
        ServerLevel level = player.level();

        BlockPos pos = payload.pos();

        Registry<Structure> registry = level.registryAccess().lookupOrThrow(Registries.STRUCTURE);
        Holder.Reference<Structure> refuge = registry.getOrThrow(Refuge.REFUGE_KEY);

        Stopwatch stopwatch = Stopwatch.createStarted(Util.TICKER);
        Pair<BlockPos, Holder<Structure>> pair = level.getChunkSource().getGenerator().findNearestMapStructure(level, HolderSet.direct(refuge), pos, 100, false);
        stopwatch.stop();

        if (pair != null) sendUpdatePacket(pair.getFirst(), player);
    }

    private static void sendUpdatePacket(BlockPos pos, ServerPlayer player) {
        ServerPlayNetworking.send(player, new UpdateRefugeLocationS2C(pos));
    }

}
