package com.mshmidov.potions;

import java.util.Arrays;

import com.mshmidov.potions.ingredent.Ingredient;
import org.apache.commons.lang3.StringUtils;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.capitalize;

public class IngredientsInfo {

    public static void main(String[] args) {

        final int maxIngredientLength = Arrays.stream(Ingredient.values()).map(i -> i.asIs().getName().length()).max(Integer::compareTo)
                .orElse(0);

        Arrays.stream(Ingredient.values())
                .map(Ingredient::asIs)
                .forEach(i -> {
                    final String substances = i.getSubstances().entrySet().stream()
                            .map(e -> String.format("%s %s", e.getKey(), e.getValue()))
                            .collect(joining(", "));

                    System.out.printf("%s:%s %s [%s]%n",
                            i.getName(),
                            StringUtils.repeat(' ', maxIngredientLength - i.getName().length()),
                            substances,
                            capitalize(i.getElement().name().toLowerCase()));
                });

    }

}
