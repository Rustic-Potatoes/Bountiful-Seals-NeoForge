package net.rusticpotatoes.bountifulseals.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
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

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        var biomes = context.lookup(Registries.BIOME);

        context.register(SPAWN_HARP_SEAL, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SNOWY_BEACH),
                        biomes.getOrThrow(Biomes.ICE_SPIKES),
                        biomes.getOrThrow(Biomes.FROZEN_OCEAN),
                        biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.HARP_SEAL.get(), 50, 1, 3))));
    }
}

