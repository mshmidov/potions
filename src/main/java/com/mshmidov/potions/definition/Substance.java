package com.mshmidov.potions.definition;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;

public enum Substance {

    LIFE, DEATH,
    BODY, GHOST,
    MIND, OBEDIENCE,
    CORPOREALITY, INCORPOREALITY,
    MOBILITY, IMMOBILITY,
    VIGOR, FATIGUE,
    EMOTIONALITY, CALM,
    RATIONALITY, IRRATIONALITY,
    SENSITIVITY, NUMBNESS,
    AGE, YOUTH,
    BULK, TININESS,
    BEAUTY, UGLINESS,
    FINESSE, CLUMSINESS,
    SPEED, SLOWNESS,
    LIGHTNESS, HEAVINESS,
    HEALTH, SICKNESS,
    ALERTNESS, SLEEPINESS,
    STRENGTH, WEAKNESS,
    EUPHORIA, SORROW,
    OBSESSION, INDIFFERENCE,
    SINCERITY, DISHONESTY,
    MEMORY, FORGETFULNESS,
    INTELLECT, STUPIDITY,
    CONCENTRATION, CONFUSION,
    CLARITY, DISTRACTION,
    RESTRAINT, ABANDON,
    AWARENESS, DISREGARD;

    public static final BiMap<Substance, Substance> OPPOSITES = ImmutableBiMap.<Substance, Substance> builder()
            .put(LIFE, DEATH)
            .put(BODY, GHOST)
            .put(MIND, OBEDIENCE)
            .put(CORPOREALITY, INCORPOREALITY)
            .put(MOBILITY, IMMOBILITY)
            .put(VIGOR, FATIGUE)
            .put(EMOTIONALITY, CALM)
            .put(RATIONALITY, IRRATIONALITY)
            .put(SENSITIVITY, NUMBNESS)
            .put(AGE, YOUTH)
            .put(BULK, TININESS)
            .put(BEAUTY, UGLINESS)
            .put(FINESSE, CLUMSINESS)
            .put(SPEED, SLOWNESS)
            .put(LIGHTNESS, HEAVINESS)
            .put(HEALTH, SICKNESS)
            .put(ALERTNESS, SLEEPINESS)
            .put(STRENGTH, WEAKNESS)
            .put(EUPHORIA, SORROW)
            .put(OBSESSION, INDIFFERENCE)
            .put(SINCERITY, DISHONESTY)
            .put(MEMORY, FORGETFULNESS)
            .put(INTELLECT, STUPIDITY)
            .put(CONCENTRATION, CONFUSION)
            .put(CLARITY, DISTRACTION)
            .put(RESTRAINT, ABANDON)
            .put(AWARENESS, DISREGARD)
            .build();

    public static final SetMultimap<Substance, Substance> FIRST_ORDER = ImmutableSetMultimap.<Substance, Substance>builder()
            .putAll(LIFE, BODY, MIND)
            .putAll(DEATH, GHOST, OBEDIENCE)
            .build();

    public static final SetMultimap<Substance, Substance> SECOND_ORDER = ImmutableSetMultimap.<Substance, Substance>builder()
            .putAll(BODY, CORPOREALITY, MOBILITY, VIGOR)
            .putAll(GHOST, INCORPOREALITY, IMMOBILITY, FATIGUE)
            .putAll(MIND, EMOTIONALITY, RATIONALITY, SENSITIVITY)
            .putAll(OBEDIENCE, CALM, IRRATIONALITY, NUMBNESS)
            .build();

    public static final SetMultimap<Substance, Substance> THIRD_ORDER = ImmutableSetMultimap.<Substance, Substance>builder()
            .putAll(CORPOREALITY, AGE, BULK, BEAUTY)
            .putAll(INCORPOREALITY, YOUTH, TININESS, UGLINESS)
            .putAll(MOBILITY, FINESSE, SPEED, LIGHTNESS)
            .putAll(IMMOBILITY, CLUMSINESS, SLOWNESS, HEAVINESS)
            .putAll(VIGOR, HEALTH, ALERTNESS, STRENGTH)
            .putAll(FATIGUE, SICKNESS, SLEEPINESS, WEAKNESS)
            .putAll(EMOTIONALITY, EUPHORIA, OBSESSION, SINCERITY)
            .putAll(CALM, SORROW, INDIFFERENCE, DISHONESTY)
            .putAll(RATIONALITY, MEMORY, INTELLECT, CONCENTRATION)
            .putAll(IRRATIONALITY, FORGETFULNESS, STUPIDITY, CONFUSION)
            .putAll(SENSITIVITY, CLARITY, RESTRAINT, AWARENESS)
            .putAll(NUMBNESS, DISTRACTION, ABANDON, DISREGARD)
            .build();

    public static final SetMultimap<Integer, Substance> SUBSTANCE_ORDER =ImmutableSetMultimap.<Integer, Substance>builder()
            .putAll(4, THIRD_ORDER.values())
            .putAll(3, THIRD_ORDER.keySet())
            .putAll(2, SECOND_ORDER.keySet())
            .putAll(1, FIRST_ORDER.keySet())
            .build();

    public Substance getOpposite() {
        if (OPPOSITES.containsKey(this)) {
            return OPPOSITES.get(this);
        } else {
            return OPPOSITES.inverse().get(this);
        }
    }

    public int getOrder() {
       return SUBSTANCE_ORDER.keys().stream()
               .filter(order -> SUBSTANCE_ORDER.get(order).contains(this))
               .findFirst()
               .orElseThrow(() -> new IllegalStateException("Cannot find order for substance " + this));
    }
}
