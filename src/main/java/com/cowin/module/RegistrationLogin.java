package com.cowin.module;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationLogin {
	
	@Id
	private Long mobileNo;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Member> members;
}
