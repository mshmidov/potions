package com.mshmidov.potions.process.log;

import java.util.Optional;

import com.mshmidov.potions.definition.Substance;

public final class IngredientTransmutation implements LogEntry {

    private final String ingredientName;
    private final Substance from;
    private final Substance to;
    private final int quantity;

    public IngredientTransmutation(String ingredientName, Substance from, Substance to, int quantity) {
        this.ingredientName = ingredientName;
        this.from = from;
        this.to = to;
        this.quantity = quantity;
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("%1s %3$s в ингредиенте \"%4$s\" трансмутирует в %2$s %3$s", from, to, quantity, ingredientName);
    }
}
