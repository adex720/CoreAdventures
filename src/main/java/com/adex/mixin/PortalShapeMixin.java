package com.adex.mixin;

import com.adex.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PortalShape.class)
public class PortalShapeMixin {


    @Inject(at = @At("HEAD"), method = "createPortalBlocks", cancellable = true)
    private void createCustomPortalBlocks(LevelAccessor levelAccessor, CallbackInfo ci) {
        PortalShapeAccessor accessor = (PortalShapeAccessor) this;
        BlockPos bottomLeft = accessor.coread$getBottomLeft();

        if (levelAccessor.getBlockState(bottomLeft.below()).is(Blocks.OBSIDIAN)) return;

        int width = accessor.coread$getWidth();
        int height = accessor.coread$getHeight();
        Direction.Axis axis = accessor.coread$getAxis();
        Direction rightDir = accessor.coread$getRightDir();

        BlockState blockState = ModBlocks.CORE_PORTAL_BLOCK.defaultBlockState().setValue(NetherPortalBlock.AXIS, axis);
        BlockPos.betweenClosed(bottomLeft, bottomLeft.relative(Direction.UP, height - 1).relative(rightDir, width - 1))
                .forEach(blockPos -> levelAccessor.setBlock(blockPos, blockState, 18));

        ci.cancel();
    }

}

