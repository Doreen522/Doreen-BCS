package com.active.lk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.User;
import com.active.lk.service.UserService;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService userServ;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		return userServ.createUser(user);
	}
	
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<User> getAllUsers() {
	    return userServ.getAllUsers();
	 }
	 
	 @RequestMapping(value="/userId/{userId}",method= RequestMethod.GET)
	 public User getUserById(@PathVariable String userId) {
		 return userServ.getUser(userId);
	 }
	 
	@RequestMapping(value="/addtionalInfo/userId/{userId}",method = RequestMethod.PUT)
	public User addMoreUserInfo(@PathVariable String userId, @RequestBody User user) {
		user.setId(userId);
		return userServ.addMoreUserInfo(user);
	}
	
	@RequestMapping(value="/uuId/{uuId}",method= RequestMethod.GET)
	 public User getUserByuuId(@PathVariable String uuId) {
		 return userServ.getUserByuuId(uuId);
	 }
	
	@RequestMapping(value="/emailVerified/uuId/{uuId}",method = RequestMethod.PUT)
	public User updateEmailVerification(@PathVariable String uuId) {
		return userServ.updateEmailVerification(uuId);
	}
	
	@RequestMapping(value="/mobile/userId/{userId}/{mobile}",method = RequestMethod.PUT)
	public User updateMobile(@PathVariable String userId, @PathVariable long mobile) {
		//user.setId(userId);
		return userServ.updateMobile(userId,mobile);
	}
	
	@RequestMapping(value="/exist/uuId/{uuId}",method= RequestMethod.GET)
	 public boolean isExistUserByuuId(@PathVariable String uuId) {
		 return userServ.isExistByUUID(uuId);
	 }
	
	
	
	
}
