package com.mouritech.mt_interview.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="freshers_registration_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fresher {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "name should not be empty")
	private String name;
	@Email
	private String email;
	private Integer graduationPercentage;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fresher_id",referencedColumnName = "id")
	private FresherPersonalDetails fresherPersonalDetails;

}
