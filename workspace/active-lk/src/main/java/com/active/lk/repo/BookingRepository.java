package com.active.lk.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import com.active.lk.model.Booking;



public interface BookingRepository extends MongoRepository<Booking, String> {

	@Query("{'userId' : '?0','status' : '?1'}")
	List<Booking> findByUserId(String userId,String status);

	@Query("{'slotId' : '?0','status' : '?1'}")
	Booking findBySlotId(String slotId,String status);
	
	@Query("{'bookingRef' : ?0}")
	Booking findByBkref(String bookingRef);
	
	
	

}
