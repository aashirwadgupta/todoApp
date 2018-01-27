package com.todoproject.sample.model;

public class TaskModel {
	private int toDoId; 
	private boolean edit;
	private boolean isCompleted;
	private String toDoText;
	private long toDoTime;
	
	public String getToDoText() {
		return toDoText;
	}
	public void setToDoText(String toDoText) {
		this.toDoText = toDoText;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public long getToDoTime() {
		return toDoTime;
	}
	public void setToDoTime(long toDoTime) {
		this.toDoTime = toDoTime;
	}
	public int getToDoId() {
		return toDoId;
	}
	public void setToDoId(int toDoId) {
		this.toDoId = toDoId;
	}
	
}
