package org.dnyanyog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.entity.Appointment;
import org.dnyanyog.exception.ResourceNotFoundException;
import org.dnyanyog.repositories.AppointmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service 
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentRepo appointmentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AppointmentRequest addAppointment(AppointmentRequest appointmentRequest) {
		Appointment appointment = this.requestToAppointment(appointmentRequest);
		Appointment saveAppointment = this.appointmentRepo.save(appointment);
		return this.appointmentToRequest(saveAppointment);

	}

	@Override
	public AppointmentRequest updateAppointment(AppointmentRequest appointmentRequest, Integer patientId) {
		Appointment appointment = appointmentRepo.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
		appointment.setName(appointmentRequest.getName());
		appointment.setAppointmentId(appointmentRequest.getAppointmentId());
		appointment.setAppointmentTime(appointmentRequest.getAppointmentTime());
		appointment.setExaminationDate(appointmentRequest.getExaminationDate());

		Appointment updateAppointment = this.appointmentRepo.save(appointment);
		AppointmentRequest appointmentrequest = this.appointmentToRequest(updateAppointment);
		return appointmentrequest;

	}

	@Override
	public void deleteAppointment(Integer patientId) {
		Appointment appointment = this.appointmentRepo.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId));
		this.appointmentRepo.delete(appointment);

	}

	@Override
	public List<AppointmentRequest> getAllAppointment() {
		List<Appointment> appointments = this.appointmentRepo.findAll();
		List<AppointmentRequest> appointmentRequest = appointments.stream()
				.map(appointment -> this.appointmentToRequest(appointment)).collect(Collectors.toList());

		return appointmentRequest;
	}  
	@Override    
	public AppointmentRequest getAppointmentById(Integer patientId) {
		Appointment appointment = this.appointmentRepo.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId));

		return this.appointmentToRequest(appointment);
	}
	

	 

	public Appointment requestToAppointment(AppointmentRequest Request) {

		Appointment appointment = this.modelMapper.map(Request, Appointment.class);
		return appointment;
	}

	public AppointmentRequest appointmentToRequest(Appointment appointment) {
		AppointmentRequest request = this.modelMapper.map(appointment, AppointmentRequest.class);
		return request;
	}


}
