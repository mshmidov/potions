package com.mshmidov.potions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.mshmidov.potions.definition.Substance;
import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.ingredent.IngredientDefinition;
import com.mshmidov.potions.output.TableOutput;
import org.apache.commons.lang3.StringUtils;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.toList;

public class SubstancesInfo {

    public static void main(String[] args) {
        generateInfo(new TableOutput(3)::print);
    }

    private static void generateInfo(Consumer<List<List<String>>> output) {

        output.accept(ImmutableList.of(
                ImmutableList.of(""),
                ImmutableList.of("Substances:"),
                ImmutableList.of("-----------")));

        final Multimap<Substance, Integer> usedSubstances = ArrayListMultimap.create();

        Arrays.stream(Ingredient.values())
                .map(Ingredient::asIs)
                .flatMap(i -> i.getSubstances().entrySet().stream())
                .forEach(e -> usedSubstances.put(e.getKey(), e.getValue()));

        final List<Substance> sortedSubstances = Arrays.stream(Substance.values())
                .sorted(Comparator
                        .comparing(Substance::getOrder)
                        .thenComparing(s -> usedSubstances.get(s).stream().collect(summarizingInt(i -> i)).getSum())
                        .thenComparing(s -> usedSubstances.get(s).size()))
                .collect(toList());

        final List<List<String>> substancesOutput = sortedSubstances.stream().map(substance -> {

            final int distinctIngredients = usedSubstances.get(substance).size();
            final String concentrations = usedSubstances.get(substance).stream().sorted().map(String::valueOf).collect(joining(","));
            final long sum = usedSubstances.get(substance).stream().collect(summarizingInt(i -> i)).getSum();
            final String order = StringUtils.repeat('I', substance.getOrder()).replace("IIII", "IV");

            return ImmutableList.of(
                    substance.name() + ":",
                    String.valueOf(distinctIngredients),
                    concentrations,
                    String.valueOf(sum),
                    order);
        }).collect(toList());

        output.accept(ImmutableList.<List<String>> builder()
                .add(ImmutableList.of("Substance", "", "", "sum", "order"))
                .addAll(substancesOutput)
                .build());

        output.accept(ImmutableList.of(
                ImmutableList.of(""),
                ImmutableList.of("Ingredients by element:"),
                ImmutableList.of("-----------------------")));

        final List<List<String>> elementsOutput = Arrays.stream(Ingredient.values())
                .map(Ingredient::asIs)
                .collect(groupingBy(IngredientDefinition::getElement))
                .entrySet().stream()
                .map(e -> ImmutableList.of(e.getKey().name() + ":", String.valueOf(e.getValue().size())))
                .collect(toList());

        output.accept(ImmutableList.<List<String>> builder()
                .add(ImmutableList.of("Element", "Ingredients"))
                .addAll(elementsOutput)
                .build());
    }
}
