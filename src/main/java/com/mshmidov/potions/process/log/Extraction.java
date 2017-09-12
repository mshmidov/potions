package com.mshmidov.potions.process.log;

import java.util.Optional;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableMultiset.Builder;
import com.google.common.collect.Multiset;
import com.mshmidov.potions.definition.Substance;

import static java.util.stream.Collectors.joining;

public final class Extraction implements LogEntry {

    private final Multiset<Substance> extracted;

    public Extraction(Substance substance, int quantity) {
        this.extracted = ImmutableMultiset.<Substance> builder().addCopies(substance, quantity).build();
    }

    private Extraction(Multiset<Substance> extractedA, Multiset<Substance> extractedB) {
        final Builder<Substance> builder = ImmutableMultiset.builder();
        extractedA.forEachEntry(builder::addCopies);
        extractedB.forEachEntry(builder::addCopies);

        this.extracted = builder.build();
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.ofNullable(other)
                .filter(e -> e instanceof Extraction)
                .map(e -> (Extraction) e)
                .map(e -> new Extraction(this.extracted, e.extracted));
    }

    @Override
    public String toString() {
        return extracted.entrySet().stream()
                .map(e -> String.format("%s %s", e.getElement(), e.getCount()))
                .sorted()
                .collect(joining(", ", "Экстрагируются ", "."));
    }
}
