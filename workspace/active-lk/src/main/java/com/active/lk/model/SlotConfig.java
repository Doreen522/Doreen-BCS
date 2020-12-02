package com.active.lk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="SlotConfig")
public class SlotConfig {
	
	@Id
	private String id;
	
	private String startTime;
	
	private String endTime;
	
	private String courtId;
	
	private String activityId;
	
	private String institutionId;
	
	private String day;
	
	private long normalDayAdultPrice;
	
	private long normalDayStudPrice;
	
	private long holidayAdultPrice;
	
	private long holidayStudPrice;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCourtId() {
		return courtId;
	}

	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


	public long getNormalDayAdultPrice() {
		return normalDayAdultPrice;
	}

	public void setNormalDayAdultPrice(long normalDayAdultPrice) {
		this.normalDayAdultPrice = normalDayAdultPrice;
	}

	public long getNormalDayStudPrice() {
		return normalDayStudPrice;
	}

	public void setNormalDayStudPrice(long normalDayStudPrice) {
		this.normalDayStudPrice = normalDayStudPrice;
	}

	public long getHolidayAdultPrice() {
		return holidayAdultPrice;
	}

	public void setHolidayAdultPrice(long holidayAdultPrice) {
		this.holidayAdultPrice = holidayAdultPrice;
	}

	public long getHolidayStudPrice() {
		return holidayStudPrice;
	}

	public void setHolidayStudPrice(long holidayStudPrice) {
		this.holidayStudPrice = holidayStudPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	
	
	
	
	

}
