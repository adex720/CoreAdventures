package com.adex.mixin;

import com.mojang.math.Axis;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PortalShape.class)
public interface PortalShapeAccessor {
    @Mutable
    @Accessor("FRAME")
    static void setFrame(BlockBehaviour.StatePredicate frame) {
        throw new AssertionError();
    }

    @Accessor("bottomLeft")
    BlockPos coread$getBottomLeft();

    @Accessor("width")
    int coread$getWidth();

    @Accessor("height")
    int coread$getHeight();

    @Accessor("axis")
    Direction.Axis coread$getAxis();

    @Accessor("rightDir")
    Direction coread$getRightDir();
}