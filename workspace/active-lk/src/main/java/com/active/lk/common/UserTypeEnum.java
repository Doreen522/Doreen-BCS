package com.active.lk.common;

public enum UserTypeEnum {
	
	USER_TYPE_CHLD("CHLD", "Child"),
	USER_TYPE_ADLT("ADLT", "Adult");
	

	private final String code;
	private final String description;

	UserTypeEnum(String code, String description) {
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
