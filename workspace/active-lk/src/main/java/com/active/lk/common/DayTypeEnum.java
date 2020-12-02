package com.active.lk.common;

public enum DayTypeEnum {
	
	DAY_TYPE_NORMAL("NORMAL", "Normal Day"),
	DAY_TYPE_HOLIDAY("HOLIDAY", "Holiday");
	

	private final String code;
	private final String description;

	DayTypeEnum(String code, String description) {
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
