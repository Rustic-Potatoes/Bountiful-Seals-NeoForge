package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BountifulSeals.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ARCTIC_COD.get());
        basicItem(ModItems.COOKED_ARCTIC_COD.get());
        basicItem(ModItems.ARCTIC_COD_BUCKET.get());

        withExistingParent(ModItems.HARP_SEAL_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.ARCTIC_COD_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
