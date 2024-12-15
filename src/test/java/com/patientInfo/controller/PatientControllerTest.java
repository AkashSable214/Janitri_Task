
package com.patientInfo.controller;

import com.patientInfo.dto.PatientDTO;
import com.patientInfo.entity.Patient;
import com.patientInfo.entity.User;
import com.patientInfo.exception.PatientNotFoundException;
import com.patientInfo.service.PatientServiceIMPL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SuppressWarnings("removal")
@SpringBootTest
class PatientControllerTest {

	@InjectMocks
	private PatientController patientController;

	@MockBean
	private PatientServiceIMPL patientService;

	@Mock
	private BindingResult bindingResult;

	private Patient patient;
	private PatientDTO patientDTO;
	private User user;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Initialize a Patient instance
		patient = new Patient();
		patient.setId(1L);
		patient.setName("John Doe");
		patient.setAge(30.0);
		patient.setHeartRate(70);

		// Initialize a User instance and associate it with the Patient
		user = new User();
		user.setId(1L); // Properly set a user ID
		patient.setUser(user);

		// Create a PatientDTO for testing
		patientDTO = new PatientDTO(patient);
	}

	@Test
    void testCreatePatient_Success() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);

        ResponseEntity<?> responseEntity = patientController.createPatient(patient, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(patient, responseEntity.getBody());
        verify(patientService, times(1)).createPatient(patient);
    }

	@Test
    void testCreatePatient_WithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = patientController.createPatient(patient, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        verify(patientService, never()).createPatient(any(Patient.class));
    }

	@Test
    void testGetPatient_Success() {
        when(patientService.getPatientById(1L)).thenReturn(patient);

        ResponseEntity<PatientDTO> responseEntity = patientController.getPatient(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(patientDTO, responseEntity.getBody());
        verify(patientService, times(1)).getPatientById(1L);
    }

	@Test
    void testGetPatient_NotFound() {
        when(patientService.getPatientById(1L)).thenThrow(new PatientNotFoundException("Patient not found with id 1"));

        assertThrows(PatientNotFoundException.class, () -> patientController.getPatient(1L));
    }

	@Test
    void testGetAllPatients() {
        when(patientService.getAllPatients()).thenReturn(Collections.singletonList(patient));

        ResponseEntity<List<PatientDTO>> responseEntity = patientController.getAllPatients();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isEmpty());
        assertEquals(1, responseEntity.getBody().size());
        verify(patientService, times(1)).getAllPatients();
    }

	@Test
    void testUpdatePatient_Success() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(patientService.updatePatient(any(Long.class), any(Patient.class))).thenReturn(patient);

        ResponseEntity<?> responseEntity = patientController.updatePatient(1L, patient, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(patient, responseEntity.getBody());
        verify(patientService, times(1)).updatePatient(1L, patient);
    }

}
