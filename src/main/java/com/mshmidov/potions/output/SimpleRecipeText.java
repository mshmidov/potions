package com.mshmidov.potions.output;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMultiset;
import com.mshmidov.potions.potion.Recipe;

import static org.apache.commons.lang3.StringUtils.capitalize;

public final class SimpleRecipeText implements RecipeText {

    private final Recipe recipe;

    public SimpleRecipeText(Recipe recipe) {
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
            result.add(String.format("%s, %s.%n", ingredient.getName().toLowerCase(), count > 1 ? count + " порции" : "1 порция"));
        });

        return result;
    }

    @Override
    public List<String> getInstructions() {
        final List<String> result = new ArrayList<>();

        if (recipe.getVerb().isHeating()) {
            result.add(String.format("Добавить порцию воды в котёл и поставить на огонь. Не допускать закипания.%n"));
        } else {
            result.add(String.format("Добавить порцию воды в котёл и довести до кипения. Снять с огня.%n"));
        }

        IntStream.range(1, recipe.getFinalRound()).forEach(round -> {

            ImmutableMultiset.copyOf(recipe.getIngredients(round)).forEachEntry((ingredient, count) -> {
                result.add(String.format("Добавить %s%s.%n", count > 1 ? count + " порции " : "", ingredient.getName().toLowerCase()));
            });

            result.add(String.format("%s.%n", capitalize(Joiner.on(" и ").skipNulls().join(
                    recipe.getVerb().isHydration() ? "добавить чайную ложку воды" : null,
                    recipe.getVerb().isAeration() ? "бурно перемешать" : "перемешать"))));

        });

        result.add(String.format("%s.%n", capitalize(Joiner.on(" и ").skipNulls().join(
                recipe.getVerb().isHeating() ? "довести до кипения" : null,
                "отфильтровать"))));

        return result;
    }

}
