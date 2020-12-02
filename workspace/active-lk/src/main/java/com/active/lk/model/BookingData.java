package com.active.lk.model;

public class BookingData {
	
	private String userId;
	
	private String slotConfigId;
	
	private long price;
	
	private String slotDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSlotConfigId() {
		return slotConfigId;
	}

	public void setSlotConfigId(String slotConfigId) {
		this.slotConfigId = slotConfigId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}
	
	
	

}
