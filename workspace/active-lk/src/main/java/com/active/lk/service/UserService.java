package com.active.lk.service;

import java.util.List;

import com.active.lk.model.User;

public interface UserService {
	public User createUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUser(String userId);
	
	public User addMoreUserInfo(User user);
	
	public User getUserByuuId(String uuid);
	
	public boolean isExistByUUID(String uuid);
	
	public User updateEmailVerification(String uuId);
	
	public User updateMobile(String id, long mobile);
	
	

}
