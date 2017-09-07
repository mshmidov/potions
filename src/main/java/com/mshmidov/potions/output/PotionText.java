package com.mshmidov.potions.output;

import java.util.List;
import java.util.Map;

import com.mshmidov.potions.definition.EffectScale;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.potion.Potion;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.capitalize;

public class PotionText {

    private final Potion potion;

    public PotionText(Potion potion) {
        this.potion = potion;
    }

    public String getName() {
        return potion.getRecipe().getName();
    }

    public List<String> getEffects() {
        return potion.getEffects().rowKeySet().stream()
                .map(verb -> effectsToString(verb, potion.getEffects().row(verb)))
                .collect(toList());
    }

    public List<String> getSideEffects() {
        return potion.getSideEffects().rowKeySet().stream()
                .map(verb -> effectsToString(verb, potion.getSideEffects().row(verb)))
                .collect(toList());
    }

    private String effectsToString(Verb verb, Map<Substance, Integer> substances) {
        final StringBuilder result = new StringBuilder();

        result.append(capitalize(verb.toString().toLowerCase())).append(" ");

        result.append(substances.entrySet().stream()
                .map(e -> effectToString(verb, e.getKey(), e.getValue()))
                .collect(joining(", ")));

        return result.toString();
    }

    private String effectToString(Verb verb, Substance substance, int quantity) {
       return String.format("%s %s (%s)",
                capitalize(substance.toString().toLowerCase()),
                quantity,
                EffectScale.forEffect(verb, quantity).stream().map(EffectScale::toString).collect(joining(", ")));
    }
}
