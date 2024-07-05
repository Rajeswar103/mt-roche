package com.mouritech.mt_interview.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mouritech.mt_interview.dao.AdminRepository;
import com.mouritech.mt_interview.model.Admin;


@Service
public class CustomAdminService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepoastory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin user = adminRepoastory.findByusername(username);
		AdminInfo adminInfo=null;
		if(user==null)
		{
			throw new UsernameNotFoundException(username+"user is not found");
		}
		adminInfo=new AdminInfo();
		adminInfo.setAdmin(user);
		return adminInfo;
	}

}
