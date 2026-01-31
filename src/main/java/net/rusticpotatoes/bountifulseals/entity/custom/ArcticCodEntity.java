package net.rusticpotatoes.bountifulseals.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rusticpotatoes.bountifulseals.item.ModItems;


public class ArcticCodEntity extends Cod {
    public ArcticCodEntity(EntityType<? extends Cod> entityType, Level level) {
        super(entityType, level);
    }
    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.ARCTIC_COD_BUCKET.get());
    }


}