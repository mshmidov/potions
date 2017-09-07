package com.mshmidov.potions.output;

import java.util.List;

public interface RecipeText {

    String getName();

    List<String> getIngredients();

    List<String> getInstructions();

}
