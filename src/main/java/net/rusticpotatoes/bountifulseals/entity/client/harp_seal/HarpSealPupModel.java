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

// Made with Blockbench 5.0.7

public class HarpSealPupModel<T extends HarpSealEntity> extends HierarchicalModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "harp_seal_pup"), "root");

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

	public HarpSealPupModel(ModelPart modelPart) {
		this.root = modelPart.getChild("root");
		this.main = this.root.getChild("main");
		this.head = this.main.getChild("head");
		this.eyes = this.head.getChild("eyes");
		this.nose = this.head.getChild("nose");
		this.wiskers = this.nose.getChild("wiskers");
		this.leftwisker = this.wiskers.getChild("leftwisker");
		this.rightwisker = this.wiskers.getChild("rightwisker");
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

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create().texOffs(83, 0).addBox(-3.0F, -2.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -3.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition head = main.addOrReplaceChild("head", CubeListBuilder.create().texOffs(103, 14).addBox(-4.0F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(99, 26).addBox(-0.01F, -0.5F, -1.75F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(101, 26).addBox(-0.01F, -0.5F, 0.75F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -1.0F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(99, 34).addBox(-4.75F, 0.0F, -0.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, -1.0F));

		PartDefinition wiskers = nose.addOrReplaceChild("wiskers", CubeListBuilder.create(), PartPose.offset(-1.75F, 3.5F, 1.5F));

		PartDefinition leftwisker = wiskers.addOrReplaceChild("leftwisker", CubeListBuilder.create().texOffs(109, 32).addBox(-0.01F, -1.5F, -2.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.5F, -1.0F));

		PartDefinition rightwisker = wiskers.addOrReplaceChild("rightwisker", CubeListBuilder.create().texOffs(113, 32).addBox(-0.01F, -1.5F, -0.25F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.5F, 0.25F));

		PartDefinition lowerhalf = main.addOrReplaceChild("lowerhalf", CubeListBuilder.create().texOffs(83, 14).addBox(1.0F, -2.01F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, 0.0F));

		PartDefinition legs = lowerhalf.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(103, 24).addBox(0.0F, -2.5F, -2.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 1.5F, 0.0F));

		PartDefinition rightleg = legs.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(111, 6).addBox(-0.5F, -1.01F, -1.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.5F, 1.25F, 0.0F, -0.1745F, 0.0F));

		PartDefinition leftleg = legs.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(83, 32).addBox(-0.25F, -0.01F, -1.5F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.75F, 0.5F, -1.75F, 0.0F, 0.1745F, 0.0F));

		PartDefinition fin = main.addOrReplaceChild("fin", CubeListBuilder.create(), PartPose.offset(-0.5F, 3.5F, 0.0F));

		PartDefinition leftfin = fin.addOrReplaceChild("leftfin", CubeListBuilder.create().texOffs(83, 26).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, -3.5F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightfin = fin.addOrReplaceChild("rightfin", CubeListBuilder.create().texOffs(111, 0).addBox(-2.0F, -0.01F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.75F, 4.0F, -0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animateWalk(HarpSealAnimations.CHILD_WALK_ANIM, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, HarpSealAnimations.CHILD_IDLE_ANIM, ageInTicks, 1f);
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