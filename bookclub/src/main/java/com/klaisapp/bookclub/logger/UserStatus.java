package com.klaisapp.bookclub.logger;

public enum UserStatus {
    ACTIVE("active"),
    INACTIVE("inactive");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
