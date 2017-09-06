package com.mshmidov.potions.potion;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.collect.Table;
import com.mshmidov.potions.definition.EffectScale;
import com.mshmidov.potions.definition.Rules;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.definition.Verb;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.capitalize;

public class Potion {

    private final Recipe recipe;

    private final Table<Verb, Substance, Integer> effects;

    private final Table<Verb, Substance, Integer> sideEffects;

    public static Potion create(Recipe recipe, Optional<Potion> base, Map<Substance, Integer> substances) {

        final Table<Verb, Substance, Integer> effects = HashBasedTable.create();
        final Table<Verb, Substance, Integer> sideEffects = HashBasedTable.create();

        final int maxConcentration = substances.values().stream()
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Potion cannot be created without any substances"));

        final int secondMaxConcentration = substances.values().stream()
                .filter(i -> i < maxConcentration)
                .max(Integer::compareTo)
                .orElse(0);

        if (maxConcentration >= Rules.MINIMAL_EFFECT_STRENGTH
                && maxConcentration >= secondMaxConcentration * Rules.MINIMAL_EFFECT_RATIO) {

            substances.forEach((substance, quantity) -> {
                if (quantity == maxConcentration) {
                    effects.put(recipe.getVerb(), substance, quantity);

                } else if (quantity > 0) {
                    sideEffects.put(recipe.getVerb(), substance, quantity);
                }
            });

        } else {
            substances.forEach((substance, quantity) -> sideEffects.put(recipe.getVerb(), substance, quantity));
        }

        base.map(Potion::getEffects).ifPresent(effects::putAll);
        base.map(Potion::getSideEffects).ifPresent(sideEffects::putAll);

        performSynthesis(effects, Substance.THIRD_ORDER, Rules.SYNTHESIS_FOURTH_ORDER_COUNT);
        performSynthesis(effects, Substance.SECOND_ORDER, Rules.SYNTHESIS_THIRD_ORDER_COUNT);
        performSynthesis(effects, Substance.FIRST_ORDER, Rules.SYNTHESIS_SECOND_ORDER_COUNT);

        if ((effects.size() - base.map(Potion::getEffects).map(Table::size).orElse(0)) > Rules.MAX_EFFECTS) {
            sideEffects.putAll(effects);
            effects.clear();

            System.out.printf("too much effects in potion; everything is converted to side effects%n");
        }

        return new Potion(recipe, effects, sideEffects);
    }

    private static void performSynthesis(
            Table<Verb, Substance, Integer> effects,
            SetMultimap<Substance, Substance> higherOrder,
            int synthesisTreshold) {

        effects.rowKeySet().forEach(verb -> {

            higherOrder.keys().forEach(higherSubstance -> {

                final SetView<Substance> lowerSubstances = Sets.intersection(
                        higherOrder.get(higherSubstance),
                        ImmutableSet.copyOf(effects.row(verb).keySet()));

                if (lowerSubstances.size() >= synthesisTreshold) {
                    final Integer minimalQuantity = lowerSubstances.stream()
                            .map(s -> effects.remove(verb, s))
                            .min(Integer::compareTo).orElse(0);

                    effects.put(verb, higherSubstance, minimalQuantity + Optional.ofNullable(effects.get(verb, higherSubstance)).orElse(0));
                    System.out.println("Synthesis of " + lowerSubstances + " into " + higherSubstance);
                }

            });
        });
    }

    private Potion(Recipe recipe, Table<Verb, Substance, Integer> effects, Table<Verb, Substance, Integer> sideEffects) {
        this.recipe = recipe;
        this.effects = ImmutableTable.copyOf(effects);
        this.sideEffects = ImmutableTable.copyOf(sideEffects);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Verb getVerb() {
        return recipe.getVerb();
    }

    public Table<Verb, Substance, Integer> getEffects() {
        return effects;
    }

    public Table<Verb, Substance, Integer> getSideEffects() {
        return sideEffects;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append(String.format("%s%n", recipe.getName()));

        if (!effects.isEmpty()) {
            result.append(effects.rowKeySet().stream()
                    .map(verb -> String.format("%s %s",
                            capitalize(verb.toString().toLowerCase()),
                            effects.row(verb).entrySet().stream()
                                    .map(e -> String.format("%s %s (%s)",
                                            capitalize(e.getKey().toString().toLowerCase()),
                                            e.getValue(),
                                            EffectScale.forEffect(verb, e.getValue())))
                                    .collect(joining(", "))))
                    .collect(joining("; ", "Эффекты: ", "." + System.getProperty("line.separator"))));
        }

        if (!sideEffects.isEmpty()) {
            result.append(sideEffects.rowKeySet().stream()
                    .map(verb -> String.format("%s %s",
                            capitalize(verb.toString().toLowerCase()),
                            sideEffects.row(verb).entrySet().stream()
                                    .map(e -> String.format("%s %s (%s)",
                                            capitalize(e.getKey().toString().toLowerCase()),
                                            e.getValue(),
                                            EffectScale.forEffect(verb, e.getValue())))
                                    .collect(joining(", "))))
                    .collect(joining("; ", "Побочные эффекты: ", "." + System.getProperty("line.separator"))));
        }
        return result.toString();
    }

}
