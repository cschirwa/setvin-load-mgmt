package za.co.setvin.entity.utility;

public enum Permission {
	
	ADMIN_READ("admin_read"), ADMIN_WRITE("admin_write"),
	REGULAR_READ("regular_read");

	private String permission;
	
	Permission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}

}
