package com.active.lk.service;

import java.util.List;

import com.active.lk.model.Court;

public interface CourtService {
	public Court createCourt(Court court);
	
	public List<Court> getAllCourts();
	
	public Court getCourt(String courtId);
	
	public List<Court> getActiveCourtByactivId(String activId);
	
	public List<Court> getCourtByactivId(String activId);
	
	public List<Court> getCourtByslotId(String slotId);
	
	public void changeCourtStatus(String courtId,String status);
	
}
