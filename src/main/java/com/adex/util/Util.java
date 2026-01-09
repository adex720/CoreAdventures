package com.adex.util;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class Util {

    public static Identifier getIdentifier(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).unwrapKey().map(ResourceKey::identifier).orElse(null);
    }

    public static <T> T removeRandomElement(Set<T> set, RandomSource random) {
        if (set.isEmpty()) return null;

        T element = set.stream().toList().get(random.nextInt(set.size()));
        set.remove(element);
        return element;
    }


    /**
     * Returns 0 if the list is empty.
     *
     * @param list   List of weights
     * @param random RandomSource
     * @return Randomly weighted index
     */
    public static int getRandomWeightedIndex(ArrayList<Float> list, RandomSource random) {
        AtomicReference<Float> sum = new AtomicReference<>(0.0f);
        list.forEach(f -> sum.updateAndGet(v -> v + f));
        return getRandomWeightedIndex(list, random, sum.get());
    }

    public static int getRandomWeightedIndex(ArrayList<Float> list, RandomSource random, float totalWeight) {
        if (totalWeight <= 0 || list.isEmpty()) return 0;

        float value = random.nextFloat() * totalWeight;
        for (int i = 0; i < list.size() - 1; i++) {
            value -= list.get(i);
            if (value <= 0) return i;
        }

        return list.size() - 1;
    }

    public static void sendPayloadS2C(CustomPacketPayload payload, ServerLevel level, Vec3 pos) {
        sendPayloadS2C(payload, level, pos, 64.0d);
    }

    public static void sendPayloadS2C(CustomPacketPayload payload, ServerLevel level, Vec3 pos, double radius) {
        PlayerLookup.around(level, pos, radius).forEach(
                player -> ServerPlayNetworking.send(player, payload));
    }

    /**
     * Returns a {@link java.util.HashSet} containing every {@link net.minecraft.core.BlockPos}
     * horizontally n blocks away from the center.
     * Time complexity: O(n^2)
     *
     * @param center Center of the search
     * @param n      Manhattan distance
     * @param level  Level of the search
     * @return {@link java.util.HashSet} of found positions
     */
    public static Set<BlockPos> getBlocksNAway(BlockPos center, int n, Level level) {
        return getBlocksNAway(center, n, level, (_, _, _) -> true);
    }

    /**
     * Returns a {@link java.util.HashSet} containing every {@link net.minecraft.core.BlockPos}
     * horizontally n blocks away from the center approved by the validator.
     * Time complexity: O(n^2) * O(validator)
     *
     * @param center    Center of the search
     * @param n         Manhattan distance
     * @param level     Level of the search
     * @param validator Validator
     * @return {@link java.util.HashSet} of found positions
     */
    public static Set<BlockPos> getBlocksNAway(BlockPos center, int n, Level level, Validator validator) {
        Set<BlockPos> set = new HashSet<>();

        for (int x = -n; x <= n; x++) {
            int left = n - (Math.abs(x));
            for (int y = -left; y <= left; y++) {
                int z = left - (Math.abs(y));

                BlockPos newPos1 = center.offset(x, y, z);
                BlockPos newPos2 = center.offset(x, y, -z);
                BlockState state1 = level.getBlockState(newPos1);
                BlockState state2 = level.getBlockState(newPos2);
                if (validator.validate(state1, newPos1, level)) set.add(newPos1);
                if (validator.validate(state2, newPos2, level)) set.add(newPos2);
            }
        }

        return set;
    }

    /**
     * Returns true when and only when given block state has full block collision and the block above it has no collision.
     *
     * @param state Block state of block to check
     * @param pos   Position of block to check
     * @param level Level of the block
     * @return true if the block is a full block and there is no collision immediately above it.
     */
    public static boolean isFullBlockWithAirAbove(BlockState state, BlockPos pos, Level level) {
        return state.isCollisionShapeFullBlock(level, pos) && level.getBlockState(pos.above()).getCollisionShape(level, pos.above()) == Shapes.empty();
    }

    @FunctionalInterface
    public interface Validator {
        boolean validate(BlockState state, BlockPos pos, Level level);
    }

}
