package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.fml.earlydisplay.ElementShader;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.rusticpotatoes.bountifulseals.BountifulSeals;

import static net.rusticpotatoes.bountifulseals.block.ModBlocks.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BountifulSeals.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        block(SUGAR_CRYSTAL_BLOCK);
        stairs(SUGAR_CRYSTAL_STAIRS, SUGAR_CRYSTAL_BLOCK);
        slab(SUGAR_CRYSTAL_SLAB, SUGAR_CRYSTAL_BLOCK);
    }

    private void block(DeferredBlock<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
    private void stairs(DeferredBlock<StairBlock> stairs, DeferredBlock<Block> block) {
        stairsBlock(stairs.get(), blockTexture(block.get()));
        blockItem(stairs);
    }
    private void slab(DeferredBlock<SlabBlock> slab, DeferredBlock<Block> block) {
        slabBlock(slab.get(), blockTexture(block.get()), blockTexture(block.get()));
        blockItem(slab);
    }
    private void wallNoItem(DeferredBlock<WallBlock> wall, DeferredBlock<Block> block) {
        wallBlock(wall.get(), blockTexture(block.get()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(BountifulSeals.MOD_ID + ":block/" + deferredBlock.getId().getPath()));
    }
}