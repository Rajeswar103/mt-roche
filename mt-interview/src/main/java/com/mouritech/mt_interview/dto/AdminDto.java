package com.mouritech.mt_interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
	
	
	private Integer id;
	private String username;
	private String password;
	private String accessPassword;
	private String role;

}
