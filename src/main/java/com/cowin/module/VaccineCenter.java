package com.cowin.module;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
//	@NotNull(message = "date field should not be empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate date;
	
//	@NotNull(message = "Address field should not be empty")
	private Address center_address;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_center_id", referencedColumnName = "center_id")
	private List<Vaccine> vaccine;
	
}
