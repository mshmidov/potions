package com.mshmidov.potions.ingredent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.ImmutableMap;
import com.mshmidov.potions.definition.Element;
import com.mshmidov.potions.definition.Substance;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.util.stream.Collectors.joining;

public final class IngredientDefinition {

    private final String name;

    private final Element element;

    private final Map<Substance, Integer> substances;

    private IngredientDefinition(Builder builder) {
        this.name = builder.name;
        this.element = builder.element;
        this.substances = ImmutableMap.copyOf(builder.substances);
    }

    public static Builder ofFire(String name) {
        return new Builder(Element.FIRE, name);
    }

    public static Builder ofWater(String name) {
        return new Builder(Element.WATER, name);
    }

    public static Builder ofEarth(String name) {
        return new Builder(Element.EARTH, name);
    }

    public String getName() {
        return name;
    }

    public Element getElement() {
        return element;
    }

    public Map<Substance, Integer> getSubstances() {
        return substances;
    }

    public AddedIngredient mutableCopy() {
        return new AddedIngredient(this);
    }

    public IngredientDefinition toFire() {
        checkState(element != Element.FIRE, "IngredientDefinition is already of fire");

        final String newName;

        if (element == Element.WATER) {
            newName = name + " (прокаленный)";

        } else if (element == Element.EARTH) {
            newName = name + " (промытый горячей водой)";

        } else {
            throw new IllegalStateException("IngredientDefinition " + this + "is of illegal element");
        }

        return IngredientDefinition.ofFire(newName).containing(substances).build();
    }

    public IngredientDefinition toWater() {
        checkState(element != Element.WATER, "IngredientDefinition is already of water");

        final String newName;

        if (element == Element.FIRE) {
            newName = name + " (холодный экстракт)";

        } else if (element == Element.EARTH) {
            newName = name + " (экстракт)";

        } else {
            throw new IllegalStateException("IngredientDefinition " + this + "is of illegal element");
        }

        return IngredientDefinition.ofWater(newName).containing(substances).build();
    }

    public IngredientDefinition toEarth() {
        checkState(element != Element.EARTH, "IngredientDefinition is already of earth");

        final String newName;

        if (element == Element.FIRE) {
            newName = name + " (промытый холодной водой)";

        } else if (element == Element.WATER) {
            newName = name + " (выдержанный в соли)";

        } else {
            throw new IllegalStateException("IngredientDefinition " + this + "is of illegal element");
        }

        return IngredientDefinition.ofEarth(newName).containing(substances).build();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IngredientDefinition)) {
            return false;
        }

        final IngredientDefinition other = (IngredientDefinition) obj;

        return Objects.equals(this.element, other.element)
                && Objects.equals(this.substances, other.substances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, substances);
    }

    @Override
    public String toString() {
        return String.format("%s (of %s, %s)",
                name,
                element.name().toLowerCase(),
                substances.entrySet().stream().map(e -> String.format("%s %s", e.getKey(), e.getValue())).collect(joining(", ")));
    }

    public static final class Builder {

        private final String name;

        private final Element element;

        private final Map<Substance, Integer> substances;

        private Builder(Element element, String name) {
            this.name = name;
            this.element = element;
            this.substances = new HashMap<>();
        }

        public Builder containing(Map<Substance, Integer> substances) {
            substances.forEach(this::containing);

            return this;
        }

        public Builder containing(Substance substance, int quantity) {
            checkArgument(!substances.containsKey(substance), "IngredientDefinition already contains %s", substance);
            checkArgument(quantity > 0, "Quantity should be positive");

            this.substances.put(substance, quantity);

            return this;
        }

        public IngredientDefinition build() {
            return new IngredientDefinition(this);
        }
    }
}
