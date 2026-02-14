package net.rusticpotatoes.bountifulseals.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.custom.crate.CrateBlock;
import net.rusticpotatoes.bountifulseals.block.custom.SnowClumpsBlock;
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
                    .sound(SoundType.WOOD)
            ));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn =BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

