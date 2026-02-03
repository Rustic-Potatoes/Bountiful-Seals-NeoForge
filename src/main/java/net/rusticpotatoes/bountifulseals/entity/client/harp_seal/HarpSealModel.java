package net.rusticpotatoes.bountifulseals.entity.client.harp_seal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.entity.custom.HarpSealEntity;

//Made With Blockbench 5.0.7

public class HarpSealModel<T extends HarpSealEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "harp_seal"), "root");

    private final ModelPart root;
    private final ModelPart main;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart wiskers;
    private final ModelPart leftwisker;
    private final ModelPart rightwisker;
    private final ModelPart eyes;
    private final ModelPart lowerhalf;
    private final ModelPart legs;
    private final ModelPart rightleg;
    private final ModelPart leftleg;
    private final ModelPart fin;
    private final ModelPart leftfin;
    private final ModelPart rightfin;

    public HarpSealModel(ModelPart modelPart) {
        this.root = modelPart.getChild("root");
        this.main = root.getChild("main");
        this.head = this.main.getChild("head");
        this.nose = this.head.getChild("nose");
        this.wiskers = this.nose.getChild("wiskers");
        this.leftwisker = this.wiskers.getChild("leftwisker");
        this.rightwisker = this.wiskers.getChild("rightwisker");
        this.eyes = this.head.getChild("eyes");
        this.lowerhalf = this.main.getChild("lowerhalf");
        this.legs = this.lowerhalf.getChild("legs");
        this.rightleg = this.legs.getChild("rightleg");
        this.leftleg = this.legs.getChild("leftleg");
        this.fin = this.main.getChild("fin");
        this.leftfin = this.fin.getChild("leftfin");
        this.rightfin = this.fin.getChild("rightfin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(1.0F, 24.0F, -7.5F));

        PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.02F, -5.5F, 12.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.0F, 0.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition head = main.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 36).addBox(-3.0F, -3.0F, -3.5F, 6.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 1.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(46, 12).addBox(-3.0F, -4.5F, -2.5F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.0F, 0.5F));

        PartDefinition wiskers = nose.addOrReplaceChild("wiskers", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftwisker = wiskers.addOrReplaceChild("leftwisker", CubeListBuilder.create().texOffs(48, 40).addBox(-0.01F, -1.5F, -3.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.5F, -0.5F));

        PartDefinition rightwisker = wiskers.addOrReplaceChild("rightwisker", CubeListBuilder.create().texOffs(51, 40).addBox(-0.01F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.5F, -0.5F));

        PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(60, 41).addBox(-0.01F, -0.75F, 1.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(60, 42).addBox(-0.01F, -0.75F, -3.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.25F, 0.0F));

        PartDefinition lowerhalf = main.addOrReplaceChild("lowerhalf", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -3.01F, -4.5F, 10.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 1.0F, 0.0F));

        PartDefinition legs = lowerhalf.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(26, 36).addBox(-1.0F, -3.52F, -3.5F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 2.5F, 0.0F));

        PartDefinition rightleg = legs.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(46, 6).addBox(-1.0F, -0.51F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 2.5F, 0.0F, -0.2182F, 0.0F));

        PartDefinition leftleg = legs.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(46, 0).addBox(-0.75F, -0.51F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, 0.0F, -2.5F, 0.0F, 0.2182F, 0.0F));

        PartDefinition fin = main.addOrReplaceChild("fin", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.5F));

        PartDefinition leftfin = fin.addOrReplaceChild("leftfin", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -5.0F));

        PartDefinition leftfin_r1 = leftfin.addOrReplaceChild("leftfin_r1", CubeListBuilder.create().texOffs(38, 20).addBox(-3.0F, -1.01F, -6.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition rightfin = fin.addOrReplaceChild("rightfin", CubeListBuilder.create().texOffs(38, 28).addBox(-3.0F, -1.01F, 0.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 4.0F, -0.0873F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(HarpSealEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(HarpSealAnimations.WALK_ANIM, limbSwing, limbSwingAmount, 4f, 2.5f);
        this.animate(entity.idleAnimationState, HarpSealAnimations.IDLE_ANIM, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, buffer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }


}