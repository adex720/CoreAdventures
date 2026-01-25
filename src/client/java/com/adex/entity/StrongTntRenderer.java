package com.adex.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class StrongTntRenderer extends EntityRenderer<PrimedStrongTnt, TntRenderState> {

    public StrongTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
    }

    @Override
    public void submit(TntRenderState renderState, @NonNull PoseStack stack, @NonNull SubmitNodeCollector nodeCollector, @NonNull CameraRenderState cameraRenderState) {
        stack.pushPose();
        stack.translate(0.0f, 0.5f, 0.0f);
        float fuseTicks = renderState.fuseRemainingInTicks;
        if (renderState.fuseRemainingInTicks < 10.0f) {
            float scale = 1.0f + getSizeMultiplier(renderState.fuseRemainingInTicks) * 0.3f;
            stack.scale(scale, scale, scale);
        }

        applyRotation(stack);

        if (renderState.blockState != null) {
            createBlock(renderState.blockState, stack, nodeCollector, renderState.lightCoords, (int) fuseTicks / 5 % 2 == 0, renderState.outlineColor);
        }

        stack.popPose();
        super.submit(renderState, stack, nodeCollector, cameraRenderState);
    }

    public static void createBlock(BlockState state, PoseStack stack, SubmitNodeCollector nodeCollector, int lightCoords, boolean bl, int outlineColor) {
        int overlayCoords = bl ? OverlayTexture.pack(OverlayTexture.u(1.0f), 10) : OverlayTexture.NO_OVERLAY;
        nodeCollector.submitBlock(stack, state, lightCoords, overlayCoords, outlineColor);
    }

    public float getSizeMultiplier(float ticks) {
        float fuseState = Mth.clamp(1.0f - ticks / 10.0f, 0.0f, 1.0f);
        return fuseState * fuseState * fuseState;
    }

    public void applyRotation(@NonNull PoseStack poseStack) {
        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0f));
        poseStack.translate(-0.5f, -0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
    }

    @Override
    public TntRenderState createRenderState() {
        return new TntRenderState();
    }

    public void extractRenderState(PrimedStrongTnt tnt, TntRenderState tntRenderState, float ticks) {
        super.extractRenderState(tnt, tntRenderState, ticks);
        tntRenderState.fuseRemainingInTicks = tnt.getFuse() - ticks + 1.0f;
        tntRenderState.blockState = tnt.getBlockState();
    }
}
