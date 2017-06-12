package com.endava.enums;

/**
 * Created by vsaban on 3/16/2017.
 */
public enum Role {
    USER,
    ADMIN;

    public static Role getRole(String roleName) {
        return Role.valueOf(Role.class, roleName);
    }

    public static class Names {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }
}
