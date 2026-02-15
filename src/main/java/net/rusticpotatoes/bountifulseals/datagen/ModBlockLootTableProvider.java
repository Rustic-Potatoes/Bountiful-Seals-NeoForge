package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.item.ModItems;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.CRATE.get());
        dropSelf(ModBlocks.SNOW_GLOBE.get());
        add(ModBlocks.SNOW_CLUMPS.get(), createPetalsDrops(ModBlocks.SNOW_CLUMPS.get()));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
