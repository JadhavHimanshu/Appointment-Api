package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentservice;

	@PostMapping("/addAppointment")
	public ResponseEntity<AppointmentResponse> addAppointment(@RequestBody AppointmentRequest appointmentRequest) {
		this.appointmentservice.addAppointment(appointmentRequest);
		return new ResponseEntity<>(new AppointmentResponse("Appointment Added Successfully", "0000"),
				HttpStatus.CREATED);

	}

	@PostMapping("/addAppointment/{patientId}")
	public ResponseEntity<AppointmentResponse> updateAppointment(@RequestBody AppointmentRequest appointmentRequest,
			@PathVariable Integer patientId) {
		this.appointmentservice.updateAppointment(appointmentRequest, patientId);
		return new ResponseEntity<>(new AppointmentResponse("Appointment Updated SucessFully", "0000"), HttpStatus.OK);
	}

	@DeleteMapping("/addAppointment/{patientId}")
	public ResponseEntity<AppointmentResponse> deleteAppointment(@PathVariable Integer patientId) {
		this.appointmentservice.deleteAppointment(patientId);
		return new ResponseEntity<>(new AppointmentResponse("Appointment Deleted sucessfully", "0000"), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<AppointmentRequest>> getAllAppointment() {
		return ResponseEntity.ok(this.appointmentservice.getAllAppointment());
	}

	@GetMapping("/addAppointment/{patientId}")
	public ResponseEntity<AppointmentRequest> getAppointmentById(@PathVariable int patientId) {
		return ResponseEntity.ok(this.appointmentservice.getAppointmentById(patientId));
	} 
	 

}
