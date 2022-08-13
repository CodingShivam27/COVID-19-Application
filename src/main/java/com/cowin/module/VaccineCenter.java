package com.cowin.module;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer center_id;
	
	@NotNull(message = "Address should not be empty")
	@Embedded
	private Address center_address;
	
	@NotNull(message = "date field should not be empty")
	private LocalDate date;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "VaccineCenter")
	private List<Vaccine> vaccine;
	
	
	
}