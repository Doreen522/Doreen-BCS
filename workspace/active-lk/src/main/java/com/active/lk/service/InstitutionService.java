package com.active.lk.service;

import java.util.List;

import com.active.lk.model.BookingNumbers;
import com.active.lk.model.Institution;

public interface InstitutionService {
	public Institution createInstitution(Institution institution);
	
	public List<Institution> getAllInstitution();
	
	public List<Institution> getAllInstitutionByStatus(String status);
	
	public Institution getInstitution(String instId);
	
	public Institution getInstitutionByuserId(String userId);
	
	public List<Institution> getInstitutionByActivity(String activityId);
	
	public BookingNumbers getBookingNumbers(String institutionId);
	
	
	

}
