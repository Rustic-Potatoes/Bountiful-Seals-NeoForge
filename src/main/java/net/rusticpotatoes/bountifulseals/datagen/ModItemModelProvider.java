package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
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
        basicItem(ModItems.SILLINESS_EXTRACT.get());
        basicItem(ModItems.SUGAR_CRYSTAL.get());

        withExistingParent(ModItems.HARP_SEAL_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.ARCTIC_COD_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

        public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
            this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                    .texture("wall",  ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID,
                            "block/" + baseBlock.getId().getPath()));
        }

}
