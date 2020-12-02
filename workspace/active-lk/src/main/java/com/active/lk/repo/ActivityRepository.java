package com.active.lk.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.active.lk.model.Activity;



public interface ActivityRepository extends MongoRepository<Activity, String> {
	
	@Query("{'instId' : ?0}")
	Activity findByInstitutionId(String instId); 
	
	@Query("{'status' : ?0}")
	List<Activity> getAllActiveActivity(String status); 
		
	
	

}
