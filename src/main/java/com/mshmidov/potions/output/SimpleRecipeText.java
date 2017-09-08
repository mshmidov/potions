package com.mshmidov.potions.output;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMultiset;
import com.mshmidov.potions.potion.SimpleRecipe;
import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.capitalize;

public final class SimpleRecipeText implements RecipeText {

    private final SimpleRecipe recipe;

    public SimpleRecipeText(SimpleRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String getName() {
        return recipe.getName();
    }

    @Override
    public List<String> getIngredients() {
        final List<String> result = new ArrayList<>();

        recipe.getAllIngredients().forEachEntry((ingredient, count) -> {
            result.add(String.format("%s, %s.", ingredient.getName().toLowerCase(), count > 1 ? count + " порции" : "1 порция"));
        });

        return result;
    }

    @Override
    public List<String> getInstructions() {
        final LinkedList<String> result = new LinkedList<>();

        if (recipe.getVerb().isHeating()) {
            result.add("Добавить порцию воды в котёл и поставить на огонь. Не допускать закипания.");
        } else {
            result.add("Добавить порцию воды в котёл и довести до кипения. Снять с огня.");
        }

        IntStream.range(1, recipe.getFinalRound()).forEach(round -> {

            ImmutableMultiset.copyOf(recipe.getIngredients(round)).forEachEntry((ingredient, count) -> {
                result.add(String.format("Добавить %s%s.", count > 1 ? count + " порции " : "", ingredient.getName().toLowerCase()));
            });

            final String roundFinish = Joiner.on(" и ").skipNulls().join(
                    recipe.getVerb().isHydration() ? "добавить чайную ложку воды" : null,
                    recipe.getVerb().isAeration() ? "бурно перемешать" : "перемешать") + ".";

            if (result.getLast().toLowerCase().endsWith(roundFinish)) {
                final String numeralPrefix = StringUtils.removeEnd(result.getLast().toLowerCase(), roundFinish).trim();
                final int numeral = parseNumeralPrefix(numeralPrefix);
                result.removeLast();
                result.add(String.format("%s: %s", getNumeralPrefix(numeral + 1), roundFinish));

            } else {
                result.add(capitalize(roundFinish));
            }

        });

        result.add(String.format("%s.", capitalize(Joiner.on(" и ").skipNulls().join(
                recipe.getVerb().isHeating() ? "довести до кипения" : null,
                "отфильтровать"))));

        return result;
    }

    private String getNumeralPrefix(int times) {
        if (times == 2) {
            return "Дважды";

        } else if (times == 3) {
            return "Трижды";

        } else if (times == 4) {
            return "Четырежды";

        } else {
            return Integer.toString(times) + " раз";
        }
    }

    private int parseNumeralPrefix(String prefix) {
        if (Objects.equals(prefix, "дважды")) {
            return 2;

        } else if (Objects.equals(prefix, "трижды")) {
            return 3;

        } else if (Objects.equals(prefix, "четырежды")) {
            return 2;

        } else if (prefix.endsWith(" раз")) {
            return Integer.parseInt(StringUtils.removeEnd(prefix, " раз"));

        } else {
            return 1;
        }

    }
}
