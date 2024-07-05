package com.mouritech.mt_interview.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mouritech.mt_interview.dao.AdminRepository;
import com.mouritech.mt_interview.dto.AdminDto;
import com.mouritech.mt_interview.exceptions.UserNameExists;
import com.mouritech.mt_interview.model.Admin;
import com.mouritech.mt_interview.service.AdminService;

@Service
public class AdminserviceImpl implements AdminService{

	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public Object saveAdminDetails(AdminDto adminDto) throws UserNameExists {
		
		
		List<Admin> list = adminRepository.findAll();
		Admin admin = mapToEntity(adminDto);
		boolean usernameExists = list.stream().anyMatch(s -> s.getUsername().equals(adminDto.getUsername()));
		if(usernameExists)
		{
			throw new UserNameExists();
		}
		admin.setRole("ADMIN");
		String s=admin.getPassword();
		admin.setPassword(passwordEncoder.encode(s));
		String password="Admin$%@!";
		if(admin.getAccessPassword().equals(password))
		{
			adminRepository.save(admin);
			return "registered succesfull............";
		}
		return "your are not eligibele for these registration";
		
	}
	
	private Admin mapToEntity(AdminDto adminDto)
	{
		return modelMapper.map(adminDto,Admin.class);
		
	}

}
