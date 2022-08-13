package com.cowin.module;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer memberId;
	
	@NotNull(message = "Name field should not be empty..")
	@Size(min=3,max=255, message = "Your name should contain minimum 3 letters and max 255.")
	@Pattern(regexp="^[A-Z][a-z]*", message = "Iavalid name - name should not contain special characters.")
	private String name;
	
	@Pattern(regexp = "^(?:m|M|male|Male|f|F|female|Female)$",message = "Invalid Input")
	private String gender;
	
	@NotNull(message = "date field should not be empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate yeardob;
	
	
}
