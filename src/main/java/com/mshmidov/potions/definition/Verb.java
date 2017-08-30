package com.mshmidov.potions.definition;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

public enum Verb {

    CHANGE(true, true, true),
    INDUCE(true, false, true),
    DAMAGE(true, true, false),
    DECREASE(true, false, false),
    PROTECT(false, true, true),
    RESTORE(false, true, false),
    INCREASE(false, false, true),
    BIND(false, false, false);

    private final boolean heating;

    private final boolean hydration;

    private final boolean aeration;

    private final Set<Element> oppositeElements;

    private Verb(boolean heating, boolean hydration, boolean aeration) {
        this.heating = heating;
        this.hydration = hydration;
        this.aeration = aeration;

        final Builder<Element> result = ImmutableSet.<Element> builder();

        result.add(heating ? Element.FIRE.getOpposite() : Element.FIRE);
        result.add(hydration ? Element.WATER.getOpposite() : Element.WATER);
        result.add(aeration ? Element.AIR.getOpposite() : Element.AIR);

        oppositeElements = result.build();
    }

    public Set<Element> oppositeElements() {
        return oppositeElements;
    }

    public boolean isHeating() {
        return heating;
    }

    public boolean isHydration() {
        return hydration;
    }

    public boolean isAeration() {
        return aeration;
    }

}
