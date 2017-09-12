package com.mshmidov.potions.process.log;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.mshmidov.potions.definition.EffectScale;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.definition.Verb;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.capitalize;

public final class NewSimplePotion implements LogEntry {

    private final Table<Verb, Substance, Integer> effects;
    private final Table<Verb, Substance, Integer> sideEffects;

    public NewSimplePotion(Table<Verb, Substance, Integer> effects, Table<Verb, Substance, Integer> sideEffects) {
        this.effects = ImmutableTable.copyOf(effects);
        this.sideEffects = ImmutableTable.copyOf(sideEffects);
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        final String effectString = effects.rowKeySet().stream()
                .map(verb -> effectsToString(verb, effects.row(verb)))
                .collect(joining("; "));

        final String sideEffectString = sideEffects.rowKeySet().stream()
                .map(verb -> effectsToString(verb, sideEffects.row(verb)))
                .collect(joining("; "));

        final StringBuilder result = new StringBuilder();

        if (!effectString.isEmpty()) {
            result.append("Эффекты: ").append(effectString);

            if (!sideEffectString.isEmpty()) {
                result.append("; ");
            }
        }

        if (!sideEffectString.isEmpty()) {
            result.append("Побочные эффекты: ").append(sideEffectString);
        }

        return String.format("%n%s%n", result.toString());
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
