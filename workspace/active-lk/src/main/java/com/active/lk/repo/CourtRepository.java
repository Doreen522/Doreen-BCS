package com.active.lk.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.active.lk.model.Court;



public interface CourtRepository extends MongoRepository<Court, String> {

	@Query("{'activId' : '?0','status' : '?1'}")
	List<Court> findByactivId(String activId,String status);
	

}
