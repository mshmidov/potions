package com.mshmidov.potions.potion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.output.ComplexRecipeText;
import com.mshmidov.potions.output.RecipeText;

public final class ComplexRecipe implements Recipe {

    private final String name;

    private final List<SimpleRecipe> recipes;

    public static Builder named(String name) {
        return new Builder(name);
    }

    private ComplexRecipe(Builder builder) {
        this.name = builder.name;
        this.recipes = ImmutableList.copyOf(builder.recipes);
    }

    @Override
    public String getName() {
        return name;
    }

    public List<SimpleRecipe> getRecipes() {
        return recipes;
    }

    @Override
    public Potion brew() {

        final ArrayDeque<SimpleRecipe> unbrewedRecipes = new ArrayDeque<>(recipes);
        Potion potion = null;

        while (!unbrewedRecipes.isEmpty()) {
            SimpleRecipe recipe = unbrewedRecipes.removeFirst();

            potion = recipe.brewOnBaseOf(potion);
        }

        return potion;
    }

    @Override
    public Multiset<Ingredient> getAllIngredients() {
        final ImmutableMultiset.Builder<Ingredient> result = ImmutableMultiset.<Ingredient> builder();

        recipes.forEach(recipe -> recipe.getAllIngredients().forEachEntry((ingredient, count) -> result.addCopies(ingredient, count)));

        return result.build();
    }

    @Override
    public RecipeText asText() {
        return new ComplexRecipeText(this);
    }

    public static final class Builder {

        private final String name;

        private final List<SimpleRecipe> recipes = new ArrayList<>();

        private Builder(String name) {
            this.name = name;
        }

        public Builder with(SimpleRecipe recipe) {
            recipes.add(recipe);
            return this;
        }

        public ComplexRecipe finish() {
            return new ComplexRecipe(this);
        }

    }
}
