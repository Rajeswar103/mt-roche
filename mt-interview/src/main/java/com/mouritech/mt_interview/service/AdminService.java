package com.mouritech.mt_interview.service;

import com.mouritech.mt_interview.dto.AdminDto;
import com.mouritech.mt_interview.exceptions.UserNameExists;

public interface AdminService {
	
	public Object saveAdminDetails(AdminDto adminDto) throws UserNameExists;

}
