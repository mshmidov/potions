package com.mshmidov.potions.process.log;

import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Consumer;

public final class BrewingLog {

    private final LinkedList<LogEntry> entries = new LinkedList<>();

    public void add(LogEntry entry) {
        final Optional<LogEntry> merged = entries.isEmpty()
                ? Optional.empty()
                : entries.getLast().merge(entry);

        if (merged.isPresent()) {
            entries.removeLast();
            merged.ifPresent(entries::add);
        } else {
            entries.add(entry);
        }

    }

    public void addAll(BrewingLog otherLog) {
        otherLog.forEach(this::add);
    }

    public void forEach(Consumer<LogEntry> action) {
        entries.forEach(action);
    }
}
