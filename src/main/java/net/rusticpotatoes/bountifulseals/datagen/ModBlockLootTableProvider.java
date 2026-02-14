package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;

import java.util.Collections;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.CRATE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Collections.singleton(ModBlocks.CRATE.get()); // will not work for more than one block
    }
}
