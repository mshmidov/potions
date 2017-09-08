package com.mshmidov.potions.potion;

import com.google.common.collect.Table;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.output.PotionText;

final class ComplexPotion implements Potion {

    private final ComplexRecipe recipe;

    private final SimplePotion finalPotion;

    public ComplexPotion(ComplexRecipe recipe, SimplePotion finalPotion) {
        this.recipe = recipe;
        this.finalPotion = finalPotion;
    }

    @Override
    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public Verb getVerb() {
        return finalPotion.getVerb();
    }

    @Override
    public Table<Verb, Substance, Integer> getEffects() {
        return finalPotion.getEffects();
    }

    @Override
    public Table<Verb, Substance, Integer> getSideEffects() {
        return finalPotion.getSideEffects();
    }

    @Override
    public PotionText asText() {
        return new PotionText(this);
    }
}
