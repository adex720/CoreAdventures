package com.adex.block.entity;

import com.adex.advancement.criterion.ModCriterionTriggers;
import com.adex.block.HeatStabilizerBlock;
import com.adex.data.dimension.ModDimensions;
import com.adex.data.tag.ModTags;
import com.adex.effect.ModEffects;
import com.adex.entity.statistics.ModStats;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class HeatStabilizerBlockEntity extends BlockEntity {

    private final float range;
    private int fuel;

    public static final int UPDATE_INTERVAL = 60;

    public HeatStabilizerBlockEntity(BlockPos blockPos, BlockState blockState, float range) {
        super(ModBlockEntities.HEAT_STABILIZER_BLOCK_ENTITY_BLOCK_ENTITY, blockPos, blockState);
        this.range = range;
        this.fuel = 0;
    }

    public HeatStabilizerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.HEAT_STABILIZER_BLOCK_ENTITY_BLOCK_ENTITY, blockPos, blockState);
        this.range = 16.0f;
        this.fuel = 0;
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        this.fuel = input.getIntOr("fuel", 0);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        if (this.fuel > 0) output.putInt("fuel", this.fuel);
    }

    public void tick(Level level, BlockPos pos) {
        if (level.getGameTime() % UPDATE_INTERVAL > 0) return;
        if (fuel <= 0) return;
        fuel--;

        // Update LIT blockState if needed
        checkBlockState(level, pos);

        // Don't give effect outside core
        if (level.dimension() != ModDimensions.CORE) return;

        // Give effect to all players within range
        Vec3 centerPos = pos.getCenter();
        AABB searchArea = AABB.ofSize(centerPos, range, range, range);
        for (Player player : level.getEntitiesOfClass(Player.class, searchArea)) {
            if (player.distanceToSqr(centerPos) <= range * range) apply(player);
        }
    }

    /**
     * Gives the player {@link ModEffects#HEAT_IMMUNITY} and
     * triggers {@link ModCriterionTriggers#BECOME_HEAT_IMMUNE} if on server side.
     */
    private void apply(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.HEAT_IMMUNITY, UPDATE_INTERVAL + 1, 0, false, false));

        if (player instanceof ServerPlayer serverPlayer)
            ModCriterionTriggers.BECOME_HEAT_IMMUNE.trigger(serverPlayer);
    }

    /**
     * Checks and updates the owner {@link BlockState} if needed.
     */
    private void checkBlockState(Level level, BlockPos pos) {
        BlockState old = level.getBlockState(pos);
        boolean shouldBeLit = fuel > 0;
        if (old.getValue(HeatStabilizerBlock.LIT) == shouldBeLit) return; // state is correct

        level.setBlock(pos, old.setValue(HeatStabilizerBlock.LIT, shouldBeLit), 3);
    }

    public InteractionResult itemUsed(ItemStack item, Level level, BlockPos pos, Player player) {
        if (!item.is(ModTags.HEAT_STABILIZER_FUEL)) return InteractionResult.PASS;

        fuel += 10;
        checkBlockState(level, pos);
        if (!level.isClientSide()) updateHand(player, item);
        return InteractionResult.SUCCESS;
    }

    /**
     * Consumes one item from the itemStack and updates player statistics.
     *
     * @param player    Player who interacted
     * @param itemStack ItemStack used
     */
    private void updateHand(Player player, ItemStack itemStack) {
        player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
        player.awardStat(ModStats.INTERACT_WITH_HEAT_STABILIZER);
        itemStack.consume(1, player);
    }
}
