package net.rusticpotatoes.bountifulseals.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.rusticpotatoes.bountifulseals.block.blockentity.custom.CrateBlockEntity;

public class FilterLockingSlot extends SlotItemHandler {
    private final CrateBlockEntity block_entity;

    public FilterLockingSlot(CrateBlockEntity blockEntity, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.block_entity = blockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.block_entity.inventory.getStackInSlot(67).getItem() == stack.getItem();


    }



}
