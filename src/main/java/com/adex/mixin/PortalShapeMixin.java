package com.adex.mixin;

import com.adex.CoreAdventures;
import com.adex.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PortalShape.class)
public class PortalShapeMixin {

    @Mutable
    @Final
    @Shadow
    private int width;

    @Mutable
    @Final
    @Shadow
    private int height;

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

    /**
     * Ensured that a portal is valid only if it is made of only one block.
     * This prevents creating a portal whose frame is made of both obsidian and reinforced ancient debris.
     */
    @SuppressWarnings({"DataFlowIssue"})
    @Inject(at = @At("RETURN"), method = "findAnyShape", cancellable = true)
    private static void ensurePortalIOfOneBlock(BlockGetter blockGetter, BlockPos blockPos, Direction.Axis axis, CallbackInfoReturnable<PortalShape> cir) {
        PortalShape portalShape = cir.getReturnValue();

        if (!portalShape.isValid()) return; // No need to check here

        int width = ((PortalShapeAccessor) portalShape).coread$getWidth();
        int height = ((PortalShapeAccessor) portalShape).coread$getHeight();
        Direction rightDirection = ((PortalShapeAccessor) portalShape).coread$getRightDir();
        BlockPos bottomLeft = ((PortalShapeAccessor) portalShape).coread$getBottomLeft().below().relative(rightDirection.getOpposite());
        BlockPos bottomRight = bottomLeft.relative(rightDirection, width + 1);
        BlockPos topLeft = bottomLeft.above(height + 1);

        Block block = blockGetter.getBlockState(bottomRight.above()).getBlock();
        for (int dy = 1; dy <= height; dy++) {
            if (blockGetter.getBlockState(bottomLeft.above(dy)).getBlock() != block ||
                    blockGetter.getBlockState(bottomRight.above(dy)).getBlock() != block) {
                ((PortalShapeMixin) (Object) portalShape).width = 0;
                ((PortalShapeMixin) (Object) portalShape).height = 0;
                cir.setReturnValue(portalShape);
                return;
            }
        }

        for (int dx = 1; dx <= width; dx++) {
            if (blockGetter.getBlockState(bottomLeft.relative(rightDirection, dx)).getBlock() != block ||
                    blockGetter.getBlockState(topLeft.relative(rightDirection, dx)).getBlock() != block) {
                ((PortalShapeMixin) (Object) portalShape).width = 0;
                ((PortalShapeMixin) (Object) portalShape).height = 0;
                cir.setReturnValue(portalShape);
                return;
            }
        }
    }
}

