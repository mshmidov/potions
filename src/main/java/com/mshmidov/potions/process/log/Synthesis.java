package com.mshmidov.potions.process.log;

import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mshmidov.potions.definition.Substance;

import static java.util.stream.Collectors.joining;

public final class Synthesis implements LogEntry {

    private final Set<Substance> lowerSubstances;
    private final Substance higherSubstance;
    private final int quantity;

    public Synthesis(Set<Substance> lowerSubstances, Substance higherSubstance, int quantity) {
        this.lowerSubstances = ImmutableSet.copyOf(lowerSubstances);
        this.higherSubstance = higherSubstance;
        this.quantity = quantity;
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("Происходит синтез вещества %s %s из %s.",
                higherSubstance,
                quantity,
                lowerSubstances.stream()
                .map(s -> s.name())
                .collect(joining(", ")));
    }
}
