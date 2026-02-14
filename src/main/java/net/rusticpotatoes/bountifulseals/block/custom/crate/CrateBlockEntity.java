package net.rusticpotatoes.bountifulseals.block.custom.crate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.ModBlockEntities;
import net.rusticpotatoes.bountifulseals.screen.menu.CrateMenu;
import org.jetbrains.annotations.Nullable;

public class CrateBlockEntity extends BlockEntity implements MenuProvider {
    private Item filter = Items.AIR;
    private boolean has_item_frame = false;
    public static final int INVENTORY_SIZE = 66;

    public CrateBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CRATE_BE.get(), blockPos, blockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.bountifulseals.crate");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CrateMenu(containerId, playerInventory, this);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public ItemStackHandler getItems() {
        return inventory;
    }

    public void dropItems() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots() - 1; i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        if (has_item_frame) {
            inv.addItem(new ItemStack(Items.ITEM_FRAME));
        }
        Containers.dropContents(level, this.worldPosition, inv);
    }

    public boolean isEmpty() {
        for (int i = 1; i < (INVENTORY_SIZE); i++) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void setFilter(Item filter_item) {
        if (filter_item instanceof BlockItem blockItem) {
            if (blockItem.getBlock().defaultBlockState().is(BlockTags.SHULKER_BOXES)) {
                return;
            }
        }
        filter = filter_item;
    }

    public Item getFilter() {
        return filter;
    }

    public void setItemFrame(boolean value) {
        has_item_frame = value;
        setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public boolean hasItemFrame() {
        return has_item_frame;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        if ((filter != null) && (filter != Items.AIR)) {
            tag.putString("Filter", BuiltInRegistries.ITEM.getKey(filter).toString());
        }
        tag.putBoolean("HasItemFrame", has_item_frame);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        if (tag.contains("Filter")) {
            filter = BuiltInRegistries.ITEM.get(ResourceLocation.parse(tag.getString("Filter")));
        } else {
            filter = Items.AIR;
        }
        if (tag.getBoolean("HasItemFrame")) {
             has_item_frame = true;
        }

    }



    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}

