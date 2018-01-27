package com.todoproject.sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.todoproject.sample.dao.ToDoRepository;
import com.todoproject.sample.dao.UserRepository;
import com.todoproject.sample.model.ToDoModel;
import com.todoproject.sample.model.UserModel;

@Service
public class ToDoServices {

	@Autowired
	private ToDoRepository toDoRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public ResponseEntity<ToDoModel> getToDoForUser(String toDoId) {
		ToDoModel toDoObj = toDoRepo.findOne(toDoId);
		if(toDoObj!=null){
			return new ResponseEntity<ToDoModel>(toDoObj, HttpStatus.ACCEPTED);
		} else {
			toDoObj = new ToDoModel();
			toDoObj.setId(toDoId);
			return new ResponseEntity<ToDoModel>(toDoObj, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<ToDoModel> createToDoForUser(String userId, ToDoModel toDoModel) {
		UserModel user = userRepo.findOne(userId);
		toDoModel.setId(userId);
		try{
			toDoRepo.save(toDoModel);
			return new ResponseEntity<ToDoModel>(toDoModel, HttpStatus.ACCEPTED);
		} catch(Exception e){
			return new ResponseEntity<ToDoModel>(toDoModel, HttpStatus.EXPECTATION_FAILED);			
		}
		
	}

	public ResponseEntity<ToDoModel> updateToDo(ToDoModel toDoModel) {
		//ToDoModel toDo = toDoRepo.findOne(toDoModel.getId());
		try{
			toDoRepo.save(toDoModel);
			return new ResponseEntity<ToDoModel>(toDoModel, HttpStatus.ACCEPTED);
		} catch(Exception e){
			return new ResponseEntity<ToDoModel>(toDoModel, HttpStatus.EXPECTATION_FAILED);			
		}
	}

}
