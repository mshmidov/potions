package com.mshmidov.potions.process.log;

import java.util.Optional;

import com.mshmidov.potions.definition.Substance;

public final class Neutralization implements LogEntry {

    private final Substance substanceA;
    private final Substance substanceB;
    private final int quantity;

    public Neutralization(Substance substanceA, Substance substanceB, int quantity) {
        this.substanceA = substanceA;
        this.substanceB = substanceB;
        this.quantity = quantity;
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("%1$s %3$s нейтрализует %2$s %3$s", substanceA, substanceB, quantity);
    }
}
