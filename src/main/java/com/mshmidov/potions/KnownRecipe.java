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
            .onRound(3).add(KnownIngredient.DRIED_LAVENDER_FLOWERS)
            .onRound(4).add(KnownIngredient.VALERIAN_ROOT)
            .onRound(6).finish()),

    LOVE_POTION(ComplexRecipe.named("Любовное зелье")
            .with(SimpleRecipe.forVerb(Verb.INDUCE)
                    .onRound(1).add(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(3).add(KnownIngredient.WHITE_ASPHODELUS_FLOWERS)
                    .onRound(4).add(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(5).finish())
            .with(SimpleRecipe.forVerb(Verb.BIND)
                    .onRound(1).add(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(3).add(KnownIngredient.EELS_EYES, KnownIngredient.DRIED_LAVENDER_FLOWERS)
                    .onRound(4).finish())
            .finish()),

    SLEEPLESS_DRAUGHT(SimpleRecipe.forVerb(Verb.DECREASE)
            .named("Бессонное зелье")
            .onRound(1).add(KnownIngredient.EELS_EYES, KnownIngredient.EELS_EYES)
            .onRound(3).add(KnownIngredient.DRIED_LAVENDER_FLOWERS.toWater())
            .onRound(4).add(KnownIngredient.VALERIAN_ROOT)
            .onRound(6).finish()),

    INDIFFERENCE_POTION(ComplexRecipe.named("Отворотное зелье")
            .with(SimpleRecipe.forVerb(Verb.INDUCE).named("Отворотное зелье")
                    .onRound(1).add(KnownIngredient.VALERIAN_ROOT.toWater(), KnownIngredient.VALERIAN_ROOT.toWater())
                    .onRound(3).add(KnownIngredient.EELS_EYES, KnownIngredient.VALERIAN_ROOT.toWater())
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
            .finish()),

    LIBERATION_POTION(ComplexRecipe.named("Освобождающее зелье")
            .with(SimpleRecipe.forVerb(Verb.RESTORE)
                    .onRound(1).add(KnownIngredient.BAT_SPLEEN, KnownIngredient.SPOONWING)
                    .onRound(2).add(KnownIngredient.POND_TURTLE_TAIL.toFire())
                    .onRound(3).add(KnownIngredient.BAT_SPLEEN)
                    .onRound(4).add(KnownIngredient.SPOONWING, KnownIngredient.POND_TURTLE_TAIL.toFire())
                    .onRound(6).add(KnownIngredient.FAIRY_WINGS)
                    .onRound(6).add(KnownIngredient.PINE_BUD)
                    .onRound(7).addTwo(KnownIngredient.MANDRAKE_ROOT)
                    .onRound(10).finish())
            .finish()),

    FORGETFULNESS_POTION(ComplexRecipe.named("Зелье забывчивости")
            .with(SimpleRecipe.forVerb(Verb.DAMAGE)
                    .onRound(1).addTwo(KnownIngredient.PEPPERMINT_LEAVES)
                    .onRound(3).add(KnownIngredient.COMMON_MUGWORT_LEAVES)
                    .onRound(5).add(KnownIngredient.PEPPERMINT_LEAVES)
                    .onRound(7).add(KnownIngredient.COMMON_MUGWORT_LEAVES)
                    .onRound(8).add(KnownIngredient.RUNESPOOR_EGG, KnownIngredient.MOONSTONE)
                    .onRound(13).finish())
            .with(SimpleRecipe.forVerb(Verb.BIND)
                    .onRound(1).addTwo(KnownIngredient.PINE_BUD)
                    .onRound(2).add(KnownIngredient.KNOTWEED_LEAVES)
                    .onRound(4).finish())
            .finish()),

    MEMORY_RESTORATION_POTION(ComplexRecipe.named("Зелье восстановления памяти")
            .with(SimpleRecipe.forVerb(Verb.RESTORE)
                    .onRound(1).addTwo(KnownIngredient.PEPPERMINT_LEAVES)
                    .onRound(3).add(KnownIngredient.COMMON_MUGWORT_LEAVES.toFire())
                    .onRound(5).add(KnownIngredient.PEPPERMINT_LEAVES.toFire())
                    .onRound(7).add(KnownIngredient.COMMON_MUGWORT_LEAVES.toFire())
                    .onRound(8).add(KnownIngredient.RUNESPOOR_EGG, KnownIngredient.SAGE_LEAF)
                    .onRound(13).finish())
            .finish()),

    AMORTENTIA(ComplexRecipe.named("Амортенция")
            .with(SimpleRecipe.forVerb(Verb.INDUCE)
                    .onRound(1).addTwo(KnownIngredient.BLACK_HELLEBORE_LEAVES)
                    .onRound(2).addTwo(KnownIngredient.NIGHTSHADE_BERRY)
                    .onRound(7).addTwo(KnownIngredient.WHITE_ASPHODELUS_FLOWERS)
                    .onRound(9).addTwo(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(11).addTwo(KnownIngredient.BLACK_HELLEBORE_LEAVES)
                    .onRound(12).addTwo(KnownIngredient.NIGHTSHADE_BERRY)
                    .onRound(17).finish())
            .with(SimpleRecipe.forVerb(Verb.DECREASE)
                    .onRound(1).addTwo(KnownIngredient.HORNED_LIZARD_BLOOD)
                    .onRound(3).addTwo(KnownIngredient.FLIXWEED_SEEDS.toWater())
                    .onRound(4).finish())
            .with(SimpleRecipe.forVerb(Verb.INCREASE)
                    .onRound(1).add(KnownIngredient.KNOTWEED_LEAVES)
                    .onRound(3).add(KnownIngredient.SNAKE_EYE)
                    .onRound(5).add(KnownIngredient.BICORN_HORN_POWDER)
                    .onRound(7).finish())
            .with(SimpleRecipe.forVerb(Verb.BIND)
                    .onRound(1).add(KnownIngredient.LOVAGE_FLOWERS)
                    .onRound(3).add(KnownIngredient.EELS_EYES, KnownIngredient.DRIED_LAVENDER_FLOWERS)
                    .onRound(4).finish())
            .finish()),

    CALMING_DRAUGHT(ComplexRecipe.named("Успокаивающее зелье")
            .with(SimpleRecipe.forVerb(Verb.INDUCE)
                    .onRound(1).addTwo(KnownIngredient.FLY_AMANITA.toFire())
                    .onRound(5).add(KnownIngredient.DRIED_LAVENDER_FLOWERS)
                    .onRound(6).add(KnownIngredient.FLY_AMANITA.toFire())
                    .onRound(12).finish())
            .finish()),

    DRAUGHT_OF_BRIGHT_MIND(ComplexRecipe.named("Напиток ясного ума")
            .with(SimpleRecipe.forVerb(Verb.INCREASE)
                    .onRound(1).addTwo(KnownIngredient.RUNESPOOR_EGG)
                    .onRound(3).add(KnownIngredient.SAGE_LEAF, KnownIngredient.COMMON_MUGWORT_LEAVES)
                    .onRound(6).addTwo(KnownIngredient.SNAKE_EYE)
                    .onRound(8).finish())
            .with(SimpleRecipe.forVerb(Verb.INCREASE)
                    .onRound(1).addTwo(KnownIngredient.SAGE_LEAF)
                    .onRound(3).add(KnownIngredient.DRIED_SCARAB)
                    .onRound(4).add(KnownIngredient.LIONFISH_RAY)
                    .onRound(5).finish())
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
    public Multiset<Verb> getAllVerbs() {
        return recipe.getAllVerbs();
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
