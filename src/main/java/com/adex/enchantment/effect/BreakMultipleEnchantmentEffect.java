package com.adex.enchantment.effect;

import com.adex.data.tag.ModTags;
import com.adex.util.Util;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.*;

public record BreakMultipleEnchantmentEffect(LevelBasedValue amount) implements EnchantmentValueEffect {

    public static final MapCodec<BreakMultipleEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(LevelBasedValue.CODEC.fieldOf("amount").forGetter(BreakMultipleEnchantmentEffect::amount)).apply(instance, BreakMultipleEnchantmentEffect::new));

    private static boolean RUNNING = false;

    @SuppressWarnings("unused")
    public void onBlockBroken(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack itemStack, int amount) {
        // Don't start new chain of blocks to break when already breaking a chain
        if (RUNNING) return;

        // Check if mined block is chainable
        if (!state.is(ModTags.CHAINABLE_BLOCKS)) return;

        RUNNING = true;
        Block block = state.getBlock();

        for (BlockPos breakPos : getBreakLocations(level, pos, block, amount, player.getRandom())) {
            BlockState breakState = level.getBlockState(breakPos);
            BlockEntity breakBlockEntity = level.getBlockEntity(breakPos);

            level.destroyBlock(breakPos, true, player);

            // Update player statistics
            player.awardStat(Stats.BLOCK_MINED.get(block));
        }

        RUNNING = false;
    }

    public static Collection<BlockPos> getBreakLocations(Level level, BlockPos pos, Block block, int amount, RandomSource random) {
        Set<BlockPos> validPos = new HashSet<>();
        ArrayList<BlockPos> chosenPos = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            validPos.addAll(getSameBlockNeighbours(level, pos, block));
            pos = Util.removeRandomElement(validPos, random);
            if (pos == null) break;
            chosenPos.add(pos);
        }

        return chosenPos;
    }

    /**
     * Returns positions of the blocks neighbors who are the same block
     */
    public static Collection<BlockPos> getSameBlockNeighbours(Level level, BlockPos pos, Block block) {
        ArrayList<BlockPos> poses = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            if (level.getBlockState(pos.relative(direction)).getBlock() == block) {
                poses.add(pos.relative(direction));
            }
        }
        return poses;
    }

    @Override
    public float process(int i, @NonNull RandomSource randomSource, float f) {
        return i;
    }

    @Override
    public @NonNull MapCodec<? extends BreakMultipleEnchantmentEffect> codec() {
        return CODEC;
    }
}
