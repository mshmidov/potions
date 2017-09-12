package com.mshmidov.potions.process.log;

import java.util.Optional;

public interface LogEntry {

    Optional<LogEntry> merge(LogEntry other);
}
