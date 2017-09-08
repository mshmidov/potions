package com.mshmidov.potions.ingredent;

import java.util.Map;

import com.mshmidov.potions.definition.Element;
import com.mshmidov.potions.definition.Substance;

public interface Ingredient {

    String getName();

    Element getElement();

    Map<Substance, Integer> getSubstances();

    AddedIngredient mutableCopy();

    Ingredient toFire();

    Ingredient toWater();

    Ingredient toEarth();

}
