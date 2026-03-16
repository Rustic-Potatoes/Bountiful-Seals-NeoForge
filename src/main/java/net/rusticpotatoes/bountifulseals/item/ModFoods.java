package net.rusticpotatoes.bountifulseals.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties ARCTIC_COD = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .build();

    public static final FoodProperties COOKED_ARCTIC_COD = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.4F)
            .build();

}
