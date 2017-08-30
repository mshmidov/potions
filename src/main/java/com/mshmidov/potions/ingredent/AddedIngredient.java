package com.mshmidov.potions.ingredent;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mshmidov.potions.definition.Element;
import com.mshmidov.potions.definition.Substance;

import static java.util.stream.Collectors.joining;

public class AddedIngredient {

    private final String name;

    private final Element element;

    private final Map<Substance, Integer> substances = new HashMap<>();

    AddedIngredient(IngredientDefinition ingredient) {
        this.name = ingredient.getName();
        this.element = ingredient.getElement();
        this.substances.putAll(ingredient.getSubstances());
    }

    public Element getElement() {
        return element;
    }

    public ImmutableMap<Substance, Integer> getSubstances() {
        return ImmutableMap.copyOf(substances);
    }

    public void invertSubstance(Substance substance) {
        if (substances.containsKey(substance)) {
            final int quantity = substances.remove(substance);
            if (quantity > 0) {
                substances.put(substance.getOpposite(), quantity);
                System.out.println(String.format("%1$s %3$s in added ingredient transmuted into %2$s %3$s", substance,
                        substance.getOpposite(), quantity));
            }
        }
    }

    public void removeSubstance(Substance substance) {
        substances.remove(substance);
    }

    public Map<Substance, Integer> extractSubstances() {
        final Map<Substance, Integer> result = new HashMap<>();

        getSubstances().forEach((substance, quantity) -> {
            final int rest = quantity - 1;
            result.put(substance, 1);
            if (rest > 0) {
                substances.put(substance, rest);
            } else {
                substances.remove(substance);
            }
        });

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s (of %s, %s)",
                name,
                element.name().toLowerCase(),
                substances.entrySet().stream().map(e -> String.format("%s %s", e.getKey(), e.getValue())).collect(joining(", ")));
    }
}
