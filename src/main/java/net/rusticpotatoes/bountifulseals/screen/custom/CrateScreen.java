package net.rusticpotatoes.bountifulseals.screen.custom;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.Log;
import net.rusticpotatoes.bountifulseals.screen.menu.CrateMenu;
import net.rusticpotatoes.bountifulseals.screen.slot.FilterLockingSlot;
import net.rusticpotatoes.bountifulseals.screen.slot.FilterSlot;
import net.rusticpotatoes.bountifulseals.screen.slot.ItemFrameSlot;

public class CrateScreen extends AbstractContainerScreen<CrateMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/gui/crate/crate_gui.png");
    private static final ResourceLocation ITEM_FRAME_TEXTURE = ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/gui/crate/item_frame_icon.png");
    private static final ResourceLocation FILTER_TEXTURE = ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/gui/crate/filter_icon.png");
    private static final ResourceLocation CHECK_TEXTURE = ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "textures/gui/crate/check.png");
    protected int crateLeftPos;
    protected int crateTopPos;

    public CrateScreen(CrateMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 256;
        this.imageHeight = 256;
    }

    @Override
    protected void init() {
        super.init();
        this.crateLeftPos = this.leftPos + CrateMenu.CRATE_MENU_X_OFSET;
        this.crateTopPos = this.topPos + CrateMenu.CRATE_MENU_Y_OFSET;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        guiGraphics.blit(GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        Slot slot = this.hoveredSlot;

        if (slot instanceof FilterLockingSlot) {
            if (!this.menu.getCarried().is(Items.AIR) && (!this.menu.getCarried().is(this.menu.blockEntity.getFilter()))) {
                guiGraphics.blit(CHECK_TEXTURE, mouseX - 11, mouseY + 4, 500, 0, 0, 8, 8, 16,16);
            }
        } else if (slot instanceof FilterSlot) {
            if (this.menu.blockEntity.getFilter() == Items.AIR) {
                guiGraphics.renderTooltip(this.font,
                        Component.translatable("container.bountifulseals.crate.filter_tooltip"),
                        mouseX, mouseY);
            } else if (!this.menu.blockEntity.isEmpty()) {
                guiGraphics.blit(CHECK_TEXTURE, mouseX - 11, mouseY + 4, 500, 0, 0, 8, 8, 16,16);
            }
        } else if (slot instanceof ItemFrameSlot) {
            if (!this.menu.blockEntity.hasItemFrame()) {
                guiGraphics.renderTooltip(this.font,
                        Component.translatable("container.bountifulseals.crate.item_frame_tooltip"),
                        mouseX, mouseY);
            } else if (!(this.menu.getCarried().is(Items.ITEM_FRAME) || this.menu.getCarried().is(Items.AIR))) {
                guiGraphics.blit(CHECK_TEXTURE, mouseX - 11, mouseY + 4, 500, 0, 0, 8, 8, 16,16);

            }
        }

        if (!this.menu.blockEntity.hasItemFrame()) {
            guiGraphics.blit(ITEM_FRAME_TEXTURE,
                    this.crateLeftPos + 8 * 18,
                    this.crateTopPos - 22,
                    0, 0,
                    16, 16,
                    16, 16);
        }

        if (this.menu.blockEntity.getFilter() == Items.AIR) {
            guiGraphics.blit(FILTER_TEXTURE,
                    this.crateLeftPos + 10 * 18,
                    this.crateTopPos - 22,
                    0, 0,
                    16, 16,
                    16, 16);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(
                this.font,
                this.title,
                CrateMenu.CRATE_MENU_X_OFSET,
                CrateMenu.CRATE_MENU_Y_OFSET - 12,
                0x404040,
                false
        );
        guiGraphics.drawString(
                this.font,
                this.playerInventoryTitle,
                CrateMenu.INVENTORY_MENU_X_OFSET,
                CrateMenu.INVENTORY_MENU_Y_OFSET - 12 ,
                0x404040,
                false
        );
    }
}