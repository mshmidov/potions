package com.mshmidov.potions;

import com.mshmidov.potions.output.PotionText;
import com.mshmidov.potions.output.RecipeText;
import com.mshmidov.potions.potion.Brewable;
import com.mshmidov.potions.potion.Potion;

import static java.util.stream.Collectors.joining;

public class Main {

    public static void main(String[] args) {

        final Brewable recipe = KnownRecipe.VIGOR_POTION;

        final Potion potion = recipe.brew();

        final PotionText potionText = potion.asText();
        System.out.println();
        System.out.println(potionText.getName());
        System.out.print("Эффекты: ");
        System.out.println(potionText.getEffects().stream().collect(joining("; ")));
        System.out.print("Побочные эффекты: ");
        System.out.println(potionText.getSideEffects().stream().collect(joining("; ")));

        final RecipeText recipeText = recipe.asText();
        System.out.println();
        System.out.println("Ингредиенты");
        recipeText.getIngredients().forEach(System.out::println);

        System.out.println();
        System.out.println("Инструкции");
        recipeText.getInstructions().forEach(System.out::println);
    }

}
