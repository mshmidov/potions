package com.mshmidov.potions.process;

import java.util.Optional;
import java.util.stream.IntStream;

import com.mshmidov.potions.ingredent.IngredientDefinition;
import com.mshmidov.potions.potion.Potion;
import com.mshmidov.potions.potion.SimpleRecipe;

public class Brewing {

    private final SimpleRecipe recipe;

    private final Cauldron cauldron = new Cauldron();

    public Brewing(SimpleRecipe recipe) {
        this.recipe = recipe;
    }

    public Potion proceed() {
      return proceedOnBaseOf(null);
    }

    public Potion proceedOnBaseOf(Potion base) {
        System.out.printf("Opposing elements: %s%n", recipe.getVerb().oppositeElements());
        IntStream.range(1, recipe.getFinalRound()).forEach(this::calculateRound);
        return Potion.create(recipe, Optional.ofNullable(base), cauldron.getSubstances());
    }

    private void calculateRound(int round) {

        System.out.println("Round " + round);
        System.out.println();
        System.out.println(cauldron);
        System.out.println();

        // adding ingredients

        recipe.getIngredients(round).stream()
                .map(IngredientDefinition::mutableCopy)
                .peek(cauldron::addIngredient)
                .forEach(ingredient -> {
                    if (recipe.getVerb().oppositeElements().contains(ingredient.getElement())) {

                        // transmutations

                        ingredient.getSubstances().forEach((addedSubstance, addedQuantity) -> {

                            if (cauldron.contains(addedSubstance.getOpposite())) {
                                final int oppositeQuantity = cauldron.getQuantity(addedSubstance.getOpposite());

                                if (addedQuantity.compareTo(oppositeQuantity) > 0) {
                                    ingredient.invertSubstance(addedSubstance);

                                } else if (addedQuantity.compareTo(oppositeQuantity) < 0) {
                                    cauldron.invertSubstance(addedSubstance.getOpposite());
                                }
                            }
                        });
                    }
                });

        cauldron.performExtraction();

        System.out.println("After extraction: " + cauldron);

        cauldron.neutralizeOpposites();

        System.out.println("At the end of round: " + cauldron);
        System.out.println("---------------------");
    }
}
