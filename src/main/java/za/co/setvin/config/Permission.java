package za.co.setvin.config;

public enum Permission {
    TRAINEE_READ("trainee:read"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    Permission(String permission){
        this.permission = permission;
    }

    private String permission;

    public String getPermission() {
        return permission;
    }
}
