package com.mshmidov.potions.process.log;

import java.util.Optional;

import com.mshmidov.potions.definition.Element;
import com.mshmidov.potions.definition.Verb;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.capitalize;

public final class NewVerb implements LogEntry {

    private final Verb verb;

    public NewVerb(Verb verb) {
        this.verb = verb;
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.empty();
    }

    @Override
    public String toString() {

        return String.format("Глагол зелья: %s. Оппозитные элементы: %s.",
                verb,
                verb.oppositeElements().stream()
                        .filter(e -> e != Element.AIR)
                        .map(e -> capitalize(e.name().toLowerCase()))
                        .collect(joining(", ")));
    }
}
