package net.rusticpotatoes.bountifulseals.entity.client.arctic_cod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.entity.custom.ArcticCodEntity;

public class ArcticCodRenderer extends MobRenderer<ArcticCodEntity, CodModel<ArcticCodEntity>> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "arctic_cod"), "root");
    public ArcticCodRenderer(EntityRendererProvider.Context context) {
        super(context, new CodModel<>(context.bakeLayer(LAYER_LOCATION)), 0.3F);
    }


    public ResourceLocation getTextureLocation(ArcticCodEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/entity/fish/arctic_cod.png");
    }

    protected void setupRotations(ArcticCodEntity entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick, scale);
        float f = 4.3F * Mth.sin(0.6F * bob);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!entity.isInWater()) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}