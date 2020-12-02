package com.active.lk.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.active.lk.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	@Query("{ 'uuid' : '?0' }")
	User findByUuid(String uuid);

}
