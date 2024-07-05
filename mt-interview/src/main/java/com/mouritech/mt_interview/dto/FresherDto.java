package com.mouritech.mt_interview.dto;

import com.mouritech.mt_interview.model.FresherPersonalDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FresherDto {
	
	
	private Integer id;
	private String name;
	private String email;
	private Integer graduationPercentage;
	private FresherPersonalDetailsDto fresherPersonalDetailsDto;

}
