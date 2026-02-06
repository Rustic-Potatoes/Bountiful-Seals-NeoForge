package net.rusticpotatoes.bountifulseals.block.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.rusticpotatoes.bountifulseals.block.blockentity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class CrateBlockEntity extends BlockEntity implements MenuProvider {
    private ItemStack filter_item = ItemStack.EMPTY;
    public final int INVENTORY_SIZE = 67;

    public CrateBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CRATE_BE.get(), blockPos, blockState);
    }

    public ItemStackHandler getItems() {
        return inventory;
    }

    public final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE + 1) {

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == INVENTORY_SIZE) {
                // Filter can only be set if inventory is empty
                return isEmpty();
            }

            // Main inventory
            ItemStack filter = getStackInSlot(INVENTORY_SIZE);
            if (filter.isEmpty()) {
                return false; // no filter → no insert
            }

            return ItemStack.isSameItem(stack, filter);
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

    };

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots() - 1; i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void clearContents() {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.bountifulseals.crate");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CrateMenu(containerId, playerInventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        if (!this.filter_item.isEmpty()) {
            tag.put("filter_item", this.filter_item.save(registries, new CompoundTag()));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        if (tag.contains("filter_item")) {
            this.filter_item = ItemStack.parseOptional(registries, tag.getCompound("filter_item"));
        } else {
            this.filter_item = ItemStack.EMPTY;
        }
    }

    public void setFilterItem(ItemStack filter_item) {
        this.filter_item = filter_item;
        inventory.setStackInSlot(INVENTORY_SIZE, filter_item.copyWithCount(1));
    }

    public ItemStack getFilterItem() {
        return this.filter_item;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    public boolean canChangeFilter() {
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        for (int i = 0; i < (INVENTORY_SIZE -1); i++){
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

