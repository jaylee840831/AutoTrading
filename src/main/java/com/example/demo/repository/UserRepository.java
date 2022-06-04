package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DAO.BingXUserDAO;



@Repository
public interface UserRepository extends JpaRepository<BingXUserDAO,String>{

	public BingXUserDAO findByUserid(String userid);
	
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE trading_user SET username= :name2 WHERE username= :name")
//	void updateByusername(@Param("name")String name,@Param("name2")String name2);
}
