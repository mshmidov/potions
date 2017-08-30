package com.mshmidov.potions.definition;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public enum Element {
    AIR, FIRE, WATER, EARTH;

    private static final Map<Element, Element> OPPOSITES = ImmutableMap.of(
            AIR, EARTH,
            FIRE, WATER,
            WATER, FIRE,
            EARTH, AIR);

    public Element getOpposite() {
        return OPPOSITES.get(this);
    }
}
