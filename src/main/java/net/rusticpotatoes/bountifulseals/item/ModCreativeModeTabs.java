package net.rusticpotatoes.bountifulseals.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.effect.ModPotions;
import org.apache.commons.io.monitor.FileAlterationListener;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BountifulSeals.MOD_ID);

    public static final Supplier<CreativeModeTab> SEAL_TAB = CREATIVE_MODE_TAB.register("seal_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SNOW_GLOBE))
                    .title(Component.translatable("creativetab.bountifulseals.seal_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.HARP_SEAL_SPAWN_EGG);
                        output.accept(ModItems.ARCTIC_COD_SPAWN_EGG);
                        output.accept(ModItems.ARCTIC_COD);
                        output.accept(ModItems.COOKED_ARCTIC_COD);
                        output.accept(ModItems.ARCTIC_COD_BUCKET);
                        output.accept(ModItems.SILLINESS_EXTRACT);
                        output.accept(ModItems.SUGAR_CRYSTAL);
                        output.accept(ModItems.PROPELLER_HAT);
                        output.accept(ModItems.CANDY_LOLLIPOP);

                        output.accept(ModBlocks.SNOW_CLUMPS);
                        output.accept(ModBlocks.CRATE);
                        output.accept(ModBlocks.SNOW_GLOBE);

                        output.accept(ModBlocks.SUGAR_CRYSTAL_BLOCK);
                        output.accept(ModBlocks.SUGAR_CRYSTAL_STAIRS);
                        output.accept(ModBlocks.SUGAR_CRYSTAL_SLAB);

                        output.accept(PotionContents.createItemStack(Items.POTION, ModPotions.SILLINESS));
                        output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.SILLINESS));
                        output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.SILLINESS));
                        output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, ModPotions.SILLINESS));

                        output.accept(PotionContents.createItemStack(Items.POTION, ModPotions.TOMS_FOOLERY));
                        output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.TOMS_FOOLERY));
                        output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.TOMS_FOOLERY));
                        output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, ModPotions.TOMS_FOOLERY));

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
