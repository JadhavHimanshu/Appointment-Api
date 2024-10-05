package org.dnyanyog.repositories;

import org.dnyanyog.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AppointmentRepo  extends JpaRepository<Appointment ,Integer> {

	 Appointment save(Appointment appointment)  ; 

	}


