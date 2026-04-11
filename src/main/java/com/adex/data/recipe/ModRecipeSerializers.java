package com.adex.data.recipe;

import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;

public class ModRecipeSerializers {

    /*public static final RecipeSerializer<SplashArrowRecipe> SPLASH_ARROW = register("crafting_special_splasharrow", new RecipeSerializer<SplashArrowRecipe>(SplashArrowRecipe.MAP_CODEC, SplashArrowRecipe.STREAM_CODEC));

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String name, S recipeSerializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, name, recipeSerializer);
    }

    public static void initialize() {
    }*/
}
