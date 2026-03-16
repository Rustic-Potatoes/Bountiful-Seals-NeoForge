package net.rusticpotatoes.bountifulseals.screen.slot;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.rusticpotatoes.bountifulseals.block.custom.crate.CrateBlockEntity;

public class ItemFrameSlot extends Slot {

    private final CrateBlockEntity block_entity;

    public ItemFrameSlot(CrateBlockEntity blockEntity, int index, int xPosition, int yPosition) {
        super(new SimpleContainer(1), index, xPosition, yPosition);
        this.block_entity = blockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return (stack.getItem() == Items.ITEM_FRAME)
                && !block_entity.hasItemFrame();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public void set(ItemStack stack) {
        //  BountifulSeals.LOGGER.debug("set");
        if (!stack.isEmpty() && (stack.getItem() == Items.ITEM_FRAME)) {
            block_entity.setItemFrame(true);
            stack.shrink(1); // consume frame
        }
    }

    @Override
    public boolean mayPickup(Player player) {
        return block_entity.hasItemFrame();
    }

    @Override
    public ItemStack remove(int amount) {
        if (block_entity.hasItemFrame()) {
            block_entity.setItemFrame(false);
            return new ItemStack(Items.ITEM_FRAME);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getItem() {
        if (block_entity.hasItemFrame()) {
            return Items.ITEM_FRAME.getDefaultInstance();
        }
        return super.getItem();
    }
}
