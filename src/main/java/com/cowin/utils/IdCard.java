package com.cowin.utils;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IdCard {
	
	@NotNull(message = "Name field should not be empty..")
	@Size(min=3,max=255, message = "Your name should contain minimum 3 letters and max 255.")
	@Pattern(regexp="^[A-Z][a-z]*", message = "Iavalid name - name should not contain special characters.")
	private String name;
	
	@NotNull(message = "date field should not be empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate dob;
	
	@NotNull(message = "gender field should not be empty")
	private String gender;
	
	@NotBlank(message = "Address field should not be empty")
	@Pattern(regexp="^[A-Z][a-z][0-9][:-/,]*", message = "]")
	private String addressline;
	
	@NotBlank(message = "city field should not be empty")
	@Pattern(regexp="^[A-Z][a-z]*", message = "Iavalid city name - should not contain special characters.")
	private String city;
	
	@NotBlank(message = "state field should not be empty")
	@Pattern(regexp="^[A-Z][a-z]*", message = "Iavalid city name - should not contain special characters.")
	private String state;
	
	@NotBlank(message = "pincode field should not be empty")
	@Pattern(regexp="^[1-9][0-9]{5}$", message = "Iavalid pincode..")
	private String pincode;
	
}
