package org.template.enums;

public enum RoleType {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    public static RoleType getRoleByName(String name) {
        for (RoleType roleType : values()) {
            if (roleType.name.equals(name)) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("No Enum for name " + name);
    }

    public String getName() {
        return name;
    }
}
