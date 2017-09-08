package com.mshmidov.potions.ingredent;

import java.util.Map;

import com.mshmidov.potions.definition.Element;
import com.mshmidov.potions.definition.Substance;

public enum KnownIngredient implements Ingredient {

    WHITE_ASPHODELUS_FLOWERS(IngredientDefinition.ofWater("Цветки асфоделуса белого")
            .containing(Substance.SORROW, 2)
            .containing(Substance.INDIFFERENCE, 1)
            .build()),

    NARTHECIUM_FLOWERS(IngredientDefinition.ofWater("Цветки нартеция")
            .containing(Substance.WEAKNESS, 2)
            .containing(Substance.DISTRACTION, 1)
            .build()),

    DAISY_ROOT(IngredientDefinition.ofEarth("Корень маргаритки")
            .containing(Substance.CALM, 2)
            .containing(Substance.HEALTH, 1)
            .build()),

    FLIXWEED_SEEDS(IngredientDefinition.ofFire("Семена Дескурайнии Софии")
            .containing(Substance.HEALTH, 2)
            .containing(Substance.RESTRAINT, 1)
            .build()),

    GINGER_ROOT(IngredientDefinition.ofEarth("Корень имбиря")
            .containing(Substance.CONCENTRATION, 2)
            .containing(Substance.ALERTNESS, 1)
            .build()),

    KNOTWEED_LEAVES(IngredientDefinition.ofWater("Листья горца")
            .containing(Substance.TININESS, 2)
            .build()),

    LAVENDER_FLOWERS(IngredientDefinition.ofWater("Цветки лаванды")
            .containing(Substance.SLEEPINESS, 2)
            .containing(Substance.HEALTH, 1)
            .build()),

    LOVAGE_FLOWERS(IngredientDefinition.ofWater("Цветки любистока")
            .containing(Substance.OBSESSION, 2)
            .containing(Substance.EUPHORIA, 1)
            .build()),

    PEPPERMINT_LEAVES(IngredientDefinition.ofWater("Листья перечной мяты")
            .containing(Substance.MEMORY, 2)
            .containing(Substance.ALERTNESS, 1)
            .build()),

    VALERIAN_SPRIGS(IngredientDefinition.ofWater("Побег валерианы")
            .containing(Substance.INDIFFERENCE, 2)
            .containing(Substance.SLEEPINESS, 1)
            .build()),

    COMMON_MUGWORT_LEAVES(IngredientDefinition.ofWater("Листья чернобыльника")
            .containing(Substance.FORGETFULNESS, 2)
            .containing(Substance.DISTRACTION, 1)
            .build()),

    ARMADILLO_BILE(IngredientDefinition.ofFire("Желчь броненосца")
            .containing(Substance.EUPHORIA, 2)
            .containing(Substance.SLOWNESS, 1)
            .build()),

    BAT_SPLEEN(IngredientDefinition.ofFire("Селезёнка летучей мыши")
            .containing(Substance.SPEED, 2)
            .containing(Substance.LIGHTNESS, 1)
            .build()),

    BEETLE_EYE(IngredientDefinition.ofFire("Глаз жука")
            .containing(Substance.AWARENESS, 2)
            .build()),

    BICORN_HORN_POWDER(IngredientDefinition.ofEarth("Порошок рога двурога")
            .containing(Substance.BULK, 2)
            .containing(Substance.STUPIDITY, 1)
            .build()),

    CATERPILLAR(IngredientDefinition.ofEarth("Сушеная гусеница")
            .containing(Substance.AGE, 2)
            .containing(Substance.SLOWNESS, 1)
            .build()),

    COCKROACH(IngredientDefinition.ofEarth("Сушеный таракан")
            .containing(Substance.SPEED, 2)
            .containing(Substance.UGLINESS, 1)
            .build()),

    CROCODILE_HEART(IngredientDefinition.ofFire("Сердце крокодила")
            .containing(Substance.STRENGTH, 2)
            .containing(Substance.AWARENESS, 1)
            .build()),

    EELS_EYES(IngredientDefinition.ofFire("Глаза угря")
            .containing(Substance.ALERTNESS, 2)
            .containing(Substance.OBSESSION, 1)
            .build()),

    FROG_BRAIN(IngredientDefinition.ofFire("Мозг лягушки")
            .containing(Substance.DISREGARD, 2)
            .containing(Substance.SPEED, 1)
            .build()),

    HORNED_LIZARD_BLOOD(IngredientDefinition.ofFire("Кровь жабовидной ящерицы")
            .containing(Substance.ABANDON, 2)
            .containing(Substance.SPEED, 1)
            .build()),

    SPOONWING(IngredientDefinition.ofFire("Нитекрылка")
            .containing(Substance.LIGHTNESS, 2)
            .containing(Substance.FINESSE, 1)
            .build()),

    LEECH(IngredientDefinition.ofFire("Пиявка")
            .containing(Substance.SLOWNESS, 2)
            .containing(Substance.UGLINESS, 1)
            .build()),

    DRIED_SCARAB(IngredientDefinition.ofEarth("Сушеный скарабей")
            .containing(Substance.YOUTH, 2)
            .containing(Substance.STRENGTH, 1)
            .build()),

    WOLFSBANE_ROOT(IngredientDefinition.ofEarth("Корень борца")
            .containing(Substance.SICKNESS, 5)
            .containing(Substance.NUMBNESS, 3)
            .containing(Substance.CONFUSION, 1)
            .build()),

    NIGHTSHADE_BERRY(IngredientDefinition.ofFire("Ягоды беладонны")
            .containing(Substance.OBSESSION, 5)
            .containing(Substance.IRRATIONALITY, 3)
            .containing(Substance.BEAUTY, 1)
            .build()),

    BLACK_HELLEBORE_LEAVES(IngredientDefinition.ofWater("Лист чёрного морозника")
            .containing(Substance.EUPHORIA, 5)
            .containing(Substance.RATIONALITY, 3)
            .containing(Substance.SICKNESS, 1)
            .build()),

    WORMWOOD_LEAVES(IngredientDefinition.ofWater("Лист горькой полыни")
            .containing(Substance.WEAKNESS, 5)
            .containing(Substance.IMMOBILITY, 3)
            .containing(Substance.SORROW, 1)
            .build()),

    LIONFISH_RAY(IngredientDefinition.ofFire("Луч крылатки")
            .containing(Substance.CLUMSINESS, 2)
            .containing(Substance.WEAKNESS, 1)
            .build()),

    SNAKE_TOOTH(IngredientDefinition.ofFire("Змеиный зуб")
            .containing(Substance.DISHONESTY, 2)
            .containing(Substance.INTELLECT, 1)
            .build()),

    SNAKE_EYE(IngredientDefinition.ofFire("Змеиный глаз")
            .containing(Substance.INTELLECT, 2)
            .containing(Substance.MEMORY, 1)
            .build()),

    MANDRAKE_ROOT(IngredientDefinition.ofEarth("Корень мандрагоры")
            .containing(Substance.HEALTH, 5)
            .containing(Substance.MOBILITY, 3)
            .containing(Substance.YOUTH, 1)
            .build()),

    SOPOPHORA_BEAN(IngredientDefinition.ofFire("Боб сопофоры")
            .containing(Substance.SLEEPINESS, 5)
            .containing(Substance.FATIGUE, 3)
            .containing(Substance.STUPIDITY, 1)
            .build()),

    ACROMANTULA_POISON(IngredientDefinition.ofFire("Яд акромантулы")
            .containing(Substance.ABANDON, 5)
            .containing(Substance.VIGOR, 3)
            .containing(Substance.DISREGARD, 1)
            .build()),

    DOXY_EGGS(IngredientDefinition.ofFire("Яйца докси")
            .containing(Substance.ABANDON, 5)
            .containing(Substance.EMOTIONALITY, 3)
            .containing(Substance.CLUMSINESS, 1)
            .build()),

    BOOMSLANG_SKIN(IngredientDefinition.ofEarth("Шкура бумсланга")
            .containing(Substance.FINESSE, 5)
            .containing(Substance.CORPOREALITY, 3)
            .containing(Substance.YOUTH, 1)
            .build()),

    UNICORN_HORN_POWDER(IngredientDefinition.ofEarth("Порошок рога единорога")
            .containing(Substance.CLARITY, 5)
            .containing(Substance.VIGOR, 3)
            .containing(Substance.OBSESSION, 1)
            .build()),

    SHRIVELFIG(IngredientDefinition.ofFire("Плод абиссинской смоковницы")
            .containing(Substance.TININESS, 2)
            .containing(Substance.HEAVINESS, 1)
            .build()),

    SALAMANDER_BLOOD(IngredientDefinition.ofFire("Кровь саламандры")
            .containing(Substance.DISTRACTION, 5)
            .containing(Substance.MOBILITY, 3)
            .containing(Substance.HEALTH, 1)
            .build()),

    UNICORN_HAIR(IngredientDefinition.ofEarth("Волос единорога")
            .containing(Substance.SINCERITY, 5)
            .containing(Substance.INCORPOREALITY, 3)
            .containing(Substance.BEAUTY, 1)
            .build()),

    MOONSTONE(IngredientDefinition.ofEarth("Лунный камень")
            .containing(Substance.CLARITY, 3)
            .containing(Substance.BEAUTY, 1)
            .build()),

    GRIFFIN_CLAW(IngredientDefinition.ofEarth("Коготь грифона")
            .containing(Substance.STRENGTH, 5)
            .containing(Substance.RATIONALITY, 3)
            .containing(Substance.CONCENTRATION, 1)
            .build()),

    PORCUPINE_QUILL(IngredientDefinition.ofEarth("Игла дикобраза")
            .containing(Substance.FINESSE, 2)
            .containing(Substance.BULK, 1)
            .build()),

    FLY_AMANITA(IngredientDefinition.ofEarth("Красный мухомор")
            .containing(Substance.EUPHORIA, 2)
            .containing(Substance.CALM, 1)
            .build()),

    HOLLY_BERRY(IngredientDefinition.ofFire("Ягоды остролиста")
            .containing(Substance.STRENGTH, 2)
            .containing(Substance.EUPHORIA, 1)
            .build()),

    SAGE_LEAF(IngredientDefinition.ofWater("Лист шалфея")
            .containing(Substance.CLARITY, 2)
            .containing(Substance.AGE, 1)
            .build()),

    ROMAN_SNAIL(IngredientDefinition.ofEarth("Виноградная улитка")
            .containing(Substance.DISTRACTION, 2)
            .containing(Substance.SLOWNESS, 1)
            .build())

    ;

    private final IngredientDefinition definition;

    private KnownIngredient(IngredientDefinition definition) {
        this.definition = definition;
    }

    @Override
    public IngredientDefinition toFire() {
        return definition.toFire();
    }

    @Override
    public Ingredient toWater() {
        return definition.toWater();
    }

    @Override
    public Ingredient toEarth() {
        return definition.toEarth();
    }

    @Override
    public String getName() {
        return definition.getName();
    }

    @Override
    public Element getElement() {
        return definition.getElement();
    }

    @Override
    public Map<Substance, Integer> getSubstances() {
        return definition.getSubstances();
    }

    @Override
    public AddedIngredient mutableCopy() {
        return definition.mutableCopy();
    }

}
