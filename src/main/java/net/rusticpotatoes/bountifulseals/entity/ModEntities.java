package net.rusticpotatoes.bountifulseals.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.entity.custom.ArcticCodEntity;
import net.rusticpotatoes.bountifulseals.entity.custom.HarpSealEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, BountifulSeals.MOD_ID);

    public static final Supplier<EntityType<HarpSealEntity>> HARP_SEAL =
            ENTITY_TYPES.register("harp_seal", () -> EntityType.Builder.of(HarpSealEntity::new, MobCategory.CREATURE)
                    .sized(1.1f, 0.6f).build("harp_seal"));

    public static final Supplier<EntityType<ArcticCodEntity>> ARCTIC_COD =
            ENTITY_TYPES.register("arctic_cod", () -> EntityType.Builder.of(ArcticCodEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.5F, 0.3F).eyeHeight(0.195F).clientTrackingRange(4).build("arctic_cod"));

    public static void register(IEventBus iEventBus) {
        ENTITY_TYPES.register(iEventBus);
    }
}
