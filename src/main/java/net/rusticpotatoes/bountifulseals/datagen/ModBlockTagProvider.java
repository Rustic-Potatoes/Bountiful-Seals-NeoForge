package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.tag.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BountifulSeals.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Blocks.HARP_SEAL_SPAWNABLE_ON)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.SAND)
                .add(Blocks.GRAVEL)
                .add(Blocks.SNOW)
                .add(Blocks.SNOW_BLOCK)
                .add(ModBlocks.SNOW_CLUMPS.get())
                .add(Blocks.ICE)
                .add(Blocks.FROSTED_ICE)
                .add(Blocks.PACKED_ICE);
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.CRATE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SUGAR_CRYSTAL_BLOCK.get())
                .add(ModBlocks.SUGAR_CRYSTAL_STAIRS.get())
                .add(ModBlocks.SUGAR_CRYSTAL_SLAB.get());
    }
}