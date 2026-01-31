package net.rusticpotatoes.bountifulseals.entity.client.harp_seal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.entity.custom.HarpSealEntity;

public class HarpSealRenderer  extends MobRenderer<HarpSealEntity, HarpSealModel<HarpSealEntity>> {
    public HarpSealRenderer(EntityRendererProvider.Context context) {
        super(context, new HarpSealModel<>(context.bakeLayer(HarpSealModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HarpSealEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/entity/harp_seal/harp_seal.png");
    }

    @Override
    public void render(HarpSealEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
