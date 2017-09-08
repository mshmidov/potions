package com.mshmidov.potions;

import com.google.common.collect.Multiset;
import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.ingredent.KnownIngredient;
import com.mshmidov.potions.output.RecipeText;
import com.mshmidov.potions.potion.ComplexRecipe;
import com.mshmidov.potions.potion.Potion;
import com.mshmidov.potions.potion.Recipe;
import com.mshmidov.potions.potion.SimpleRecipe;

public enum KnownRecipe implements Recipe {

    SLEEPING_DRAUGHT(SimpleRecipe.forVerb(Verb.INDUCE)
            .named("Сонное зелье")
            .onRound(1).add(KnownIngredient.EELS_EYES, KnownIngredient.EELS_EYES)
            .onRound(3).add(KnownIngredient.LAVENDER_FLOWERS)
            .onRound(4).add(KnownIngredient.VALERIAN_SPRIGS)
            .onRound(6).finish()),

    LOVE_POTION(ComplexRecipe.named("Любовное зелье")
            .with(SimpleRecipe.forVerb(Verb.INDUCE)
                    .onRound(1).add(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(3).add(KnownIngredient.WHITE_ASPHODELUS_FLOWERS)
                    .onRound(4).add(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(5).finish())
            .with(SimpleRecipe.forVerb(Verb.BIND)
                    .onRound(1).add(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(3).add(KnownIngredient.EELS_EYES, KnownIngredient.LAVENDER_FLOWERS)
                    .onRound(4).finish())
            .finish()),

    SLEEPLESS_DRAUGHT(SimpleRecipe.forVerb(Verb.DECREASE)
            .named("Бессонное зелье")
            .onRound(1).add(KnownIngredient.EELS_EYES, KnownIngredient.EELS_EYES)
            .onRound(3).add(KnownIngredient.LAVENDER_FLOWERS)
            .onRound(4).add(KnownIngredient.VALERIAN_SPRIGS)
            .onRound(6).finish()),

    INDIFFERENCE_POTION(ComplexRecipe.named("Отворотное зелье")
            .with(SimpleRecipe.forVerb(Verb.INDUCE).named("Отворотное зелье")
                    .onRound(1).add(KnownIngredient.VALERIAN_SPRIGS, KnownIngredient.VALERIAN_SPRIGS)
                    .onRound(3).add(KnownIngredient.EELS_EYES, KnownIngredient.VALERIAN_SPRIGS)
                    .onRound(5).finish())
            .with(SimpleRecipe.forVerb(Verb.DAMAGE).named("Отворотное зелье")
                    .onRound(1).add(KnownIngredient.WHITE_ASPHODELUS_FLOWERS, KnownIngredient.WHITE_ASPHODELUS_FLOWERS)
                    .onRound(3).add(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(5).finish())
            .finish()),

    LIVING_DEATH_DRAUGHT(ComplexRecipe.named("Напиток живой смерти")
            .with(SimpleRecipe.forVerb(Verb.INDUCE)
                    .named("Напиток живой смерти")
                    .onRound(1).add(KnownIngredient.HOLLY_BERRY)
                    .onRound(2).add(KnownIngredient.WORMWOOD_LEAVES, KnownIngredient.WORMWOOD_LEAVES.toFire())
                    .onRound(3).add(KnownIngredient.MANDRAKE_ROOT, KnownIngredient.NARTHECIUM_FLOWERS)
                    .onRound(4).add(KnownIngredient.SAGE_LEAF)
                    .onRound(5).finish())
            .with(SimpleRecipe.forVerb(Verb.INDUCE)
                    .named("Напиток живой смерти")
                    .onRound(1).add(KnownIngredient.FROG_BRAIN, KnownIngredient.HORNED_LIZARD_BLOOD)
                    .onRound(2).add(KnownIngredient.ROMAN_SNAIL, KnownIngredient.ROMAN_SNAIL)
                    .onRound(3).add(KnownIngredient.FROG_BRAIN, KnownIngredient.HORNED_LIZARD_BLOOD)
                    .onRound(4).add(KnownIngredient.COCKROACH)
                    .onRound(6).finish())
            .with(SimpleRecipe.forVerb(Verb.INDUCE)
                    .named("Напиток живой смерти")
                    .onRound(1).add(KnownIngredient.SOPOPHORA_BEAN, KnownIngredient.SOPOPHORA_BEAN)
                    .onRound(2).add(KnownIngredient.SNAKE_EYE)
                    .onRound(4).finish())
            .finish()),

    VIGOR_POTION(ComplexRecipe.named("Ободряющее зелье")
            .with(SimpleRecipe.forVerb(Verb.INDUCE)
                    .named("Ободряющее зелье")
                    .onRound(1).add(KnownIngredient.FLIXWEED_SEEDS, KnownIngredient.FLIXWEED_SEEDS)
                    .onRound(2).add(KnownIngredient.EELS_EYES, KnownIngredient.EELS_EYES)
                    .onRound(3).add(KnownIngredient.HOLLY_BERRY, KnownIngredient.HOLLY_BERRY)
                    .onRound(4).add(KnownIngredient.WHITE_ASPHODELUS_FLOWERS.toFire(), KnownIngredient.HORNED_LIZARD_BLOOD)
                    .onRound(6).finish())
            .with(SimpleRecipe.forVerb(Verb.DECREASE)
                    .named("Ободряющее зелье")
                    .onRound(1).add(KnownIngredient.SOPOPHORA_BEAN, KnownIngredient.SOPOPHORA_BEAN)
                    .onRound(2).add(KnownIngredient.SOPOPHORA_BEAN, KnownIngredient.SNAKE_EYE)
                    .onRound(4).finish())
            .finish());

    private final Recipe recipe;

    private KnownRecipe(Recipe recipe) {
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
    public Multiset<Ingredient> getAllIngredients() {
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
