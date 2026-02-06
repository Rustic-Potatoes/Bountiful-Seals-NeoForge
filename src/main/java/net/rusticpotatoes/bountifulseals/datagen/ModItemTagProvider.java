package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.tag.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.rusticpotatoes.bountifulseals.item.ModItems.*;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, BountifulSeals.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.HARP_SEAL_FOODS)
                .add(Items.COD).add(ARCTIC_COD.get());
        tag(ItemTags.FISHES)
                .add(ARCTIC_COD.get()).add(COOKED_ARCTIC_COD.get());
        tag(Tags.Items.ANIMAL_FOODS)
                .addTags(ModTags.Items.HARP_SEAL_FOODS);
        tag(Tags.Items.BUCKETS_ENTITY_WATER)
                .add(ARCTIC_COD_BUCKET.get());
        tag(Tags.Items.FOODS_RAW_FISH)
                .add(ARCTIC_COD.get());
        tag(Tags.Items.FOODS_COOKED_FISH)
                .add(COOKED_ARCTIC_COD.get());
    }
}
