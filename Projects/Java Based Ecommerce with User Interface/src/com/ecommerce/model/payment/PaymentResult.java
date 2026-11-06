package com.ecommerce.model.payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Value object representing the result and receipt of a payment attempt.
 */
public class PaymentResult {
    private final boolean success;
    private final String transactionId;
    private final PaymentStatus status;
    private final String paymentMethodType;
    private final double amount;
    private final String message;
    private final LocalDateTime timestamp;

    public PaymentResult(boolean success, String transactionId, PaymentStatus status,
                         String paymentMethodType, double amount, String message) {
        this.success = success;
        this.transactionId = transactionId;
        this.status = status;
        this.paymentMethodType = paymentMethodType;
        this.amount = amount;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static PaymentResult success(String txId, String methodType, double amount, String msg) {
        return new PaymentResult(true, txId, PaymentStatus.SUCCESS, methodType, amount, msg);
    }

    public static PaymentResult pending(String txId, String methodType, double amount, String msg) {
        return new PaymentResult(true, txId, PaymentStatus.PENDING_ON_DELIVERY, methodType, amount, msg);
    }

    public static PaymentResult failure(String txId, String methodType, double amount, String msg) {
        return new PaymentResult(false, txId, PaymentStatus.FAILED, methodType, amount, msg);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("PaymentResult[TxID=%s, Method=%s, Amount=₹%,.2f, Status=%s, Msg='%s', Time=%s]",
                transactionId, paymentMethodType, amount, status, message, timestamp.format(fmt));
    }
}
