package net.rusticpotatoes.bountifulseals.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rusticpotatoes.bountifulseals.BountifulSeals;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, BountifulSeals.MOD_ID);

    public static final Holder<Potion> SILLINESS = POTIONS.register("silliness_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SILLINESS, 12000, 0, true, true, true)));

    public static final Holder<Potion> TOMS_FOOLERY = POTIONS.register("toms_foolery_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.TOMS_FOOLERY, 12000, 0, true, true, true)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}