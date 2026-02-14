package net.rusticpotatoes.bountifulseals.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BEACH_BALL)
                .requires(Items.LEATHER)
                .requires(Items.WHITE_WOOL)
                .requires(Items.RED_WOOL)
                .requires(Items.STRING)
                .unlockedBy("has_wool", has(ItemTags.WOOL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRATE)
                .pattern("IWI")
                .pattern("WCW")
                .pattern("IWI")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('W', Tags.Items.STRIPPED_WOODS)
                .define('C', Tags.Items.CHESTS_WOODEN)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON)).save(recipeOutput);



        smeltingRecipe(recipeOutput, List.of(ModItems.ARCTIC_COD), RecipeCategory.FOOD, ModItems.COOKED_ARCTIC_COD.get(),
                0.25f, 200, "arctic_cod" );

        smokingRecipe(recipeOutput, List.of(ModItems.ARCTIC_COD), RecipeCategory.FOOD, ModItems.COOKED_ARCTIC_COD.get(),
                0.25f, 100, "arctic_cod" );


    }


    protected static void smeltingRecipe(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                        float pExperience, int pCookingTIme, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void smokingRecipe(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smoking");
    }

    protected static <T extends AbstractCookingRecipe> void cookingRecipe(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, BountifulSeals.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));}
    }
}

