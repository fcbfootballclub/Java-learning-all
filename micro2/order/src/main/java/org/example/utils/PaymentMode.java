package org.example.utils;

public enum PaymentMode {
    CASH("cash"),
    CREDIT_CARD("credit_card");
    private String mode;

    private PaymentMode(String mode) {
        this.mode = mode;
    }

    public static PaymentMode getMode(String mode) {
        for(PaymentMode paymentMode : PaymentMode.values()) {
            if (mode.equals(paymentMode.name())) {
                return paymentMode;
            }
        }
        return null;
    }

    public String getMode() {
        return mode;
    }
}
