package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.NewUserDetail;
import com.example.demo.model.DAO.BingXUserDAO;
import com.example.demo.repository.UserRepository;

@Service("newuserDetailService")
public class NewUserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
		
		BingXUserDAO user=findUser(s);
		System.out.println(user);
		if(user==null) {
			throw new UsernameNotFoundException("用戶不存在");
		}
		
		user.setApiKey(new BCryptPasswordEncoder().encode(user.getApiKey()));

		return new NewUserDetail(user);
	}
	
	//user的帳密
	public BingXUserDAO findUser(String s) {
		return userRepository.findByUserid(s);
	}
//	
//	public void updateUser(String s,String s2) {
//		usersRepository.updateByusername(s,s2);
//	}
//	
//	//user的個人資料
//	public UserInfo findUserInfo(String s) {
//		return userInfoRepository.findByusername(s);
//	}
}
