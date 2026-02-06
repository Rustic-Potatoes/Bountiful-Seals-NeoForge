package net.rusticpotatoes.bountifulseals.screen.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.rusticpotatoes.bountifulseals.block.blockentity.custom.CrateBlockEntity;

public class FilterSlot extends SlotItemHandler {

    private final CrateBlockEntity block_entity;

    public FilterSlot(CrateBlockEntity blockEntity, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.block_entity = blockEntity;
    }


    @Override
    public boolean mayPlace(ItemStack stack) {
        if (!stack.isEmpty() && this.block_entity.isEmpty() && block_entity.getFilterItem().isEmpty()) {
            block_entity.setFilterItem(stack.copyWithCount(1));
        }
        return false;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean mayPickup(Player player) {
        if (this.block_entity.isEmpty() && !this.block_entity.getFilterItem().isEmpty()) {
            block_entity.setFilterItem(ItemStack.EMPTY);
        }
         return false;
    }

}
