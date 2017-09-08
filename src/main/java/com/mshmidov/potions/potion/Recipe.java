package com.mshmidov.potions.potion;

import com.google.common.collect.Multiset;
import com.mshmidov.potions.ingredent.IngredientDefinition;
import com.mshmidov.potions.output.RecipeText;

public interface Recipe {

    String getName();

    Potion brew();

    Multiset<IngredientDefinition> getAllIngredients();

    RecipeText asText();
}
