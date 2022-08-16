package com.cowin.service;

import java.util.List;

import com.cowin.model.AppoinmetDTO;
import com.cowin.model.Appointment;


public interface AppointmentService {
	
	public AppoinmetDTO addAppointMentDetails(AppoinmetDTO appDto);
	
	public String deleteBookedTrip(AppoinmetDTO appDto);
	
	public List<Appointment> getAllAppointmentsOfMember(AppoinmetDTO appDto);
	
}
