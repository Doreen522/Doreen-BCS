package com.active.lk.serviceImpl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.common.StatusEnum;
import com.active.lk.model.Activity;
import com.active.lk.repo.ActivityRepository;
import com.active.lk.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{

	
	@Autowired 
	ActivityRepository activeRepo;
	
	
	
	@Override
	public Activity createActivity(Activity activity) {
		return activeRepo.save(activity);
	}

	@Override
	public List<Activity> getAllActivity() {
		
		return activeRepo.findAll();
	}

	@Override
	public Activity getActivity(String acId) {
	  Activity activity=activeRepo.findById(acId).get();
		return activity;
		
	}


	@Override
	public Activity getActivityByInstitutionId(String instId) {
		Activity activity= activeRepo.findByInstitutionId(instId);
		return activity;
	}

	@Override
	public List<Activity> getAllActiveActivity() {
		return activeRepo.getAllActiveActivity(StatusEnum.ACTIVITY_STATUS_ACTIVE.getCode().toString());
	}

}
