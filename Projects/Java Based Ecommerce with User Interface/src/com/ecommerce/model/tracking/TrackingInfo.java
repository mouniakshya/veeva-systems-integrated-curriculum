package com.ecommerce.model.tracking;

import com.ecommerce.model.order.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Encapsulates courier tracking information for an order.
 * Satisfies Requirement 6.
 */
public class TrackingInfo {
    private final String trackingNumber;
    private final String courierPartner;
    private OrderStatus currentStatus;
    private String currentLocation;
    private LocalDateTime estimatedDelivery;
    private final List<TrackingCheckpoint> checkpoints = new ArrayList<>();

    public TrackingInfo(String trackingNumber, String courierPartner, String initialLocation) {
        this.trackingNumber = Objects.requireNonNull(trackingNumber, "Tracking number cannot be null");
        this.courierPartner = Objects.requireNonNull(courierPartner, "Courier partner cannot be null");
        this.currentLocation = initialLocation != null ? initialLocation : "Fulfillment Center";
        this.currentStatus = OrderStatus.DISPATCHED;
        this.estimatedDelivery = LocalDateTime.now().plusDays(3);

        addCheckpoint(currentLocation, "Package picked up by " + courierPartner);
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getCourierPartner() {
        return courierPartner;
    }

    public synchronized OrderStatus getCurrentStatus() {
        return currentStatus;
    }

    public synchronized String getCurrentLocation() {
        return currentLocation;
    }

    public synchronized LocalDateTime getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public synchronized void setEstimatedDelivery(LocalDateTime estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    public synchronized void addCheckpoint(String location, String message) {
        this.currentLocation = location;
        checkpoints.add(new TrackingCheckpoint(LocalDateTime.now(), location, message));
    }

    public synchronized void updateStatus(OrderStatus status, String location, String message) {
        this.currentStatus = status;
        this.currentLocation = location;
        addCheckpoint(location, message);
    }

    public synchronized List<TrackingCheckpoint> getCheckpoints() {
        return Collections.unmodifiableList(new ArrayList<>(checkpoints));
    }

    public synchronized String getFormattedTrackingReport() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("=================== COURIER TRACKING DETAILS ===================\n");
        sb.append(String.format("Tracking Number: %s\n", trackingNumber));
        sb.append(String.format("Courier Partner: %s\n", courierPartner));
        sb.append(String.format("Current Status : %s\n", currentStatus.getDescription()));
        sb.append(String.format("Current Hub    : %s\n", currentLocation));
        sb.append(String.format("Estimated ETA  : %s\n", estimatedDelivery.format(fmt)));
        sb.append("------------------------- Checkpoint History --------------------\n");
        for (TrackingCheckpoint cp : checkpoints) {
            sb.append("  * ").append(cp.toString()).append("\n");
        }
        sb.append("================================================================");
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("TrackingInfo[#%s via %s - %s @ %s]",
                trackingNumber, courierPartner, currentStatus, currentLocation);
    }
}
