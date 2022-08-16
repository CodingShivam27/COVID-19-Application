package com.cowin.service;

import java.util.List;

import com.cowin.module.AppoinmetDTO;
import com.cowin.module.Appointment;


public interface AppointmentService {
	
	public AppoinmetDTO addAppointMentDetails(AppoinmetDTO appDto);
	
	public String deleteBookedTrip(AppoinmetDTO appDto);
	
	public List<Appointment> getAllAppointmentsOfMember(AppoinmetDTO appDto);
	
}
