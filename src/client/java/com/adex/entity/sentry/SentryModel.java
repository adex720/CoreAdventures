package com.adex.entity.sentry;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SentryModel extends EntityModel<SentryRenderState> {

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart handRight;
    private final ModelPart handLeft;
    private final ModelPart legRight;
    private final ModelPart legLeft;

    public SentryModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.handRight = root.getChild("handRight");
        this.handLeft = root.getChild("handLeft");
        this.legRight = root.getChild("legRight");
        this.legLeft = root.getChild("legLeft");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0f, -6.0f, -2.0f, 8.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 6.0f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -6.0f, -4.0f, 8.0f, 8.0f, 8.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, -2.0f, 0.0f));
        partdefinition.addOrReplaceChild("handRight", CubeListBuilder.create().texOffs(16, 32).addBox(0.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(4.0f, 2.0f, 0.0f));
        partdefinition.addOrReplaceChild("handLeft", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(-4.0f, 2.0f, 0.0f));
        partdefinition.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(24, 16).addBox(-2.0f, -0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(2.0f, 12.0f, 0.0f));
        partdefinition.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0f, -0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(-2.0f, 12.0f, 0.0f));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(SentryRenderState renderState) {
        super.setupAnim(renderState);
        float attackTicksRemaining = renderState.attackTicksRemaining;
        float speed = renderState.walkAnimationSpeed;
        float animationPos = renderState.walkAnimationPos;
        if (attackTicksRemaining > 0.0f) {
            this.handRight.xRot = -2.0f + 1.5f * Mth.triangleWave(attackTicksRemaining, 10.0f);
            this.handLeft.xRot = -2.0f + 1.5f * Mth.triangleWave(attackTicksRemaining, 10.0f);
        } else {
            this.handRight.xRot = (-0.2f + 1.5f * Mth.triangleWave(animationPos, 13.0f)) * speed;
            this.handLeft.xRot = (-0.2f - 1.5f * Mth.triangleWave(animationPos, 13.0f)) * speed;
        }

        this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.legRight.xRot = -1.5f * Mth.triangleWave(animationPos, 13.0f) * speed;
        this.legLeft.xRot = 1.5f * Mth.triangleWave(animationPos, 13.0f) * speed;
        this.legRight.yRot = 0.0f;
        this.legLeft.yRot = 0.0f;
    }
}