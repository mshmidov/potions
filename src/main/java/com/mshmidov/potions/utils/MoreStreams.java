package com.mshmidov.potions.utils;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public final class MoreStreams {

    private MoreStreams() {
    }

    public static <T> Stream<Pair<Integer,T>> indexed(List<T> list) {
        return IntStream.range(0, list.size()).mapToObj(i-> Pair.with(i, list.get(i)));
    }

}
