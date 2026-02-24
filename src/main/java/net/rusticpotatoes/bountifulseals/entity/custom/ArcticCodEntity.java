package net.rusticpotatoes.bountifulseals.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.rusticpotatoes.bountifulseals.item.ModItems;


public class ArcticCodEntity extends Cod {
    public ArcticCodEntity(EntityType<? extends Cod> entityType, Level level) {
        super(entityType, level);
    }
    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.ARCTIC_COD_BUCKET.get());
    }

    public static boolean checkArcticCodSpawnRules(
            EntityType<? extends ArcticCodEntity> arcticCod, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random
    ) {
        int i = level.getSeaLevel();
        int j = i - 3;
        return pos.getY() >= j
                && pos.getY() <= i
                && level.getFluidState(pos.below()).is(FluidTags.WATER)
                && level.getBlockState(pos.above()).is(Blocks.WATER);
    }
}