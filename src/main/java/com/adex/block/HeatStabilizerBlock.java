package com.adex.block;

import com.adex.block.entity.HeatStabilizerBlockEntity;
import com.adex.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class HeatStabilizerBlock extends BaseEntityBlock {

    private final float range;

    public HeatStabilizerBlock(Properties properties, float range) {
        super(properties);
        this.range = range;
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(properties1 -> new HeatStabilizerBlock(properties1, range));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new HeatStabilizerBlockEntity(pos, state, range);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level level, @NonNull BlockState blockState, @NonNull BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.HEAT_STABILIZER_BLOCK_ENTITY_BLOCK_ENTITY, (entityLevel, pos, state, entity) -> entity.tick(entityLevel, pos));
    }
}
