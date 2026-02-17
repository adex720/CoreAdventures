package com.adex.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class ChandelierBlock extends Block {

    public ChandelierBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState blockState, @NonNull BlockGetter blockGetter, @NonNull BlockPos blockPos, @NonNull CollisionContext collisionContext) {
        return Shapes.box(0.375f, 0.4375f, 0.375f, 0.625f, 1.0f, 0.625f);
    }
}
