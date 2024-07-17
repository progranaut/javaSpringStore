package com.ivlev.javaspringstore.enums;

import java.util.Arrays;

public enum RoleType {

    ROLE_ADMIN,

    ROLE_USER;

    public static RoleType fromString (String role){
        return Arrays.stream(RoleType.values())
                .filter(roleType -> roleType.name().equals(role))
                .findAny()
                .orElse(null);
    }

}
