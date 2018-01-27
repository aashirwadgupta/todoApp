package com.todoproject.sample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todoproject.sample.model.ToDoModel;
import com.todoproject.sample.services.ToDoServices;

@RestController
public class ToDoDataRestController {

	@Autowired
	private ToDoServices toDoService;
	
	@RequestMapping(value="/api/getToDo", method=RequestMethod.GET)
	public ResponseEntity<ToDoModel> getToDoForUser(@RequestParam("id")String toDoId){
		System.out.println(toDoId);
		return toDoService.getToDoForUser(toDoId);
	}
	
	@RequestMapping(value="/api/createToDo", method=RequestMethod.POST)
	public ResponseEntity<ToDoModel> createToDo(@RequestParam("id")String userId, @RequestBody ToDoModel toDoModel){
		return toDoService.createToDoForUser(userId, toDoModel);
	}
	
	@RequestMapping(value="/api/updateToDo", method=RequestMethod.POST)
	public ResponseEntity<ToDoModel> updateToDo(@RequestBody ToDoModel toDoModel){
		return toDoService.updateToDo(toDoModel);
	}
}
