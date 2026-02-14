package net.rusticpotatoes.bountifulseals.block.custom.crate.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.rusticpotatoes.bountifulseals.Log;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.block.custom.crate.CrateBlockEntity;
import org.joml.Quaternionf;


public class CrateBlockRenderer implements BlockEntityRenderer<CrateBlockEntity> {
    public CrateBlockRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(CrateBlockEntity blockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack;
        BlockState state = blockEntity.getLevel().getBlockState(blockEntity.getBlockPos());

        if (blockEntity.hasItemFrame() && (blockEntity.getFilter() != Items.AIR) && (state.getBlock() == ModBlocks.CRATE.get())) {
            stack = new ItemStack(blockEntity.getFilter());

            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            if (canBeSeen(blockEntity.getBlockPos(), facing, blockEntity.getLevel())) {

                pPoseStack.pushPose();
                if (facing == Direction.NORTH) {
                    pPoseStack.translate(0.5f, 0.5f, 1f / 32);
                } else if (facing == Direction.EAST) {
                    pPoseStack.translate(31f / 32, 0.5f, 0.5f);
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(90f));
                } else if (facing == Direction.SOUTH) {
                    pPoseStack.translate(0.5f, 0.5f, 31f / 32);
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(180f));
                } else if (facing == Direction.WEST) {
                    pPoseStack.translate(1f / 32, 0.5f, 0.5f);
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(270f));
                }


                pPoseStack.scale(0.5f, 0.5f, 0.5f);

                itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos().above(1)), OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, blockEntity.getLevel(), 1);
                pPoseStack.popPose();
            }
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    private boolean canBeSeen(BlockPos pos, Direction direction, Level level) {
        BlockState state = Blocks.AIR.defaultBlockState();
        if (direction == Direction.NORTH) state = level.getBlockState(pos.north(1));
        else if (direction == Direction.EAST) state = level.getBlockState(pos.east(1));
        else if (direction == Direction.SOUTH) state = level.getBlockState(pos.south(1));
        else if (direction == Direction.WEST) state = level.getBlockState(pos.west(1));
        return state.is(Blocks.AIR);
    }
}