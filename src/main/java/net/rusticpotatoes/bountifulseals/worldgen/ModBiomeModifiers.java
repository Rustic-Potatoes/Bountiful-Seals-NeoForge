package net.rusticpotatoes.bountifulseals.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.entity.ModEntities;

import java.util.List;

public class ModBiomeModifiers {

    private static ResourceKey<BiomeModifier> registerResourceKey(String id) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, id));
    }

    public static ResourceKey<BiomeModifier> SPAWN_HARP_SEAL = registerResourceKey("spawn_harp_seal");
    public static ResourceKey<BiomeModifier> ADD_SNOW_CLUMPS = registerResourceKey("add_snow_clumps");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SNOW_CLUMPS, new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.getOrThrow(Biomes.FROZEN_RIVER),
                                biomes.getOrThrow(Biomes.ICE_SPIKES),
                                biomes.getOrThrow(Biomes.FROZEN_OCEAN),
                                biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN),
                                biomes.getOrThrow(Biomes.SNOWY_BEACH),
                                biomes.getOrThrow(Biomes.SNOWY_PLAINS)
                        ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SNOW_CLUMPS_PLACED_KEY)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));

        context.register(SPAWN_HARP_SEAL, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SNOWY_BEACH),
                        biomes.getOrThrow(Biomes.ICE_SPIKES),
                        biomes.getOrThrow(Biomes.FROZEN_OCEAN),
                        biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.HARP_SEAL.get(), 50, 3, 5))));
    }
}

