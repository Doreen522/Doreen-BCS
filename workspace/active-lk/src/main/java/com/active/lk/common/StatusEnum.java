package com.active.lk.common;

public enum StatusEnum {
	
	ACTIVITY_STATUS_ACTIVE("ACTIVE" , "Active Activity"),
	ACTIVITY_STATUS_PENDING("PENDING" , "Pending Activity Information"),
	ACTIVITY_STATUS_DELETED("DELETED" , "Activity Deleted"),
	BOOKING_STATUS_ACTIVE("ACTIVE" , "Active Booking"),
	BOOKING_STATUS_ACTUALIZED("ACTUALIZED" , "Actualized Booking Information"),
	BOOKING_STATUS_DELETED("DELETED" , "Booking Deleted"),
	COURT_STATUS_ACTIVE("ACTIVE" , "Active Court"),
	COURT_STATUS_PENDING("PENDING" , "Pending Court Information"),
	COURT_STATUS_DELETED("DELETED" , "Court Deleted"),
	INSTITUTION_STATUS_ACTIVE("ACTIVE","Active Institution"),
	INSTITUTION_STATUS_PENDING("PENDING", "Pending Istitution info"),
	INSTITUTION_STATUS_DELETED("DELETED", "Deactivate Institution"),
	SLOTCONFIG_STATUS_ACTIVE("ACTIVE" , "Active SlotConfig"),
	SLOTCONFIG_STATUS_PENDING("PENDING" , "Pending SlotConfig Information"),
	SLOTCONFIG_STATUS_DELETED("DELETE" , "SlotConfig Deleted"),
	SLOT_STATUS_ACTIVE("ACTIVE" , "Active Slot"),
	SLOT_STATUS_ACTUALIZED("ACTUALIZED" , "Actualized Slots"),
	SlOT_STATUS_BOOKED("BOOKED" , "Slot Booked"),
	SlOT_STATUS_DELETED("DELETED" , "Slot Deleted"),
	USER_STATUS_ACTIVE("ACTIVE", "Active User"),
	USER_STATUS_PENDING("PENDING", "Pending User Information"),
	USER_STATUS_DELETED("DELETED", "Deactivated User");
	
	
	
	
	private final String code;
	private final String description;
	
	private StatusEnum(String code, String description) {
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
