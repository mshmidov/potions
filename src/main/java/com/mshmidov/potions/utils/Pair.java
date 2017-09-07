package com.mshmidov.potions.utils;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class Pair<A, B> {

    private final A a;

    private final B b;

    public static <A, B> Pair<A, B> with(A a, B b) {
        return new Pair<>(a, b);
    }

    private Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getLeft() {
        return a;
    }

    public B getRight() {
        return b;
    }

    public <A1, B1> Pair<A1, B1> map(BiFunction<A, B, Pair<A1, B1>> mapper) {
        return mapper.apply(a, b);
    }

    public <A1> Pair<A1, B> mapLeft(BiFunction<A, B, A1> mapper) {
        return Pair.with(mapper.apply(a, b), b);
    }

    public <A1> Pair<A1, B> mapLeft(Function<A, A1> mapper) {
        return Pair.with(mapper.apply(a), b);
    }

    public <B1> Pair<A, B1> mapRight(BiFunction<A, B, B1> mapper) {
        return Pair.with(a, mapper.apply(a, b));
    }

    public <B1> Pair<A, B1> mapRight(Function<B, B1> mapper) {
        return Pair.with(a, mapper.apply(b));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        final Pair<?, ?> other = (Pair<?, ?>) obj;

        return Objects.equals(this.a, other.a)
                && Objects.equals(this.b, other.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a,b);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).build();
    }

}
