package com.adex.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GolemModel extends EntityModel<GolemRenderState> {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart handLeft;
    private final ModelPart handRight;
    private final ModelPart legLeft;
    private final ModelPart legRight;

    public GolemModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.handLeft = root.getChild("handLeft");
        this.handRight = root.getChild("handRight");
        this.legLeft = root.getChild("legLeft");
        this.legRight = root.getChild("legRight");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(72, 11).addBox(-7.0f, -29.0f, -4.0f, 14.0f, 5.0f, 8.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 24.0f, 0.0f));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0f, -24.0f, -12.0f, 24.0f, 17.0f, 24.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 24.0f, 0.0f));
        partdefinition.addOrReplaceChild("handLeft", CubeListBuilder.create().texOffs(111, 25).addBox(-15.0f, -22.0f, -2.0f, 3.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 24.0f, 0.0f));
        partdefinition.addOrReplaceChild("handRight", CubeListBuilder.create().texOffs(97, 25).addBox(12.0f, -22.0f, -2.0f, 3.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 24.0f, 0.0f));
        partdefinition.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(16, 42).addBox(-7.0f, -7.0f, -2.0f, 4.0f, 7.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 24.0f, 0.0f));
        partdefinition.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(0, 42).addBox(3.0f, -7.0f, -2.0f, 4.0f, 7.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 24.0f, 0.0f));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(GolemRenderState renderState) {
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
