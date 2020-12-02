package com.active.lk.repo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.active.lk.model.SlotConfig;

public interface SlotConfigRepository extends MongoRepository <SlotConfig, String> {
	
	@Query("{ 'courtId' : ?0 }")
	
	SlotConfig findByCtId(String courtId);
	
	
	@Query("{ 'status' : ?0 }")
	List<SlotConfig> findAllActiveSlotConfig(String status);
	
	@Query("{ 'institutionId' : '?0','activityId' : '?1','status' : '?2'}")
	List<SlotConfig> getByInstIdAndActivity(String institutionId, String activityId,String status);
	
	@Query("{ 'institutionId' : '?0','activityId' : '?1','courtId' : '?2','status' : '?3','day' : '?4'}")
	List<SlotConfig> getByInstIdAndActvAdnDay(String institutionId, String activityId,String courtId,String status,String day);
	
	@Query("{'status' : '?0','day' : '?1'}")
	List<SlotConfig> findAllActiveSlotConfigByDay(String status,String day);
	
	@Query("{ 'institutionId' : '?0','status' : '?1'}")
	List<SlotConfig> getByInstitution(String institutionId,String status);
	
	@Query("{ 'institutionId' : '?0'}")
	List<SlotConfig> getAllByInstitution(String institutionId);
	
	


	

	

}
