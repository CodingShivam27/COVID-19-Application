package com.cowin.module;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long BookingId;
	
	@NotEmpty(message = "Date field should not be empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate DateOfBooking;
	
	@ManyToOne
	private VaccineCenter vaccinecenter;
	
	@OneToOne
	private Member member; 
	
}
