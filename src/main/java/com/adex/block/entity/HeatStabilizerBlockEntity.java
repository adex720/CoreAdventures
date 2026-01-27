package com.adex.block.entity;

import com.adex.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HeatStabilizerBlockEntity extends BlockEntity {

    private final float range;

    public static final int UPDATE_INTERVAL = 60;

    public HeatStabilizerBlockEntity(BlockPos blockPos, BlockState blockState, float range) {
        super(ModBlockEntities.HEAT_STABILIZER_BLOCK_ENTITY_BLOCK_ENTITY, blockPos, blockState);
        this.range = range;
    }

    public HeatStabilizerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.HEAT_STABILIZER_BLOCK_ENTITY_BLOCK_ENTITY, blockPos, blockState);
        this.range = 16.0f;
    }

    public void tick(Level level, BlockPos pos) {
        if (level.getGameTime() % UPDATE_INTERVAL > 0) return;

        Vec3 centerPos = pos.getCenter();
        AABB searchArea = AABB.ofSize(centerPos, range, range, range);
        for (Player player : level.getEntitiesOfClass(Player.class, searchArea)) {
            if (player.distanceToSqr(centerPos) <= range * range) apply(player);
        }
    }

    private void apply(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.HEAT_IMMUNITY, UPDATE_INTERVAL + 1, 0, false, false));
    }
}
