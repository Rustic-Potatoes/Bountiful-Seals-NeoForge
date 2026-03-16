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
    public static final int CRATE_MENU_X_OFSET = 30;
    public static final int CRATE_MENU_Y_OFSET = 41;
    public static final int INVENTORY_MENU_X_OFSET = 48;
    public static final int INVENTORY_MENU_Y_OFSET = 163;

    public final CrateBlockEntity blockEntity;
    private final Level level;


    public CrateMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public CrateMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenus.CRATE_MENU.get(), containerId);
        this.blockEntity = ((CrateBlockEntity) blockEntity);
        this.level = inv.player.level();

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(inv, x + y * 9 + 9, INVENTORY_MENU_X_OFSET + x * 18, INVENTORY_MENU_Y_OFSET + y * 18));
            }
        }

        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(inv, x, INVENTORY_MENU_X_OFSET + x * 18, INVENTORY_MENU_Y_OFSET + 3 * 18 + 4));
        }

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 11; x++) {
                this.addSlot(new FilterLockingSlot(this.blockEntity, this.blockEntity.inventory, (x + y * 11), CRATE_MENU_X_OFSET + x * 18, CRATE_MENU_Y_OFSET + y * 18));
            }
        }

        this.addSlot(new ItemFrameSlot(this.blockEntity, 0, CRATE_MENU_X_OFSET + 8 * 18, CRATE_MENU_Y_OFSET - 22));

        this.addSlot(new FilterSlot(this.blockEntity, 0, CRATE_MENU_X_OFSET + 10 * 18, CRATE_MENU_Y_OFSET - 22));

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
}

