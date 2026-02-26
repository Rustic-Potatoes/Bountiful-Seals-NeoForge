package net.rusticpotatoes.bountifulseals.screen.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.rusticpotatoes.bountifulseals.Log;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.block.custom.crate.CrateBlockEntity;
import net.rusticpotatoes.bountifulseals.screen.slot.FilterLockingSlot;
import net.rusticpotatoes.bountifulseals.screen.slot.FilterSlot;
import net.rusticpotatoes.bountifulseals.screen.slot.ItemFrameSlot;


public class CrateMenu extends AbstractContainerMenu {
    public final CrateBlockEntity blockEntity;
    private final Level level;


    public CrateMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public CrateMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenus.CRATE_MENU.get(), containerId);
        this.blockEntity = ((CrateBlockEntity) blockEntity);
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        for (int j = 0; j < 6; j++) {
            for (int k = 0; k < 11; k++) {
                this.addSlot(new FilterLockingSlot(this.blockEntity, this.blockEntity.inventory, (k + j * 11), -10 + k * 18, 7 + j * 18));
            }
        }
        this.addSlot(new FilterSlot(this.blockEntity, 0, 170, -15));
        this.addSlot(new ItemFrameSlot(this.blockEntity, 0, 134, -15));

    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return !(slot instanceof FilterSlot || slot instanceof ItemFrameSlot);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot slot = slots.get(index);
        ItemStack sourceStack = slot.getItem();
        ItemStack copySourceStack = sourceStack.copy();


        if (!slot.hasItem() || slot instanceof FilterSlot || slot instanceof ItemFrameSlot) return ItemStack.EMPTY;

        if (index < 36) {// inside of inventory
            if (this.blockEntity.getFilter() == Items.AIR) {
                this.blockEntity.setFilter(sourceStack.getItem());
            }
            if (!moveItemStackTo(sourceStack, 36, 36
                    + CrateBlockEntity.INVENTORY_SIZE, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < 36 + CrateBlockEntity.INVENTORY_SIZE) { // inside of crate block
            if (!moveItemStackTo(sourceStack, 0, 36, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index == 103) {
            return ItemStack.EMPTY;
        } else {
            Log.error("Invalid slot Index for Crate Menu:" + index);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        slot.onTake(playerIn, sourceStack);
        return copySourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.CRATE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 129 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 187));
        }
    }


}

