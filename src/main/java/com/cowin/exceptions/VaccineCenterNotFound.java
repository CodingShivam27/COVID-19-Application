package com.cowin.exceptions;

public class VaccineCenterNotFound extends RuntimeException{

	public VaccineCenterNotFound() {
		
	}
	
	public VaccineCenterNotFound(String msg) {
		super (msg);
	}
}
