package com.active.lk.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.active.lk.model.Slot;

public interface SlotRepository extends MongoRepository <Slot, String> {

	@Query("{'acid' : ?0}")
	Slot findByActivityId(String acId);
	
	@Query("{'institutionId' : ?0,'status' : ?1}")
	List<Slot> findByInstitutionId(String institutionId,String status);
	
	@Query("{'slotConfigId' : '?0','status' : '?1'}")
	List<Slot> findBySlotConfigId(String slotConfigId,String status);
	
	@Query("{'slotConfigId' : '?0','status' : '?1','slotDate' : '?2'}")
	Slot getSlotBySlotConfigIdAndDate(String slotConfigId,String status,String slotDate);
	
	@Query("{'slotDate' : '?0','status' : '?1'}")
	List<Slot> getSlotByDateAndStatus(String slotDate,String status);
	
	@Query("{'slotConfigId' : '?0','slotDate' : {$regex:'?1'}}")
	List<Slot> getSlotBySlotConfigIdAndDuration(String slotConfigId,String date);
	
	
	

	
	
	

}
