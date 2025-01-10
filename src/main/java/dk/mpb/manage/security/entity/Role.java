package dk.mpb.manage.security.entity;

/**
 * Enum representing user roles in the system.
 */
public enum Role {
    USER,
    ADMIN;
    public static Role fromString(String roleString) {
        return Role.valueOf(roleString.trim().toUpperCase());
    }
}
