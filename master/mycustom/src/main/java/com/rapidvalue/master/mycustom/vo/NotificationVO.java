package com.rapidvalue.master.mycustom.vo;

import java.util.List;

public class NotificationVO {

	private String message;
	private List<String> users;
	private String state;

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(final List<String> users) {
		this.users = users;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}