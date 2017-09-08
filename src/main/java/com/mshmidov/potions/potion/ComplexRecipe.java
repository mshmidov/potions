package com.mshmidov.potions.potion;

import java.util.ArrayDeque;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.mshmidov.potions.ingredent.IngredientDefinition;
import com.mshmidov.potions.output.ComplexRecipeText;
import com.mshmidov.potions.output.RecipeText;

public final class ComplexRecipe implements Brewable {

    private final String name;

    private final List<SimpleRecipe> recipes;

    public ComplexRecipe(String name, SimpleRecipe... recipes) {
        this.name = name;
        this.recipes = ImmutableList.copyOf(recipes);
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
    public Multiset<IngredientDefinition> getAllIngredients() {
        final ImmutableMultiset.Builder<IngredientDefinition> result = ImmutableMultiset.<IngredientDefinition> builder();

        recipes.forEach(recipe -> recipe.getAllIngredients().forEachEntry((ingredient, count) -> result.addCopies(ingredient, count)));

        return result.build();
    }

    @Override
    public RecipeText asText() {
        return new ComplexRecipeText(this);
    }

}
