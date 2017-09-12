package com.mshmidov.potions.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.ingredent.AddedIngredient;
import com.mshmidov.potions.process.log.BrewingLog;
import com.mshmidov.potions.process.log.CauldronState;
import com.mshmidov.potions.process.log.Extraction;
import com.mshmidov.potions.process.log.Neutralization;

import static java.util.stream.Collectors.joining;

public class Cauldron {

    private final List<AddedIngredient> ingredients = new ArrayList<>();
    private final Map<Substance, Integer> substances = new HashMap<>();

    public void addIngredient(AddedIngredient ingredient) {
        ingredients.add(ingredient);
    }

    public boolean contains(Substance substance) {
        return substances.containsKey(substance);
    }

    public int getQuantity(Substance substance) {
        return substances.getOrDefault(substance, 0);
    }

    public ImmutableMap<Substance, Integer> getSubstances() {
        return ImmutableMap.copyOf(substances);
    }

    public void invertSubstance(Substance substance) {
        if (substances.containsKey(substance)) {
            final int quantity = substances.remove(substance);
            if (quantity > 0) {
                substances.put(substance.getOpposite(), quantity);
            }
        }
    }

    public void removeSubstance(Substance substance) {
        substances.remove(substance);
    }

    public BrewingLog performExtraction() {
        final BrewingLog log = new BrewingLog();

        ingredients.stream()
                .map(AddedIngredient::extractSubstances)
                .forEach(ingredient -> ingredient.forEach((substance, quantity) -> {
                    substances.merge(substance, quantity, (a, b) -> a + b);
                    log.add(new Extraction(substance, quantity));
                }));

        return log;
    }

    public BrewingLog neutralizeOpposites() {
        final BrewingLog log = new BrewingLog();

        Substance.OPPOSITES.entrySet().stream()
                .filter(pair -> substances.containsKey(pair.getKey()) && substances.containsKey(pair.getValue()))
                .map(pair -> {
                    final Substance substanceA = pair.getKey();
                    final Substance substanceB = pair.getValue();

                    final int quantityA = substances.get(substanceA);
                    final int quantityB = substances.get(substanceB);

                    if (quantityA > quantityB) {
                        substances.put(substanceA, quantityA - quantityB);
                        substances.remove(substanceB);

                        return new Neutralization(substanceA, substanceB, quantityB);

                    } else if (quantityA < quantityB) {
                        substances.remove(substanceA);
                        substances.put(substanceB, quantityB - quantityA);
                        return new Neutralization(substanceA, substanceB, quantityA);
                    } else {
                        substances.remove(substanceA);
                        substances.remove(substanceB);
                        return new Neutralization(substanceA, substanceB, quantityA);
                    }

                })
                .forEach(log::add);

        return log;
    }

    public CauldronState getState() {
        return new CauldronState(ingredients, substances);
    }

    @Override
    public String toString() {
        return String.format("Dissolved substances: %s; not yet dissolved: %s",
                substances.entrySet().stream()
                        .map(e -> String.format("%s %s", e.getKey(), e.getValue()))
                        .map(s -> s.toLowerCase())
                        .collect(joining(", ")),
                ingredients.stream().flatMap(i -> i.getSubstances().entrySet().stream())
                        .map(e -> String.format("%s %s", e.getKey(), e.getValue()))
                        .map(s -> s.toLowerCase())
                        .collect(joining(", ")));
    }
}
