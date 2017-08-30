package com.mshmidov.potions.ingredent;

import com.mshmidov.potions.definition.Substance;

public enum Ingredient {

    EELS_EYES(IngredientDefinition.ofWater("Глаза угря")
            .containing(Substance.ALERTNESS, 2)
            .containing(Substance.OBSESSION, 1)
            .build()),

    LAVENDER_FLOWERS(IngredientDefinition.ofWater("Цветки лаванды")
            .containing(Substance.SLEEPINESS, 2)
            .containing(Substance.HEALTH, 1)
            .build()),

    VALERIAN_SPRIGS(IngredientDefinition.ofWater("Побег валерианы")
            .containing(Substance.INDIFFERENCE, 2)
            .containing(Substance.SLEEPINESS, 1)
            .build()),

    LOVAGE_FLOWERS(IngredientDefinition.ofFire("Цветки любистока")
            .containing(Substance.OBSESSION, 2)
            .containing(Substance.EUPHORIA, 1)
            .build()),

    WHITE_ASPHODELUS_FLOWERS(IngredientDefinition.ofEarth("Цветки асфоделуса белого")
            .containing(Substance.SORROW, 2)
            .containing(Substance.INDIFFERENCE, 1)
            .build());

    private final IngredientDefinition definition;

    private Ingredient(IngredientDefinition definition) {
        this.definition = definition;
    }

    public IngredientDefinition asIs() {
        return definition;
    }

    public IngredientDefinition toFire() {
        return definition.toFire();
    }

    public IngredientDefinition toWater() {
        return definition.toWater();
    }

    public IngredientDefinition toEarth() {
        return definition.toEarth();
    }

}
