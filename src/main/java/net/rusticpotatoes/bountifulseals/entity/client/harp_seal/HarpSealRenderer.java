package net.rusticpotatoes.bountifulseals.entity.client.harp_seal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.entity.custom.HarpSealEntity;

public class HarpSealRenderer extends MobRenderer<HarpSealEntity, EntityModel<HarpSealEntity>> {

    private final HarpSealPupModel<HarpSealEntity> babyModel;
    private final HarpSealModel<HarpSealEntity> adultModel;

    public HarpSealRenderer(EntityRendererProvider.Context context) {
        super(context, new HarpSealModel<>(context.bakeLayer(HarpSealModel.LAYER_LOCATION)), 0.5f);
        this.babyModel = new HarpSealPupModel<>(context.bakeLayer(HarpSealPupModel.LAYER_LOCATION));
        this.adultModel = new HarpSealModel<>(context.bakeLayer(HarpSealModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(HarpSealEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/entity/harp_seal/harp_seal.png");
    }

    @Override
    public void render(HarpSealEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        this.model = entity.isBaby() ? this.babyModel : this.adultModel;

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
