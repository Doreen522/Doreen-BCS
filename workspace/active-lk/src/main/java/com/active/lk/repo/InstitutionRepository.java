package com.active.lk.repo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.active.lk.model.Institution;



public interface InstitutionRepository extends MongoRepository<Institution, String> {
	
	@Query("{'userid' : ?0}")
	Institution findByUserid (String userid);
	
	@Query("{'activities.id' : '?0', 'status' : '?1'}")
	List<Institution> findAllByActivity(String activityId,String status);
	
	@Query("{'status' : '?0'}")
	List<Institution> findAllByStatus(String status);
	

	

}
