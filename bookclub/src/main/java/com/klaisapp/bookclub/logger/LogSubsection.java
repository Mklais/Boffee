package com.klaisapp.bookclub.logger;

public enum LogSubsection {
    REGISTRATION("Registration"),
    LOGIN("Login"),
    LOGOUT("Logout"),
    CRUD("CRUD"),
    NONE("");

    private final String display;

    LogSubsection(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return this.display;
    }
}
