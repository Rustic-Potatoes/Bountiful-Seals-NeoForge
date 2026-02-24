package net.rusticpotatoes.bountifulseals.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.entity.ModEntities;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BountifulSeals.MOD_ID);

    public static final DeferredItem<Item> ARCTIC_COD_BUCKET = ITEMS.register("arctic_cod_bucket",
            () -> new MobBucketItem(
                    ModEntities.ARCTIC_COD.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties()
                            .stacksTo(1)
                            .component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));

    public static final DeferredItem<Item> ARCTIC_COD = ITEMS.register("arctic_cod",
            () -> new Item(new Item.Properties().food(ModFoods.ARCTIC_COD)));

    public static final DeferredItem<Item> COOKED_ARCTIC_COD = ITEMS.register("cooked_arctic_cod",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_ARCTIC_COD)));

    public static final DeferredItem<Item> HARP_SEAL_SPAWN_EGG = ITEMS.register("harp_seal_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.HARP_SEAL, 0xcecec6, 0x676664, new Item.Properties()));

    public static final DeferredItem<Item> ARCTIC_COD_SPAWN_EGG = ITEMS.register("arctic_cod_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ARCTIC_COD,0xc5b8a6 , 0x7c8e92, new Item.Properties()));

    public static final DeferredItem<Item> SNOW_GLOBE = ITEMS.register("snow_globe",
            () -> new BlockItem(ModBlocks.SNOW_GLOBE.get(), new Item.Properties().stacksTo(16)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.bountifulseals.snow_globe1"));
                    tooltipComponents.add(Component.translatable("tooltip.bountifulseals.snow_globe2"));

                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
