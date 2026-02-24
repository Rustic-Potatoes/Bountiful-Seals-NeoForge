package net.rusticpotatoes.bountifulseals;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.block.ModBlockEntities;
import net.rusticpotatoes.bountifulseals.screen.menu.ModMenus;
import net.rusticpotatoes.bountifulseals.entity.ModEntities;
import net.rusticpotatoes.bountifulseals.item.ModCreativeModeTabs;
import net.rusticpotatoes.bountifulseals.item.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BountifulSeals.MOD_ID)
public class BountifulSeals {
    public static final String MOD_ID = "bountifulseals";

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
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }
    @SubscribeEvent
    public void wanderTrader(WandererTradesEvent event) {
        event.getGenericTrades().add(new BasicItemListing(
                6, new ItemStack(ModItems.ARCTIC_COD_BUCKET.get()), 4, 1)
        );
        event.getGenericTrades().add(new BasicItemListing(
                8, new ItemStack(ModItems.SNOW_GLOBE.get()), 4, 1)
        );
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
