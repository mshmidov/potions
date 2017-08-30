package com.mshmidov.potions;

import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.potion.Brewable;
import com.mshmidov.potions.potion.ComplexRecipe;
import com.mshmidov.potions.potion.Potion;
import com.mshmidov.potions.potion.Recipe;

public class Main {

    public static void main(String[] args) {

        final Brewable recipe = newIndifferencePotion();

        final Potion potion = recipe.brew();
        System.out.println();
        System.out.println(potion);
        System.out.println(recipe);
    }

    private static Brewable newSleepingDraught() {
        final Recipe recipe = Recipe.forVerb(Verb.INDUCE)
                .named("Сонное зелье")
                .onRound(1).add(Ingredient.EELS_EYES.toFire(), Ingredient.EELS_EYES.toFire())
                .onRound(3).add(Ingredient.LAVENDER_FLOWERS.asIs())
                .onRound(4).add(Ingredient.VALERIAN_SPRIGS.asIs())
                .onRound(6).finish();
        return recipe;
    }

    private static Brewable newLovePotion() {

        return new ComplexRecipe(
                Recipe.forVerb(Verb.INDUCE).named("Любовное зелье")
                        .onRound(1).add(Ingredient.LOVAGE_FLOWERS.toWater())
                        .onRound(3).add(Ingredient.WHITE_ASPHODELUS_FLOWERS.asIs())
                        .onRound(4).add(Ingredient.LOVAGE_FLOWERS.toWater())
                        .onRound(5).finish(),
                Recipe.forVerb(Verb.BIND).named("Любовное зелье")
                        .onRound(1).add(Ingredient.LOVAGE_FLOWERS.asIs())
                        .onRound(3).add(Ingredient.EELS_EYES.asIs(), Ingredient.LAVENDER_FLOWERS.asIs())
                        .onRound(4).finish());
    }

    private static Brewable newSleeplessDraught() {
        final Recipe recipe = Recipe.forVerb(Verb.DECREASE)
                .named("Бессонное зелье")
                .onRound(1).add(Ingredient.EELS_EYES.toFire(), Ingredient.EELS_EYES.toFire())
                .onRound(3).add(Ingredient.LAVENDER_FLOWERS.asIs())
                .onRound(4).add(Ingredient.VALERIAN_SPRIGS.asIs())
                .onRound(6).finish();
        return recipe;
    }

    private static Brewable newIndifferencePotion() {

        return new ComplexRecipe(
                Recipe.forVerb(Verb.INDUCE).named("Отворотное зелье")
                        .onRound(1).add(Ingredient.VALERIAN_SPRIGS.asIs(), Ingredient.VALERIAN_SPRIGS.asIs())
                        .onRound(3).add(Ingredient.EELS_EYES.toFire(), Ingredient.VALERIAN_SPRIGS.asIs())
                        .onRound(5).finish(),
                Recipe.forVerb(Verb.DAMAGE).named("Отворотное зелье")
                        .onRound(1).add(Ingredient.WHITE_ASPHODELUS_FLOWERS.asIs(), Ingredient.WHITE_ASPHODELUS_FLOWERS.asIs())
                        .onRound(3).add(Ingredient.LOVAGE_FLOWERS.asIs())
                        .onRound(5).finish());
    }
}
