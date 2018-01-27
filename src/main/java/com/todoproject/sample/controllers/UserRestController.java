package com.todoproject.sample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todoproject.sample.model.UserModel;
import com.todoproject.sample.services.UserServices;

@RestController
public class UserRestController {

	@Autowired
	private UserServices userService;
	
	@RequestMapping(value="/api/getAllUsers", method=RequestMethod.GET)
	public List<UserModel> getAllUsers(){
		return userService.findAllUsers();
	}
	
	@RequestMapping(value="/api/getUser", method=RequestMethod.GET)
	public UserModel getUser(@RequestParam("id") String userId){
		return userService.getAUser(userId);
	}
	
	@RequestMapping(value="/api/userLogin", method=RequestMethod.POST)
	public ResponseEntity<UserModel> logInUser(@RequestBody UserModel userDetails){
		return userService.doLogin(userDetails.getMailId(), userDetails.getSecretCode());
	}
	
	@RequestMapping(value="/api/userProfile", method=RequestMethod.GET)
	public UserModel getUserProfile(@RequestParam("id") String userId){
		return userService.getUserProfile(userId);
	}
	
	@RequestMapping(value="/api/updateUserProfile", method=RequestMethod.POST)
	public ResponseEntity<UserModel> updateUserProfile(@RequestBody UserModel userProfile){
		return userService.updateUserProfile(userProfile);
	}
	
	@RequestMapping(value="/api/createUser", method=RequestMethod.POST)
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel){
		return userService.createUser(userModel);
	}
}
