package com.cowin.module;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineId;

	@Pattern(regexp="^[A-Z][a-z][0-9]*", message = "Invalid Vaccine Name")
	private String vaccineName;
	
	@Pattern(regexp = "^[0-9]", message="Invalid Vaccine Price")
	private double price;
	
	@Pattern(regexp = "^[0-9]", message="Invalid Vaccine Count")
	private int vaccineCount;
	
	
	@ManyToOne
	private VaccineCenter vaccinecenter;
	
}
