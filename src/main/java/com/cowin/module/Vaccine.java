package com.cowin.module;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

	@Pattern(regexp="^[A-Z][a-z][0-9]*", message = "Invalid Vaccine Name")
	private String vaccineName;
	
	@Pattern(regexp = "^[0-9]", message="Invalid Vaccine Price")
	private double price;
	
	@Pattern(regexp = "^[0-9]", message="Invalid Vaccine Count")
	private int vaccineCount;
	
	@OneToMany
	private VaccineCenter vaccinecenter;
	
}
