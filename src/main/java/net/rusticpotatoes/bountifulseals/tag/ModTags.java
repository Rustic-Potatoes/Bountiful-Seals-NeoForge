package net.rusticpotatoes.bountifulseals.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.rusticpotatoes.bountifulseals.BountifulSeals;

public class ModTags {
    public class Items {
        public static final TagKey<Item> HARP_SEAL_FOODS = createItemTag("harp_seal_foods");
    }
    public class Blocks {
    }
    private static TagKey<Item> createItemTag(String id) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, id));
    }
    private static TagKey<Block> createBlockTag(String id) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, id));
    }
}
