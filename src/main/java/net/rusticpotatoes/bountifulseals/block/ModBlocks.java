package net.rusticpotatoes.bountifulseals.block;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.custom.SnowClumpsBlock;
import net.rusticpotatoes.bountifulseals.block.custom.SnowGlobeBlock;
import net.rusticpotatoes.bountifulseals.block.custom.crate.CrateBlock;
import net.rusticpotatoes.bountifulseals.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BountifulSeals.MOD_ID);



    public static final DeferredBlock<Block> SNOW_CLUMPS = registerBlock("snow_clumps",
            () -> new SnowClumpsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW).noCollission().sound(SoundType.SNOW)
                    .replaceable()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> CRATE = registerBlock("crate",
            () -> new CrateBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .sound(SoundType.WOOD)
                    .strength(4.0F, 3.0F)
                    .forceSolidOn()
            ));

    public static final DeferredBlock<Block> SNOW_GLOBE = registerBlockWithoutItem("snow_globe",
            () -> new SnowGlobeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .sound(SoundType.STONE)
                    .strength(0.25F, 1F)
                    .pushReaction(PushReaction.DESTROY)
            ));

    private static final BlockBehaviour.Properties SUGAR_CRYSTAL_PROP = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_BLUE)
            .sound(SoundType.GLASS).strength(0.4F);

    public static final DeferredBlock<Block> SUGAR_CRYSTAL_BLOCK = registerBlock("sugar_crystal_block",
            () -> new Block(SUGAR_CRYSTAL_PROP));
    public static final DeferredBlock<StairBlock> SUGAR_CRYSTAL_STAIRS = registerBlock("sugar_crystal_stairs",
            () -> new StairBlock(ModBlocks.SUGAR_CRYSTAL_BLOCK.get().defaultBlockState(), SUGAR_CRYSTAL_PROP));
    public static final DeferredBlock<SlabBlock> SUGAR_CRYSTAL_SLAB = registerBlock("sugar_crystal_slab",
            () -> new SlabBlock(SUGAR_CRYSTAL_PROP));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }


    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

