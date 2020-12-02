package com.active.lk.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.model.User;
import com.active.lk.service.FirebaseService;
import com.active.lk.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

@Service
public class FirebaseServiceImpl implements FirebaseService{
	
	@Autowired
	UserService userServ;

	@Override
	public User createAdminUser(User user) {
		CreateRequest request = new CreateRequest()
			    .setEmail(user.getEmail())
			    .setEmailVerified(false)
			    .setPassword("secretPassword")
			   // .setPhoneNumber(String.valueOf(user.getMobile()))
			    .setDisplayName(user.getName())
			    .setPhotoUrl("http://www.example.com/12345678/photo.png")
			    .setDisabled(false);

			UserRecord userRecord;
			try {
				userRecord = FirebaseAuth.getInstance().createUser(request);
				if(userRecord!=null) {
					System.out.println("Successfully created new user: " + userRecord.getUid());
					user.setUuid(userRecord.getUid());
					userServ.createUser(user);
				}
				
			} catch (FirebaseAuthException e) {
			
				e.printStackTrace();
			}
		
		return null;
	}

	@Override
	public User getUserByMail(String mailId) {
		UserRecord userRecord;
		User user = null;
		try {
			userRecord = FirebaseAuth.getInstance().getUserByEmail(mailId);
			if(userRecord!=null) {
				System.out.println("Successfully fetched user data: " + userRecord.getEmail());
				user = userServ.getUserByuuId(userRecord.getUid());
			}
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}
	

	
	
	

}
