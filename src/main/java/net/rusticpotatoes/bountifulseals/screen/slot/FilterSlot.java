package net.rusticpotatoes.bountifulseals.screen.slot;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.rusticpotatoes.bountifulseals.block.custom.crate.CrateBlockEntity;

public class FilterSlot extends Slot {

    private final CrateBlockEntity block_entity;

    public FilterSlot(CrateBlockEntity blockEntity, int index, int xPosition, int yPosition) {
        super(new SimpleContainer(1), index, xPosition, yPosition);
        this.block_entity = blockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if (!stack.isEmpty() && this.block_entity.isEmpty() && (this.block_entity.getFilter() == Items.AIR)) {
            if (stack.getItem() instanceof BlockItem blockItem) {
                if (blockItem.getBlock().defaultBlockState().is(BlockTags.SHULKER_BOXES)) {
                    return false;
                }
            }
            block_entity.setFilter(stack.getItem());
        }
        return false;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean mayPickup(Player player) {
        if (this.block_entity.isEmpty() && (this.block_entity.getFilter() != Items.AIR)) {
            block_entity.setFilter(Items.AIR);
        }
         return false;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(block_entity.getFilter());
    }

    @Override
    public boolean hasItem() {
        return (this.block_entity.getFilter() != Items.AIR);
    }
}
