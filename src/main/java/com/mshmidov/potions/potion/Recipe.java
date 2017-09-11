package com.mshmidov.potions.potion;

import com.google.common.collect.Multiset;
import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.output.RecipeText;

public interface Recipe {

    String getName();

    Potion brew();

    Multiset<Verb> getAllVerbs();

    Multiset<Ingredient> getAllIngredients();

    RecipeText asText();
}
