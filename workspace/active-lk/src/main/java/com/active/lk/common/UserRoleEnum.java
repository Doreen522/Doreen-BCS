package com.active.lk.common;

public enum UserRoleEnum {
	
	USER_ROLE_USER("USER", "User"),
	USER_ROLE_ADMIN("ADMIN", "Admin");
	

	private final String code;
	private final String description;

	UserRoleEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}


}
