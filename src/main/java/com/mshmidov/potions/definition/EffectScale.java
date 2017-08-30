package com.mshmidov.potions.definition;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

public enum EffectScale {
    WEAK("слабый"),
    COMMON("обычный"),
    QUICK("быстрый"),
    STRONG("сильный"),
    LONG("долгий");

    private final String name;

    private EffectScale(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Set<EffectScale> forEffect(Verb verb, int quantity) {
        final Builder<EffectScale> result = ImmutableSet.<EffectScale> builder();

        if (quantity < 3) {
            result.add(EffectScale.WEAK);

        } else if (quantity < 7) {
            result.add(EffectScale.COMMON);

        } else if (quantity < 13) {
            if (verb.isAeration()) {
                result.add(QUICK);

            } else if (verb.isHeating()) {
                result.add(STRONG);

            } else if (verb.isHydration()) {
                result.add(LONG);

            } else {
                result.add(LONG);
            }

        } else if (quantity < 21) {

            if (verb.isAeration()) {
                result.add(QUICK);

            }
            if (verb.isHeating()) {
                result.add(STRONG);

            }
            if (verb.isHydration()) {
                result.add(LONG);
            }

        } else {
            result.add(QUICK);
            result.add(STRONG);
            result.add(LONG);
        }

        return result.build();
    }
}
