package com.active.lk.service;

import java.util.List;

import com.active.lk.model.Activity;

public interface ActivityService {
	
	public Activity createActivity(Activity activity);
	
	public List<Activity> getAllActivity();
	
	public List<Activity> getAllActiveActivity(); 
	
	public Activity getActivity(String acId);
	
	public Activity getActivityByInstitutionId(String instId);
	
	
}
