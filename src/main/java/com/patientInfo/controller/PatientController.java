package com.patientInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.patientInfo.dto.PatientDTO;
import com.patientInfo.entity.Patient;
import com.patientInfo.exception.PatientNotFoundException;
import com.patientInfo.service.PatientServiceIMPL;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientServiceIMPL patientService;

    // Create Patient
    @PostMapping
    public ResponseEntity<?> createPatient(@Valid @RequestBody Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMessages);
        }

        Patient createdPatient = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    // Get Patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            throw new PatientNotFoundException("Patient not found with id " + id);
        }
        return ResponseEntity.ok(new PatientDTO(patient));
    }

    // Get All Patients
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<PatientDTO> patientDTOs = patients.stream()
                .map(PatientDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patientDTOs);
    }

    // Update Patient
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, 
                                           @Valid @RequestBody Patient patient, 
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMessages);
        }

        Patient updatedPatient = patientService.updatePatient(id, patient);
        if (updatedPatient == null) {
            throw new PatientNotFoundException("Patient not found with id " + id);
        }
        return ResponseEntity.ok(updatedPatient);
    }

    
    
   
}
