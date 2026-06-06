package net.rusticpotatoes.bountifulseals.datagen;

import com.sun.jna.platform.win32.WinDef;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.rusticpotatoes.bountifulseals.BountifulSeals;
import net.rusticpotatoes.bountifulseals.block.ModBlocks;
import net.rusticpotatoes.bountifulseals.item.ModItems;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SUGAR_CRYSTAL, 4)
                .requires(ModBlocks.SUGAR_CRYSTAL_BLOCK)
                .unlockedBy("has_sugar_crystal_block", has(ModBlocks.SUGAR_CRYSTAL_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SUGAR_CRYSTAL_BLOCK)
                .pattern("SS")
                .pattern("SS")
                .define('S', ModItems.SUGAR_CRYSTAL)
                .unlockedBy("has_sugar_crystal", has(ModItems.SUGAR_CRYSTAL)).save(recipeOutput);

        stairRecipe(recipeOutput, ModBlocks.SUGAR_CRYSTAL_STAIRS, ModBlocks.SUGAR_CRYSTAL_BLOCK, true);
        slabRecipe(recipeOutput, ModBlocks.SUGAR_CRYSTAL_SLAB, ModBlocks.SUGAR_CRYSTAL_BLOCK, true);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRATE)
                .pattern("IWI")
                .pattern("WCW")
                .pattern("IWI")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('W', Tags.Items.STRIPPED_WOODS)
                .define('C', Tags.Items.CHESTS_WOODEN)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SNOW_GLOBE)
                .pattern("WGS")
                .pattern("GCG")
                .pattern("DDD")
                .define('W', ItemTags.WOODEN_BUTTONS)
                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                .define('S', ItemTags.STONE_BUTTONS)
                .define('C', ModBlocks.SNOW_CLUMPS)
                .define('D', Tags.Items.COBBLESTONES_DEEPSLATE)
                .unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CANDY_LOLLIPOP)
                .pattern("RSY")
                .pattern("SSS")
                .pattern("JSB")
                .define('R', Items.RED_DYE)
                .define('S', ModItems.SUGAR_CRYSTAL)
                .define('Y', Items.YELLOW_DYE)
                .define('J', Items.STICK)
                .define('B', Items.BLUE_DYE)
                .unlockedBy("has_sugar_crystal", has(ModItems.SUGAR_CRYSTAL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.PROPELLER_HAT)
                .pattern("GIY")
                .pattern("B R")
                .define('G', Blocks.GREEN_WOOL)
                .define('I', Items.IRON_INGOT)
                .define('Y', Blocks.YELLOW_WOOL)
                .define('B', Blocks.BLUE_WOOL)
                .define('R', Blocks.RED_WOOL)
                .unlockedBy("has_wool", has(ItemTags.WOOL)).save(recipeOutput);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.SNOW_CLUMPS, 4)
                .requires(Items.SNOWBALL, 2)
                .unlockedBy("has_snowball", has(Items.SNOWBALL)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.SNOWBALL, 2)
                .requires(ModBlocks.SNOW_CLUMPS, 4)
                .unlockedBy("has_snow_clumps", has(ModBlocks.SNOW_CLUMPS)).save(recipeOutput, "snowball_from_snow_clumps");




        smeltingRecipe(recipeOutput, List.of(ModItems.ARCTIC_COD), RecipeCategory.FOOD,
                ModItems.COOKED_ARCTIC_COD.get(), "arctic_cod");

        smokingRecipe(recipeOutput, List.of(ModItems.ARCTIC_COD), RecipeCategory.FOOD, ModItems.COOKED_ARCTIC_COD.get(),
                0.25f, 100, "arctic_cod");

        smeltingRecipe(recipeOutput, List.of(Items.SUGAR), RecipeCategory.MISC,
                ModItems.SUGAR_CRYSTAL, "sugar_crystal");
    }


    private static void stairRecipe(RecipeOutput output, ItemLike result, ItemLike ingredient, boolean isStoneCuttable) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4).pattern("#  ").pattern("## ").pattern("###").define('#', ingredient).unlockedBy(ingredient.asItem().getDescriptionId(), has(ingredient)).save(output);
        if (isStoneCuttable) {
            stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, result, ingredient);
        }
    }

    private static void slabRecipe(RecipeOutput output, ItemLike result, ItemLike ingredient, boolean isStoneCuttable) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 6).pattern("###").define('#', ingredient).unlockedBy(ingredient.asItem().getDescriptionId(), has(ingredient)).save(output);
        if (isStoneCuttable) {
            stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, result, ingredient, 2);
        }
    }

    protected static void smeltingRecipe(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                         String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                0.25f, 200, pGroup, "_from_smelting");
    }

    protected static void smeltingRecipe(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                         float pExperience, int pCookingTIme, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void smokingRecipe(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory,
                                        ItemLike pResult, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                0.25f, 100, pGroup, "_from_smoking");
    }

    protected static void smokingRecipe(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                        float pExperience, int pCookingTIme, String pGroup) {
        cookingRecipe(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smoking");
    }

    protected static <T extends AbstractCookingRecipe> void cookingRecipe(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, BountifulSeals.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

