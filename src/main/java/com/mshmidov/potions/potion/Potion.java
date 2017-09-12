package com.mshmidov.potions.potion;

import com.google.common.collect.Table;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.output.PotionText;
import com.mshmidov.potions.process.log.BrewingLog;

public interface Potion {

    Recipe getRecipe();

    Verb getVerb();

    Table<Verb, Substance, Integer> getEffects();

    Table<Verb, Substance, Integer> getSideEffects();

    PotionText asText();

    BrewingLog getLog();
}
