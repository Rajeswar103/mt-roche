package com.mouritech.mt_interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FresherPersonalDetailsDto {
	
	
	private Integer id;
	private String motherName;
	private String fatherName;
	private String address;

}
