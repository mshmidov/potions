package com.mshmidov.potions.output;

import java.util.ArrayList;
import java.util.List;

import com.mshmidov.potions.potion.ComplexRecipe;
import com.mshmidov.potions.potion.Recipe;
import com.mshmidov.potions.utils.MoreStreams;
import com.mshmidov.potions.utils.Pair;

import static java.util.stream.Collectors.toList;

public final class ComplexRecipeText implements RecipeText {

    private final ComplexRecipe recipe;

    public ComplexRecipeText(ComplexRecipe recipe) {
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
        return MoreStreams.indexed(recipe.getRecipes())
                .map(p -> p.mapRight(Recipe::asText))
                .map(p -> p.mapRight(RecipeText::getInstructions))
                .map(p -> p.mapRight((i, insructions) -> {
                    if (i > 0) {
                        final String firstLine = insructions.remove(0);
                        insructions.add(0, firstLine.replace("Добавить порцию воды в котёл", "Вернуть в котёл"));
                    }
                    return insructions;
                }))
                .map(Pair::getRight)
                .flatMap(List::stream)
                .collect(toList());
    }
}
