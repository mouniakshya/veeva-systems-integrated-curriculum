package com.ecommerce.model.tracking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Immutable tracking checkpoint milestone.
 */
public final class TrackingCheckpoint {
    private final LocalDateTime timestamp;
    private final String location;
    private final String updateMessage;

    public TrackingCheckpoint(LocalDateTime timestamp, String location, String updateMessage) {
        this.timestamp = Objects.requireNonNull(timestamp, "Timestamp cannot be null");
        this.location = location != null ? location : "In Transit Hub";
        this.updateMessage = updateMessage != null ? updateMessage : "Status updated";
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] @ %s: %s", timestamp.format(fmt), location, updateMessage);
    }
}
