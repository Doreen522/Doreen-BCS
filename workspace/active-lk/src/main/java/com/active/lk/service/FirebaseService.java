package com.active.lk.service;

import com.active.lk.model.User;

public interface FirebaseService {
	
	public User createAdminUser(User user);
	
	public User getUserByMail(String mailId);

}
