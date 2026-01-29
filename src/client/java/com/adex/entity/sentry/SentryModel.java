package com.adex.entity.sentry;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class SentryModel extends HumanoidModel<SentryRenderState> {

    public SentryModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0f, -6.0f, -2.0f, 8.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 6.0f, 0.0f));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -6.0f, -4.0f, 8.0f, 8.0f, 8.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, -2.0f, 0.0f));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 32).addBox(0.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(4.0f, 2.0f, 0.0f));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(-4.0f, 2.0f, 0.0f));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(24, 16).addBox(-2.0f, -0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(2.0f, 12.0f, 0.0f));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0f, -0.0f, -2.0f, 4.0f, 12.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.offset(-2.0f, 12.0f, 0.0f));

        head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, new CubeDeformation(0.0f)), PartPose.offset(0.0f, 0.0f, 0.0f));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(SentryRenderState renderState) {
        if (renderState.lookingAtItem) {
            holdItem(renderState);
            return;
        }

        super.setupAnim(renderState);
    }

    public ModelPart getMainHand(SentryRenderState renderState) {
        return renderState.mainArm == HumanoidArm.RIGHT ? rightArm : leftArm;
    }

    public void legsStanding() {
        rightLeg.xRot = 0.0f;
        leftLeg.xRot = 0.0f;
        rightLeg.yRot = 0.005f;
        leftLeg.yRot = -0.005f;
        rightLeg.zRot = 0.005f;
        leftLeg.zRot = -0.005f;
    }

    public void holdItem(SentryRenderState renderState) {
        legsStanding();

        ModelPart mainHand = getMainHand(renderState);

        float currentXHandRotation = mainHand.xRot;
        float wantedXHandRotation = -0.9f;
        float step = 0.05f;

        float currentYHandRotation = mainHand.yRot;
        float wantedYHandRotation = mainHand == rightArm ? -0.5f : 0.5f;
        float currentXHeadRotation = head.xRot;
        float wantedXHeadRotation = 0.5f;
        float currentYHeadRotation = head.yRot;
        float wantedYHeadRotation = 0.0f;

        float difference = Math.abs(currentXHandRotation - wantedXHandRotation);
        // ratio between movement this tick and total required movement
        float progress = difference <= step ? 1.0f : step / difference;

        // Modify every rotation the same percentage of the required rotation
        mainHand.xRot = Mth.rotLerpRad(progress, currentXHandRotation, wantedXHandRotation);
        mainHand.yRot = Mth.rotLerpRad(progress, currentYHandRotation, wantedYHandRotation);
        head.xRot = Mth.rotLerpRad(progress, currentXHeadRotation, wantedXHeadRotation);
        head.yRot = Mth.rotLerpRad(progress, currentYHeadRotation, wantedYHeadRotation);
    }

}