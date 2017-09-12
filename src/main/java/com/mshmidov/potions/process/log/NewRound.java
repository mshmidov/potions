package com.mshmidov.potions.process.log;

import java.util.Optional;

public final class NewRound implements LogEntry {

    private final int round;

    public NewRound(int round) {
        this.round = round;
    }

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("%nРаунд %s.", round);
    }
}
