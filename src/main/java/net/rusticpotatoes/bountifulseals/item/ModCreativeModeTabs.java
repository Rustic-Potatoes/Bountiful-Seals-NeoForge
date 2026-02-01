package net.rusticpotatoes.bountifulseals.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BountifulSeals.MOD_ID);

    public static final Supplier<CreativeModeTab> SEAL_TAB = CREATIVE_MODE_TAB.register("seal_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BEACH_BALL.get()))
                    .title(Component.translatable("creativetab.bountifulseals.seal_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BEACH_BALL);
                        output.accept(ModItems.HARP_SEAL_SPAWN_EGG);
                        output.accept(ModItems.ARCTIC_COD_SPAWN_EGG);
                        output.accept(ModItems.ARCTIC_COD);
                        output.accept(ModItems.COOKED_ARCTIC_COD);
                        output.accept(ModItems.ARCTIC_COD_BUCKET);

                        output.accept(ModBlocks.SNOW_CLUMPS);
                    }).build());

    public static void register(IEventBus eventBus) {
     CREATIVE_MODE_TAB.register(eventBus);
    }
}
