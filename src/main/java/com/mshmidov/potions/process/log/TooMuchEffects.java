package com.mshmidov.potions.process.log;

import java.util.Optional;

public final class TooMuchEffects implements LogEntry {

    @Override
    public Optional<LogEntry> merge(LogEntry other) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Зелье содержит слишком много эффектов; все эффекты становятся побочными.";
    }
}
