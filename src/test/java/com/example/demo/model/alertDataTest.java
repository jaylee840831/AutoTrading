package com.example.demo.model;

import lombok.Data;

public class alertDataTest {
	private String username;
	private String indicator;
	private String message;
	public alertDataTest() {
		super();
		this.username = "jasper";
		this.indicator = "KD 隨機指標";
		this.message = "xxx less than 50 買入";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "alertDataTest [username=" + username + ", indicator=" + indicator + ", message=" + message + "]";
	}
	
	
}
