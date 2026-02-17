package com.adex.util;

import com.adex.CoreAdventures;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    public static final float ONE_SIXTEENTH = 0.0625f;
    public static final float ONE_OVER_SQRT_2 = 1.0f / Mth.SQRT_OF_TWO;

    public static final Direction[] CARDINAL_DIRECTIONS = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

    public static <T> List<T> combine(List<T> list1, List<T> list2) {
        return Stream.of(list1, list2).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static Identifier getIdentifier(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).unwrapKey().map(ResourceKey::identifier).orElse(null);
    }

    public static Identifier getIdentifier(Block block) {
        return BuiltInRegistries.BLOCK.wrapAsHolder(block).unwrapKey().map(ResourceKey::identifier).orElse(null);
    }

    public static <T> T removeRandomElement(Set<T> set, RandomSource random) {
        if (set.isEmpty()) return null;

        T element = set.stream().toList().get(random.nextInt(set.size()));
        set.remove(element);
        return element;
    }

    public static <T> T removeRandomElement(List<T> list, RandomSource random) {
        if (list.isEmpty()) return null;

        T element = list.get(random.nextInt(list.size()));
        list.remove(element);
        return element;
    }

    /**
     * Loops trough every element in an iterable and applies the provided function for each one.
     * This method returns true if at least one of the function calls returns true.
     * The function is not called for elements after one which returns true.
     *
     * @param iterable Iterable
     * @param checker  Function to check if the element is looked for
     * @param <T>      Type of elements in the iterable
     * @return True if the iterator contains a valid element
     */
    public static <T> boolean contains(Iterable<T> iterable, Function<T, Boolean> checker) {
        for (T element : iterable) {
            if (checker.apply(element)) return true;
        }

        return false;
    }

    /**
     * Generates a list of unique random integers between 0 (inclusive)
     * and the specified maximum value (inclusive).
     *
     * <p>The method returns exactly {@code count} distinct integers.
     *
     * @param max    the upper bound (inclusive). Must be >= 0.
     * @param count  the number of unique random integers to generate.
     *               Must be between 0 and {@code max + 1}.
     * @param random RandomSource
     * @return a list containing {@code count} unique random integers
     * between 0 and {@code max} (inclusive)
     */
    public static List<Integer> generateUniqueRandomInts(int max, int count, RandomSource random) {
        if (max < 0) {
            CoreAdventures.LOGGER.error("Cannot run generateUniqueRandomInts, max must be >= 0, got {}", max);
            return List.of();
        }

        if (count < 0 || count > max + 1) {
            CoreAdventures.LOGGER.error("Cannot run generateUniqueRandomInts, count must be between 0 and max + 1, got {}", count);
            return List.of();
        }

        Set<Integer> result = new HashSet<>(count);

        while (result.size() < count) {
            int number = random.nextInt(max + 1);
            result.add(number);
        }

        return new ArrayList<>(result);
    }

    public static Rotation getDirectionDifference(Direction base, Direction compare) {
        if (base.get2DDataValue() == -1 || compare.get2DDataValue() == -1)
            throw new IllegalArgumentException("Cannot get difference for direction UP or DOWN");

        if (base == compare) return Rotation.NONE;
        if (base.getClockWise() == compare) return Rotation.CLOCKWISE_90;
        if (base.getCounterClockWise() == compare) return Rotation.COUNTERCLOCKWISE_90;
        return Rotation.CLOCKWISE_180;
    }

    public static boolean isCompletelyInside(BoundingBox outer, BoundingBox inner) {
        return outer.minX() <= inner.minX() && outer.minY() <= inner.minY() && outer.minZ() <= inner.minZ() &&
                outer.maxX() >= inner.maxX() && outer.maxY() >= inner.maxY() && outer.maxZ() >= inner.maxZ();
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

    public static Direction randomCardinalDirection(RandomSource random) {
        return CARDINAL_DIRECTIONS[random.nextInt(4)];
    }

    public static BlockPos randomPosIn(BoundingBox boundingBox, RandomSource random) {
        return new BlockPos(boundingBox.minX() + random.nextInt(boundingBox.getXSpan()),
                boundingBox.minY() + random.nextInt(boundingBox.getYSpan()),
                boundingBox.minZ() + random.nextInt(boundingBox.getZSpan()));
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
