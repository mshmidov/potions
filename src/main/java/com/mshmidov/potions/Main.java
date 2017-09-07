package com.mshmidov.potions;

import com.mshmidov.potions.output.RecipeText;
import com.mshmidov.potions.potion.Brewable;
import com.mshmidov.potions.potion.Potion;

public class Main {

    public static void main(String[] args) {

        final Brewable recipe = KnownRecipe.VIGOR_POTION;

        final Potion potion = recipe.brew();
        System.out.println();
        System.out.println(potion);

        final RecipeText recipeText = recipe.asText();
        recipeText.getIngredients().forEach(System.out::println);
        recipeText.getInstructions().forEach(System.out::println);
    }

}
