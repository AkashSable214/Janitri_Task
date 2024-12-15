package com.patientInfo.service;

import java.util.List;

import com.patientInfo.entity.Patient;

public interface PatientService {
	// Add a new patient
	Patient createPatient(Patient patient);

	// Retrieve all patients
	List<Patient> getAllPatients();

	// Retrieve a patient by ID
	Patient getPatientById(Long id);

	// Update an existing patient
	Patient updatePatient(Long id, Patient patientDetails);

}
