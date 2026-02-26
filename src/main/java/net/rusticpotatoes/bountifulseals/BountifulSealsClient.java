package net.rusticpotatoes.bountifulseals;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.rusticpotatoes.bountifulseals.block.ModBlockEntities;
import net.rusticpotatoes.bountifulseals.block.custom.crate.client.CrateBlockRenderer;
import net.rusticpotatoes.bountifulseals.entity.ModEntities;
import net.rusticpotatoes.bountifulseals.entity.client.arctic_cod.ArcticCodRenderer;
import net.rusticpotatoes.bountifulseals.entity.client.harp_seal.HarpSealRenderer;
import net.rusticpotatoes.bountifulseals.screen.custom.CrateScreen;
import net.rusticpotatoes.bountifulseals.screen.menu.ModMenus;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = BountifulSeals.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = BountifulSeals.MOD_ID, value = Dist.CLIENT)
public class BountifulSealsClient {
    public BountifulSealsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new); // Config
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.HARP_SEAL.get(), HarpSealRenderer::new);
        EntityRenderers.register(ModEntities.ARCTIC_COD.get(), ArcticCodRenderer::new);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.CRATE_BE.get(), CrateBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.CRATE_MENU.get(), CrateScreen::new);
    }

}
