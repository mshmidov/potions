package com.mshmidov.potions.process.log;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.ingredent.AddedIngredient;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CauldronState implements LogEntry {

    private final List<AddedIngredient> ingredients;
    private final Map<Substance, Integer> substances;

    public CauldronState(List<AddedIngredient> ingredients, Map<Substance, Integer> substances) {
        this.ingredients = ingredients.stream().map(AddedIngredient::mutableCopy).collect(toList());
        this.substances = ImmutableMap.copyOf(substances);
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.ofNullable(other).filter(e -> e instanceof CauldronState);
    }

    @Override
    public String toString() {

        if (ingredients.isEmpty() && substances.isEmpty()) {
            return "В котле пусто.";
        }

        final String dissolved = substances.entrySet().stream()
                .map(e -> String.format("%s %s", e.getKey(), e.getValue()))
                .map(s -> s.toLowerCase())
                .collect(joining(", "));
        final String undissolved = ingredients.stream().flatMap(i -> i.getSubstances().entrySet().stream())
                .map(e -> String.format("%s %s", e.getKey(), e.getValue()))
                .map(s -> s.toLowerCase())
                .collect(joining(", "));

        if (!dissolved.isEmpty() && undissolved.isEmpty()) {

            return "В котле: " + dissolved;

        } else if (dissolved.isEmpty() && !undissolved.isEmpty()) {
            return "Нерастворённые ингредиенты: " + undissolved;

        } else {
            return String.format("В котле: %s; нерастворённые ингредиенты: %s", dissolved, undissolved);
        }
    }
}
