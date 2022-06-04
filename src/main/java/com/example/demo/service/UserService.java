package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DAO.BingXUserDAO;
import com.example.demo.repository.UserRepository;

/*
 * 網站用戶資訊service
 * */

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	//查詢網站用戶資訊
	public BingXUserDAO getUserInfo(String userid) {
		return userRepository.findByUserid(userid);
	}
}
