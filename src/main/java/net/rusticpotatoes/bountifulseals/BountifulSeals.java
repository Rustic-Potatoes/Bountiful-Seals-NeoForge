package net.rusticpotatoes.bountifulseals;

import com.mojang.logging.LogUtils;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.entity.ModEntities;
import net.rusticpotatoes.bountifulseals.item.ModCreativeModeTabs;
import net.rusticpotatoes.bountifulseals.item.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BountifulSeals.MOD_ID)
public class BountifulSeals {
    public static final String MOD_ID = "bountifulseals";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public BountifulSeals(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModEntities.register(modEventBus);










        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.BEACH_BALL);
        }
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.HARP_SEAL_SPAWN_EGG);
            event.accept(ModItems.ARCTIC_COD_SPAWN_EGG);
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(
                    Blocks.SNOW_BLOCK.asItem().getDefaultInstance(),
                    ModBlocks.SNOW_CLUMPS.toStack(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(
                    Items.COOKED_COD.getDefaultInstance(),
                    ModItems.ARCTIC_COD.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.insertAfter(
                    ModItems.ARCTIC_COD.get().getDefaultInstance(),
                    ModItems.COOKED_ARCTIC_COD.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                    );
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
