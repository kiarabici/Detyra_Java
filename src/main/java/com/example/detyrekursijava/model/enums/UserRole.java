package com.example.detyrekursijava.model.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserRole fromString(String text) {
        for (UserRole userType : UserRole.values()) {
            if (userType.value.equalsIgnoreCase(text)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
