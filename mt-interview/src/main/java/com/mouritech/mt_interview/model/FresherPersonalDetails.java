package com.mouritech.mt_interview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Fresher_personal_details")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FresherPersonalDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "motherName should not be empty")
	private String motherName;
	@NotNull(message = "fatherName should not be empty")
	private String fatherName;
	@NotEmpty(message = "address should not be empty")
	private String address;
	

}
