package net.rusticpotatoes.bountifulseals.screen.custom;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.screen.menu.CrateMenu;

public class CrateScreen extends AbstractContainerScreen<CrateMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/gui/crate/crate_gui.png");
    private static final ResourceLocation ITEM_FRAME_TEXTURE = ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/gui/crate/item_frame_icon.png");
    private static final ResourceLocation FILTER_TEXTURE = ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/gui/crate/filter_icon.png");


    public CrateScreen(CrateMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = (width - 256) / 2;
        int y = (height - 256) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, 256, 256, 256, 256);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        renderSlotTooltip(guiGraphics, 134, -15, mouseX, mouseY, "container.bountifulseals.crate.item_frame_tooltip");
        renderSlotTooltip(guiGraphics, 170, -15, mouseX, mouseY, "container.bountifulseals.crate.filter_tooltip");


        if (!this.menu.blockEntity.hasItemFrame()) {
            int x = this.leftPos + 134;
            int y = this.topPos - 15;

            guiGraphics.blit(ITEM_FRAME_TEXTURE, x, y, 0, 0, 16, 16, 16, 16);
        }

        if (this.menu.blockEntity.getFilter() == Items.AIR) {
            int x = this.leftPos + 170;
            int y = this.topPos - 15;

            guiGraphics.blit(FILTER_TEXTURE, x, y, 0, 0, 16, 16, 16, 16);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(
                this.font,
                this.title,
                -11,
                -5,
                0x404040,
                false
        );
        guiGraphics.drawString(
                this.font,
                this.playerInventoryTitle,
                8,
                118,
                0x404040,
                false
        );
        ;
    }

    private void renderSlotTooltip(GuiGraphics guiGraphics, int x, int y, int mouseX, int mouseY, String tranlatableText) {
        if ((mouseX >= this.leftPos + x && mouseX <= this.leftPos + x + 16) && (mouseY >= this.topPos + y && mouseY <= this.topPos + y + 16) && !(this.getSlotUnderMouse() != null && this.getSlotUnderMouse().hasItem())) {
            guiGraphics.renderTooltip(
                    this.font,
                    Component.translatable(tranlatableText),
                    mouseX,
                    mouseY
            );
        }

    }

}