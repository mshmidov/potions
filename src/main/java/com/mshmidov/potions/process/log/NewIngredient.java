package com.mshmidov.potions.process.log;

import java.util.Objects;
import java.util.Optional;

import com.mshmidov.potions.ingredent.Ingredient;

public final class NewIngredient implements LogEntry {

    private final Ingredient ingredient;

    private final int quantity;

    public NewIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        this.quantity = 1;
    }

    private NewIngredient(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.ofNullable(other)
                .filter(e -> e instanceof NewIngredient)
                .map(e -> (NewIngredient) e)
                .filter(e -> Objects.equals(e.ingredient, this.ingredient))
                .map(e -> new NewIngredient(ingredient, quantity + 1));
    }

    @Override
    public String toString() {
        return String.format("Добавлен ингредиент: %s%s.",
                quantity > 1 ? quantity + "x " : "",
                ingredient.toString());
    }
}
