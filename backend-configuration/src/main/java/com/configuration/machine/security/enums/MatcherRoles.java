package com.configuration.machine.security.enums;

public enum MatcherRoles {

    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    MatcherRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
