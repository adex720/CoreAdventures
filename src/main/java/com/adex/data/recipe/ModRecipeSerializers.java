package com.adex.data.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipeSerializers {

    public static final RecipeSerializer<SplashArrowRecipe> SPLASH_ARROW = register("crafting_special_splasharrow", new CustomRecipe.Serializer<>(SplashArrowRecipe::new));

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String name, S recipeSerializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, name, recipeSerializer);
    }

    public static void initialize() {
    }
}
