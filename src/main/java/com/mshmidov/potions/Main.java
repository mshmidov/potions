package com.mshmidov.potions;

import com.mshmidov.potions.output.PotionText;
import com.mshmidov.potions.output.RecipeText;
import com.mshmidov.potions.potion.Potion;
import com.mshmidov.potions.potion.Recipe;
import com.mshmidov.potions.process.log.BrewingLog;

import static java.util.stream.Collectors.joining;

public class Main {

    public static void main(String[] args) {

        final Recipe recipe = KnownRecipe.DRAUGHT_OF_BRIGHT_MIND;

        final Potion potion = recipe.brew();

        printLog(potion.getLog());
        System.out.println();
        printRecipe(potion);
    }

    private static void printLog(BrewingLog log) {
        log.forEach(e -> System.out.println(e.toString()));

    }

    private static void printRecipe(final Potion potion) {
        final PotionText potionText = potion.asText();
        System.out.println(potionText.getName());
        System.out.print("Эффекты: ");
        System.out.println(potionText.getEffects().stream().collect(joining("; ")));
        System.out.print("Побочные эффекты: ");
        System.out.println(potionText.getSideEffects().stream().collect(joining("; ")));

        final RecipeText recipeText = potion.getRecipe().asText();
        System.out.println();
        System.out.println("Ингредиенты");
        recipeText.getIngredients().forEach(System.out::println);

        System.out.println();
        System.out.println("Инструкции");
        recipeText.getInstructions().forEach(System.out::println);
    }

}
