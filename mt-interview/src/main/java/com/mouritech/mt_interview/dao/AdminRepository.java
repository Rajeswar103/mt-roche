package com.mouritech.mt_interview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.mt_interview.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{
	
	Admin findByusername(String username);

}
