package com.mshmidov.potions.potion;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multiset;
import com.mshmidov.potions.definition.Verb;
import com.mshmidov.potions.ingredent.IngredientDefinition;
import com.mshmidov.potions.output.RecipeText;
import com.mshmidov.potions.output.SimpleRecipeText;
import com.mshmidov.potions.process.Brewing;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class Recipe implements Brewable {

    private final String name;

    private final Verb verb;

    private final ListMultimap<Integer, IngredientDefinition> ingredients;

    private final int finalRound;

    private Recipe(Builder builder) {
        this.name = builder.name;
        this.verb = builder.verb;
        this.ingredients = ImmutableListMultimap.copyOf(builder.ingredients);
        this.finalRound = builder.finalRound;
    }

    public static Builder forVerb(Verb verb) {
        return new Builder(verb);
    }

    @Override
    public String getName() {
        return name;
    }

    public Verb getVerb() {
        return verb;
    }

    public List<IngredientDefinition> getIngredients(int round) {
        checkArgument(round < finalRound, "Recipe ends on round %s", finalRound);

        return ingredients.get(round);
    }

    @Override
    public Multiset<IngredientDefinition> getAllIngredients() {
        return ImmutableMultiset.copyOf(ingredients.values());
    }

    public int getFinalRound() {
        return finalRound;
    }

    @Override
    public Potion brew() {
        return new Brewing(this).proceed();
    }

    public Potion brewOnBaseOf(Potion base) {
        return new Brewing(this).proceedOnBaseOf(base);
    }

    @Override
    public RecipeText asText() {
        return new SimpleRecipeText(this);
    }

    public static final class Builder {

        private final Verb verb;

        private final ListMultimap<Integer, IngredientDefinition> ingredients = ArrayListMultimap.create();

        private String name = "";

        private int finalRound = 1;

        private Builder(Verb verb) {
            this.verb = verb;
        }

        public Builder named(String name) {
            this.name = name;
            return this;
        }

        public RoundBuilder onRound(int round) {
            return new RoundBuilder(round);
        }

        private Recipe build() {
            checkState(ingredients.keySet().stream().noneMatch(i -> i >= finalRound),
                    "Ingredients cannot be added on final round or after that.");

            checkState(ingredients.keySet().stream().map(ingredients::get).map(List::size).noneMatch(n -> n > 2),
                    "No more than two ingredients can be added on single round");

            return new Recipe(this);
        }

        public final class RoundBuilder {

            final int round;

            private RoundBuilder(int round) {
                this.round = round;
            }

            public Builder add(IngredientDefinition ingredient) {
                Builder.this.ingredients.put(round, ingredient);
                return Builder.this;
            }

            public Builder add(IngredientDefinition ingredient1, IngredientDefinition ingredient2) {
                Builder.this.ingredients.put(round, ingredient1);
                Builder.this.ingredients.put(round, ingredient2);
                return Builder.this;
            }

            public Recipe finish() {
                Builder.this.finalRound = round;
                return Builder.this.build();
            }
        }

    }
}
