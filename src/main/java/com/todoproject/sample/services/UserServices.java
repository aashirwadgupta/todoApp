package com.todoproject.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.todoproject.sample.dao.UserRepository;
import com.todoproject.sample.model.UserModel;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepo;
	
	public ResponseEntity<UserModel> doLogin(String userId, String secretCode) {
		if(userId!=null){
			UserModel userModel = userRepo.findOne(userId);
			if(secretCode.equals(userModel.getSecretCode())){
				return new ResponseEntity<UserModel>(userModel, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<UserModel>(userModel, HttpStatus.EXPECTATION_FAILED);				
			}
		} else {
			return new ResponseEntity<UserModel>(new UserModel(), HttpStatus.BAD_REQUEST);
		}
	}

	public List<UserModel> findAllUsers() {
		return userRepo.findAll();
	}

	public UserModel getAUser(String userId) {
		UserModel userModel = userRepo.findOne(userId);
		return userModel;
	}

	public ResponseEntity<UserModel> updateUserProfile(UserModel userProfile) {
		UserModel userModel = userRepo.findOne(userProfile.getId());
		try{
			userRepo.save(userModel);
			return new ResponseEntity<UserModel>(userModel, HttpStatus.ACCEPTED);
		} catch(Exception e){
			return new ResponseEntity<UserModel>(userModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public UserModel getUserProfile(String userId) {
		return userRepo.findOne(userId);
	}

	public ResponseEntity<UserModel> createUser(UserModel userModel) {
		UserModel userObj = userRepo.findOne(userModel.getMailId());
		if(null!=userObj){
			return new ResponseEntity<UserModel>(userObj, HttpStatus.EXPECTATION_FAILED);
		} else {
			try{
				userRepo.save(userModel);
				return new ResponseEntity<UserModel>(userModel, HttpStatus.ACCEPTED);
			} catch(Exception e){
				return new ResponseEntity<UserModel>(userModel, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
