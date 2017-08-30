package com.mshmidov.potions.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.ingredent.AddedIngredient;

import static java.util.stream.Collectors.joining;

public class Cauldron {

    private final List<AddedIngredient> ingredients = new ArrayList<>();
    private final Map<Substance, Integer> substances = new HashMap<>();

    public void addIngredient(AddedIngredient ingredient) {
        ingredients.add(ingredient);
        System.out.println("Added " + ingredient);
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
                System.out.println(
                        String.format("%1$s %3$s in solution transmuted into %2$s %3$s", substance, substance.getOpposite(), quantity));
            }
        }
    }

    public void removeSubstance(Substance substance) {
        substances.remove(substance);
    }

    public void performExtraction() {
        ingredients.stream()
                .map(AddedIngredient::extractSubstances)
                .forEach(ingredient -> ingredient.forEach((substance, quantity) -> {
                    substances.merge(substance, quantity, (a, b) -> a + b);
                    System.out.println(String.format("Extracted %s %s", substance, quantity));
                }));
    }

    public void neutralizeOpposites() {
        Substance.OPPOSITES.entrySet().stream()
                .filter(pair -> substances.containsKey(pair.getKey()) && substances.containsKey(pair.getValue()))
                .forEach(pair -> {
                    final Substance substanceA = pair.getKey();
                    final Substance substanceB = pair.getValue();

                    final int quantityA = substances.get(substanceA);
                    final int quantityB = substances.get(substanceB);

                    if (quantityA > quantityB) {
                        substances.put(substanceA, quantityA - quantityB);
                        substances.remove(substanceB);

                        System.out.println(String.format("%1$s %3$s neutralized %2$s %3$s", substanceA, substanceB, quantityB));

                    } else if (quantityA < quantityB) {
                        substances.remove(substanceA);
                        substances.put(substanceB, quantityB - quantityA);
                        System.out.println(String.format("%1$s %3$s neutralized %2$s %3$s", substanceA, substanceB, quantityA));
                    } else {
                        substances.remove(substanceA);
                        substances.remove(substanceB);
                        System.out.println(String.format("%1$s %3$s neutralized %2$s %3$s", substanceA, substanceB, quantityA));
                    }

                });
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
