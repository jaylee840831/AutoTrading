package com.example.demo.model;

public class BingX {
//	private String username;
	private String apiKey;
	private String secretKey;
	
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
//	@Override
//	public String toString() {
//		return "UserInfo [username=" + username + ", apiKey=" + apiKey + ", secretKey=" + secretKey + "]";
//	}
	@Override
	public String toString() {
		return "UserInfo [apiKey=" + apiKey + ", secretKey=" + secretKey + "]";
	}
	
	
	
}
