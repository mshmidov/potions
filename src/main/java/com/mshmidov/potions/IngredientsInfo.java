package com.mshmidov.potions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.ingredent.KnownIngredient;
import com.mshmidov.potions.output.TableOutput;
import com.mshmidov.potions.potion.Recipe;
import org.apache.commons.lang3.StringUtils;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class IngredientsInfo {

    public static void main(String[] args) {

        final TableOutput tableOutput = new TableOutput(2);

        tableOutput.print(ImmutableList.of(
                ImmutableList.of(""),
                ImmutableList.of("Ingredient usages:"),
                ImmutableList.of("-----------")));

        final Multiset<Ingredient> ingredientUsages = HashMultiset.create();

        final Multiset<Verb> verbUsages = HashMultiset.create();

        for (Recipe recipe : KnownRecipe.values()) {
            recipe.getAllIngredients().forEachEntry((ingredient, count) -> ingredientUsages.add(ingredient, count));
            recipe.getAllVerbs().forEachEntry((verb, count) -> verbUsages.add(verb, count));
        }

        final List<List<String>> ingredientRows = Arrays.stream(KnownIngredient.values())
                .map(i -> new IngredientStats(i.getName(), i.getSubstances(), ingredientUsages.count(i)))
                .sorted(Comparator.comparing(IngredientStats::getUsages).reversed())
                .map(IngredientStats::toColumns)
                .collect(toList());

        tableOutput.print(ingredientRows);

        tableOutput.print(ImmutableList.of(
                ImmutableList.of(""),
                ImmutableList.of("Verb usages:"),
                ImmutableList.of("-----------")));

        final List<List<String>> verbRows = Arrays.stream(Verb.values())
                .map(v -> new VerbStats(v, verbUsages.count(v)))
                .sorted(Comparator.comparing(VerbStats::getUsages).reversed())
                .map(VerbStats::toColumns)
                .collect(toList());

        tableOutput.print(verbRows);

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

    private static final class VerbStats {

        private final Verb verb;
        private final int usages;

        public VerbStats(Verb verb, int usages) {
            this.verb = verb;
            this.usages = usages;
        }

        public Verb getVerb() {
            return verb;
        }

        public int getUsages() {
            return usages;
        }

        public List<String> toColumns() {
            return ImmutableList.of(
                    StringUtils.capitalize( verb.name().toLowerCase()),
                    Integer.toString(usages));
        }

    }
}
