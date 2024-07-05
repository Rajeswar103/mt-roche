package com.mouritech.mt_interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {
	
	
	private String username;
	private String password;

}
