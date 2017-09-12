package com.mshmidov.potions.process;

import java.util.Optional;
import java.util.stream.IntStream;

import com.mshmidov.potions.ingredent.Ingredient;
import com.mshmidov.potions.potion.Potion;
import com.mshmidov.potions.potion.SimplePotion;
import com.mshmidov.potions.potion.SimpleRecipe;
import com.mshmidov.potions.process.log.BrewingLog;
import com.mshmidov.potions.process.log.CauldronTransmutation;
import com.mshmidov.potions.process.log.IngredientTransmutation;
import com.mshmidov.potions.process.log.NewIngredient;
import com.mshmidov.potions.process.log.NewRound;
import com.mshmidov.potions.process.log.NewVerb;

public class Brewing {

    private final SimpleRecipe recipe;

    private final Cauldron cauldron = new Cauldron();

    public Brewing(SimpleRecipe recipe) {
        this.recipe = recipe;
    }

    public Potion proceed() {
        return proceedOnBaseOf(null);
    }

    public SimplePotion proceedOnBaseOf(SimplePotion base) {

        final BrewingLog log = new BrewingLog();

        Optional.ofNullable(base).map(Potion::getLog).ifPresent(log::addAll);
        log.add(new NewVerb(recipe.getVerb()));

        IntStream.range(1, recipe.getFinalRound()).mapToObj(this::calculateRound).forEach(log::addAll);

        return SimplePotion.create(recipe, Optional.ofNullable(base), cauldron.getSubstances(), log);
    }

    private BrewingLog calculateRound(int round) {

        final BrewingLog log = new BrewingLog();

        log.add(new NewRound(round));
        log.add(cauldron.getState());

        // adding ingredients

        recipe.getIngredients(round).stream()
                .peek(i -> log.add(new NewIngredient(i)))
                .map(Ingredient::mutableCopy)
                .peek(cauldron::addIngredient)
                .forEach(ingredient -> {
                    if (recipe.getVerb().oppositeElements().contains(ingredient.getElement())) {

                        // transmutations

                        ingredient.getSubstances().forEach((addedSubstance, addedQuantity) -> {

                            if (cauldron.contains(addedSubstance.getOpposite())) {
                                final int oppositeQuantity = cauldron.getQuantity(addedSubstance.getOpposite());

                                if (addedQuantity.compareTo(oppositeQuantity) > 0) {
                                    ingredient.invertSubstance(addedSubstance);
                                    log.add(new IngredientTransmutation(ingredient.getName(), addedSubstance, addedSubstance.getOpposite(),
                                            addedQuantity));

                                } else if (addedQuantity.compareTo(oppositeQuantity) < 0) {
                                    cauldron.invertSubstance(addedSubstance.getOpposite());
                                    log.add(new CauldronTransmutation(addedSubstance.getOpposite(), addedSubstance, oppositeQuantity));
                                }
                            }
                        });
                    }
                });

        log.addAll(cauldron.performExtraction());
        log.add(cauldron.getState());
        log.addAll(cauldron.neutralizeOpposites());
        log.add(cauldron.getState());

        return log;
    }
}
