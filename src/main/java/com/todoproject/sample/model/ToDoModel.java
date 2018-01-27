package com.todoproject.sample.model;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
public class ToDoModel {
	private String id;
	private List<TaskModel> taskList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<TaskModel> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<TaskModel> taskList) {
		this.taskList = taskList;
	}
		
}
