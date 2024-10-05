package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.AppointmentRequest;

public  interface AppointmentService {

	AppointmentRequest addAppointment(AppointmentRequest appointmentRequest);

	AppointmentRequest updateAppointment(AppointmentRequest appointmentRequest, Integer patientId);

	void deleteAppointment(Integer patientId);

	List<AppointmentRequest> getAllAppointment();


	AppointmentRequest getAppointmentById(Integer patientId);



}
