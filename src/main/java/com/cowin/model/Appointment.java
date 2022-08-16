package com.cowin.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long BookingId;

//	@NotEmpty(message = "Date field should not be empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy")
	private Date DateOfBooking;

	private Boolean status ;
	
	private String vaccineName;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "center_id")
//	@JsonIgnore
//	private VaccineCenter vaccinecenter;

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "memberId")
//	private Member member;

}
