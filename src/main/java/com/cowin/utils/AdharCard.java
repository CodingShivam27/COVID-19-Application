package com.cowin.utils;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AdharCard {
	
	@Id
	@Column(unique = true)
	@NotNull(message = "Missing or empty value - Adhar Number.")
//	@Pattern(regexp = "^[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4}$", message = "Invalid Aadhaar Number.")
	private String adharNo;
	
	@NotNull(message = "Name field should not be empty..")
	@Size(min=3,max=255, message = "Your name should contain minimum 3 letters and max 255.")
//	@Pattern(regexp="/^[A-Za-z]+$/", message = "Iavalid name - name should not contain special characters.")
	private String name;
	
	@NotNull(message = "date field should not be empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy")
	private Date dob;
	
	@NotNull(message = "gender field should not be empty")
	private String gender;
	
	@NotNull(message = "Address field should not be empty")
//	@Pattern(regexp="/^[A-Z][a-z]+$/", message = "Iavalid addressline name - should not contain special characters.")
	private String addressline;
	
	@NotNull(message = "city field should not be empty")
//	@Pattern(regexp="/^[A-Za-z]+$/", message = "Iavalid city name - should not contain special characters.")
	private String city;
	
	@NotNull(message = "state field should not be empty")
//	@Pattern(regexp="/^[A-Za-z]+$/", message = "Iavalid state name - should not contain special characters.")
	private String state;
	
	@NotNull(message = "pincode field should not be empty")
//	@Pattern(regexp="^[1-9][0-9]{5}$", message = "Iavalid pincode..")
	private String pincode;
}
