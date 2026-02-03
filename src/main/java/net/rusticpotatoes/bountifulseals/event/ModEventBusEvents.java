package net.rusticpotatoes.bountifulseals.event;

import net.minecraft.client.model.CodModel;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.entity.ModEntities;
import net.rusticpotatoes.bountifulseals.entity.client.arctic_cod.ArcticCodRenderer;
import net.rusticpotatoes.bountifulseals.entity.client.harp_seal.HarpSealModel;
import net.rusticpotatoes.bountifulseals.entity.client.harp_seal.HarpSealPupModel;
import net.rusticpotatoes.bountifulseals.entity.custom.ArcticCodEntity;
import net.rusticpotatoes.bountifulseals.entity.custom.HarpSealEntity;

@EventBusSubscriber(modid = BountifulSeals.MOD_ID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HarpSealModel.LAYER_LOCATION, HarpSealModel::createBodyLayer);
        event.registerLayerDefinition(HarpSealPupModel.LAYER_LOCATION, HarpSealPupModel::createBodyLayer);
        event.registerLayerDefinition(ArcticCodRenderer.LAYER_LOCATION, CodModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.HARP_SEAL.get(), HarpSealEntity.createAttribute().build());
        event.put(ModEntities.ARCTIC_COD.get(), ArcticCodEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        event.register(
                ModEntities.HARP_SEAL.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                HarpSealEntity::checkHarpSealSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
                );
    }
}
