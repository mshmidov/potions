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
import com.mshmidov.potions.output.PotionText;
import com.mshmidov.potions.process.log.BrewingLog;
import com.mshmidov.potions.process.log.NewSimplePotion;
import com.mshmidov.potions.process.log.Synthesis;
import com.mshmidov.potions.process.log.TooMuchEffects;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.capitalize;

public final class SimplePotion implements Potion {

    private final SimpleRecipe recipe;

    private final Table<Verb, Substance, Integer> effects;

    private final Table<Verb, Substance, Integer> sideEffects;

    private final BrewingLog brewingLog;

    public static SimplePotion create(SimpleRecipe recipe, Optional<SimplePotion> base, Map<Substance, Integer> substances,
            BrewingLog brewingLog) {

        final BrewingLog log = new BrewingLog();
        base.map(Potion::getLog).ifPresent(log::addAll);
        log.addAll(brewingLog);

        final Table<Verb, Substance, Integer> effects = HashBasedTable.create();
        final Table<Verb, Substance, Integer> sideEffects = HashBasedTable.create();

        final int maxConcentration = substances.values().stream()
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("SimplePotion cannot be created without any substances"));

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

        base.map(SimplePotion::getEffects).ifPresent(effects::putAll);
        base.map(SimplePotion::getSideEffects).ifPresent(sideEffects::putAll);

       log.addAll(performSynthesis(effects, Substance.THIRD_ORDER, Rules.SYNTHESIS_FOURTH_ORDER_COUNT));
       log.addAll(performSynthesis(effects, Substance.SECOND_ORDER, Rules.SYNTHESIS_THIRD_ORDER_COUNT));
       log.addAll(performSynthesis(effects, Substance.FIRST_ORDER, Rules.SYNTHESIS_SECOND_ORDER_COUNT));

        if ((effects.size() - base.map(SimplePotion::getEffects).map(Table::size).orElse(0)) > Rules.MAX_EFFECTS) {
            sideEffects.putAll(effects);
            effects.clear();
            log.add(new TooMuchEffects());
        }

        log.add(new NewSimplePotion(effects, sideEffects));

        return new SimplePotion(recipe, effects, sideEffects, log);
    }

    private static BrewingLog performSynthesis(
            Table<Verb, Substance, Integer> effects,
            SetMultimap<Substance, Substance> higherOrder,
            int synthesisTreshold) {

        final BrewingLog log = new BrewingLog();

        effects.rowKeySet().forEach(verb -> {

            higherOrder.keys().forEach(higherSubstance -> {

                final SetView<Substance> lowerSubstances = Sets.intersection(
                        higherOrder.get(higherSubstance),
                        ImmutableSet.copyOf(effects.row(verb).keySet()));

                if (lowerSubstances.size() >= synthesisTreshold) {
                    final Integer minimalQuantity = lowerSubstances.stream()
                            .map(s -> effects.remove(verb, s))
                            .min(Integer::compareTo).orElse(0);

                    final int quantity = minimalQuantity + Optional.ofNullable(effects.get(verb, higherSubstance)).orElse(0);
                    effects.put(verb, higherSubstance, quantity);

                    log.add(new Synthesis(lowerSubstances, higherSubstance, quantity));
                }

            });
        });

        return log;
    }

    private SimplePotion(SimpleRecipe recipe,
            Table<Verb, Substance, Integer> effects,
            Table<Verb, Substance, Integer> sideEffects,
            BrewingLog brewingLog) {

        this.recipe = recipe;
        this.effects = ImmutableTable.copyOf(effects);
        this.sideEffects = ImmutableTable.copyOf(sideEffects);
        this.brewingLog = brewingLog;
    }

    @Override
    public SimpleRecipe getRecipe() {
        return recipe;
    }

    @Override
    public Verb getVerb() {
        return recipe.getVerb();
    }

    @Override
    public Table<Verb, Substance, Integer> getEffects() {
        return effects;
    }

    @Override
    public Table<Verb, Substance, Integer> getSideEffects() {
        return sideEffects;
    }

    @Override
    public BrewingLog getLog() {
        return brewingLog;
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

    @Override
    public PotionText asText() {
        return new PotionText(this);
    }
}
