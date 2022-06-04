package com.example.demo.model.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import lombok.Data;

//面向資料庫的BingX使用者帳密資訊

//@Data
@Entity
@Table(name="trading_user")
public class BingXUserDAO {
	@Id
	private String userid;
	@Column(name="api_key")
	private String apiKey;
	@Column(name="secret_key")
	private String secretKey;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
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
	
	
}