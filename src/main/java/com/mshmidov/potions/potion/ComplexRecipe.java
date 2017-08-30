package com.mshmidov.potions.potion;

import java.util.ArrayDeque;
import java.util.List;

import com.google.common.collect.ImmutableList;

import static java.util.stream.Collectors.joining;

public final class ComplexRecipe implements Brewable {

    private final List<Recipe> recipes;

    public ComplexRecipe(Recipe... recipes) {
        this.recipes = ImmutableList.copyOf(recipes);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public Potion brew() {

        final ArrayDeque<Recipe> unbrewedRecipes = new ArrayDeque<>(recipes);
        Potion potion = null;

        while (!unbrewedRecipes.isEmpty()) {
            Recipe recipe = unbrewedRecipes.removeFirst();

            potion = recipe.brewOnBaseOf(potion);
        }

        return potion;
    }

    @Override
    public String toString() {
        return recipes.stream().map(r -> r.toInstructions()).collect(joining(System.getProperty("line.separator")));
    }
}
