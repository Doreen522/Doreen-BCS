package com.active.lk.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.common.UserTypeEnum;
import com.active.lk.model.User;
import com.active.lk.repo.UserRepository;
import com.active.lk.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public User createUser(User user) {
		user.setCreateDateTime(new DateTime().toDateTimeISO().toDate());
		user.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user=userRepo.findById(userId).get();
		return user;
	}

	@Override
	public User addMoreUserInfo(User user) {
		User updateUser=userRepo.findById(user.getId()).get();
		updateUser.setGender(user.getGender());
		updateUser.setMobile(user.getMobile());
		updateUser.setStatus(user.getStatus());
		updateUser.setDob(user.getDob());
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		int birthdate = Integer.parseInt(formatter.format(user.getDob()));
		Date now = new Date();
		int currentDate = Integer.parseInt(formatter.format(now));                          
	    int age = (currentDate - birthdate) / 10000;
	    if(age>18)
	    	updateUser.setMemberType(UserTypeEnum.USER_TYPE_ADLT.getCode());
	    else
	    	updateUser.setMemberType(UserTypeEnum.USER_TYPE_CHLD.getCode());
		updateUser.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		return userRepo.save(updateUser);
	}
	
	@Override
	public User getUserByuuId(String uuId) {
		User user= userRepo.findByUuid(uuId);
		return user;
	}

	
	@Override
	public User updateEmailVerification(String uuId) {
		User updateUser=userRepo.findByUuid(uuId);
		updateUser.setEmailVerified(true);
		updateUser.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		return userRepo.save(updateUser);
	}

	@Override
	public User updateMobile(String id, long mobile) {
		User updateUser=userRepo.findById(id).get();
		updateUser.setMobile(mobile);
		updateUser.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		return userRepo.save(updateUser);
	}

	@Override
	public boolean isExistByUUID(String uuid) {
		if(getUserByuuId(uuid)==null)
				return false;
		else
			return true;
	}

	
	
	
	
}
