package com.patientInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientInfo.entity.Patient;
import com.patientInfo.entity.User;
import com.patientInfo.repo.PatientRepository;
import com.patientInfo.repo.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public  class PatientServiceIMPL implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;
    
    

    public Patient createPatient(Patient patient) {
        User user = userRepository.findById(patient.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        patient.setUser(user);
        patient.setRegistrationDate(LocalDate.now());
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient == null) {
            return null;
        }
        existingPatient.setHeartRate(patientDetails.getHeartRate());
        existingPatient.setName(patientDetails.getName());
        existingPatient.setAge(patientDetails.getAge());
        return patientRepository.save(existingPatient);
    }

	
 
    
    
    

    

	

	
}
