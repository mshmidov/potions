package com.mshmidov.potions;

import com.google.common.collect.Multiset;
import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.ingredent.IngredientDefinition;
import com.mshmidov.potions.output.RecipeText;
import com.mshmidov.potions.potion.Brewable;
import com.mshmidov.potions.potion.ComplexRecipe;
import com.mshmidov.potions.potion.Potion;
import com.mshmidov.potions.potion.Recipe;

public enum KnownRecipe implements Brewable {

    SLEEPING_DRAUGHT(Recipe.forVerb(Verb.INDUCE)
            .named("Сонное зелье")
            .onRound(1).add(Ingredient.EELS_EYES.asIs(), Ingredient.EELS_EYES.asIs())
            .onRound(3).add(Ingredient.LAVENDER_FLOWERS.asIs())
            .onRound(4).add(Ingredient.VALERIAN_SPRIGS.asIs())
            .onRound(6).finish()),

    LOVE_POTION(new ComplexRecipe("Любовное зелье",
            Recipe.forVerb(Verb.INDUCE).named("Любовное зелье")
                    .onRound(1).add(Ingredient.LOVAGE_FLOWERS.asIs())
                    .onRound(3).add(Ingredient.WHITE_ASPHODELUS_FLOWERS.asIs())
                    .onRound(4).add(Ingredient.LOVAGE_FLOWERS.asIs())
                    .onRound(5).finish(),
            Recipe.forVerb(Verb.BIND).named("Любовное зелье")
                    .onRound(1).add(Ingredient.LOVAGE_FLOWERS.asIs())
                    .onRound(3).add(Ingredient.EELS_EYES.asIs(), Ingredient.LAVENDER_FLOWERS.asIs())
                    .onRound(4).finish())),

    SLEEPLESS_DRAUGHT(Recipe.forVerb(Verb.DECREASE)
            .named("Бессонное зелье")
            .onRound(1).add(Ingredient.EELS_EYES.asIs(), Ingredient.EELS_EYES.asIs())
            .onRound(3).add(Ingredient.LAVENDER_FLOWERS.asIs())
            .onRound(4).add(Ingredient.VALERIAN_SPRIGS.asIs())
            .onRound(6).finish()),

    INDIFFERENCE_POTION(new ComplexRecipe("Отворотное зелье",
            Recipe.forVerb(Verb.INDUCE).named("Отворотное зелье")
                    .onRound(1).add(Ingredient.VALERIAN_SPRIGS.asIs(), Ingredient.VALERIAN_SPRIGS.asIs())
                    .onRound(3).add(Ingredient.EELS_EYES.asIs(), Ingredient.VALERIAN_SPRIGS.asIs())
                    .onRound(5).finish(),
            Recipe.forVerb(Verb.DAMAGE).named("Отворотное зелье")
                    .onRound(1).add(Ingredient.WHITE_ASPHODELUS_FLOWERS.asIs(), Ingredient.WHITE_ASPHODELUS_FLOWERS.asIs())
                    .onRound(3).add(Ingredient.LOVAGE_FLOWERS.asIs())
                    .onRound(5).finish())),

    LIVING_DEATH_DRAUGHT(new ComplexRecipe("Напиток живой смерти",
            Recipe.forVerb(Verb.INDUCE)
                    .named("Напиток живой смерти")
                    .onRound(1).add(Ingredient.HOLLY_BERRY.asIs())
                    .onRound(2).add(Ingredient.WORMWOOD_LEAVES.asIs(), Ingredient.WORMWOOD_LEAVES.toFire())
                    .onRound(3).add(Ingredient.MANDRAKE_ROOT.asIs(), Ingredient.NARTHECIUM_FLOWERS.asIs())
                    .onRound(4).add(Ingredient.SAGE_LEAF.asIs())
                    .onRound(5).finish(),
            Recipe.forVerb(Verb.INDUCE)
                    .named("Напиток живой смерти")
                    .onRound(1).add(Ingredient.FROG_BRAIN.asIs(), Ingredient.HORNED_LIZARD_BLOOD.asIs())
                    .onRound(2).add(Ingredient.ROMAN_SNAIL.asIs(), Ingredient.ROMAN_SNAIL.asIs())
                    .onRound(3).add(Ingredient.FROG_BRAIN.asIs(), Ingredient.HORNED_LIZARD_BLOOD.asIs())
                    .onRound(4).add(Ingredient.COCKROACH.asIs())
                    .onRound(6).finish(),
            Recipe.forVerb(Verb.INDUCE)
                    .named("Напиток живой смерти")
                    .onRound(1).add(Ingredient.SOPOPHORA_BEAN.asIs(), Ingredient.SOPOPHORA_BEAN.asIs())
                    .onRound(2).add(Ingredient.SNAKE_EYE.asIs())
                    .onRound(4).finish())),

    VIGOR_POTION(new ComplexRecipe("Ободряющее зелье",
            Recipe.forVerb(Verb.INDUCE)
                    .named("Ободряющее зелье")
                    .onRound(1).add(Ingredient.FLIXWEED_SEEDS.asIs(), Ingredient.FLIXWEED_SEEDS.asIs())
                    .onRound(2).add(Ingredient.EELS_EYES.asIs(), Ingredient.EELS_EYES.asIs())
                    .onRound(3).add(Ingredient.HOLLY_BERRY.asIs(), Ingredient.HOLLY_BERRY.asIs())
                    .onRound(4).add(Ingredient.WHITE_ASPHODELUS_FLOWERS.toFire(), Ingredient.HORNED_LIZARD_BLOOD.asIs())
                    .onRound(6).finish(),
            Recipe.forVerb(Verb.DECREASE)
                    .named("Ободряющее зелье")
                    .onRound(1).add(Ingredient.SOPOPHORA_BEAN.asIs(), Ingredient.SOPOPHORA_BEAN.asIs())
                    .onRound(2).add(Ingredient.SOPOPHORA_BEAN.asIs(), Ingredient.SNAKE_EYE.asIs())
                    .onRound(4).finish()));

    private final Brewable recipe;

    private KnownRecipe(Brewable recipe) {
        this.recipe = recipe;
    }

    @Override
    public String getName() {
        return recipe.getName();
    }

    @Override
    public Potion brew() {
        return recipe.brew();
    }

    @Override
    public Multiset<IngredientDefinition> getAllIngredients() {
        return recipe.getAllIngredients();
    }

    @Override
    public String toString() {
        return recipe.toString();
    }

    @Override
    public RecipeText asText() {
        return recipe.asText();
    }
}
