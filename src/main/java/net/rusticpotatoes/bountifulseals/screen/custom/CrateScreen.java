package net.rusticpotatoes.bountifulseals.screen.custom;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.blockentity.custom.CrateMenu;

public class CrateScreen extends AbstractContainerScreen<CrateMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/gui/crate/crate_gui.png");

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
    }
}