package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rusticpotatoes.bountifulseals.BountifulSeals;

public class ModBlockStatesProviders extends BlockStateProvider {
    public ModBlockStatesProviders(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BountifulSeals.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {

    }
}