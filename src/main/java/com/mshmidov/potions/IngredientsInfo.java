package com.mshmidov.potions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.ingredent.IngredientDefinition;
import com.mshmidov.potions.output.TableOutput;
import com.mshmidov.potions.potion.Brewable;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class IngredientsInfo {

    public static void main(String[] args) {

        final Multiset<IngredientDefinition> ingredientUsages = HashMultiset.create();

        for (Brewable recipe : KnownRecipe.values()) {
            recipe.getAllIngredients().forEachEntry((ingredient, count) -> ingredientUsages.add(ingredient, count));
        }

        final List<List<String>> rows = Arrays.stream(Ingredient.values())
                .map(Ingredient::asIs)
                .map(i -> new IngredientStats(i.getName(), i.getSubstances(), ingredientUsages.count(i)))
                .sorted(Comparator.comparing(IngredientStats::getUsages).reversed())
                .map(IngredientStats::toColumns)
                .collect(toList());

        final TableOutput tableOutput = new TableOutput(2);
        tableOutput.print(rows);

    }

    private static final class IngredientStats {

        private final String name;
        private final Map<Substance, Integer> substances;
        private final int usages;

        public IngredientStats(String name, Map<Substance, Integer> substances, int usages) {
            this.name = name;
            this.substances = substances;
            this.usages = usages;
        }

        public List<String> toColumns() {
            return ImmutableList.of(
                    name,
                    substances.entrySet().stream()
                            .map(e -> String.format("%s %s", e.getKey(), e.getValue()))
                            .collect(joining(", ")),
                    Integer.toString(usages));
        }

        public int getUsages() {
            return usages;
        }

        public String getName() {
            return name;
        }

        public Map<Substance, Integer> getSubstances() {
            return substances;
        }

    }
}
