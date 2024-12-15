package com.patientInfo.service;

import com.patientInfo.entity.Patient;
import com.patientInfo.entity.User;
import com.patientInfo.repo.PatientRepository;
import com.patientInfo.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  
@AutoConfigureMockMvc
public class PatientServiceIMPLTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PatientServiceIMPL patientService;

    private Patient patient;
    private User user;
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("email@example.com");
        user.setPassword("password");

        patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setAge(30.0);
        patient.setUser(user);
        patient.setRegistrationDate(LocalDate.now());
    }

    @Test
    void testCreatePatient() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient createdPatient = patientService.createPatient(patient);

        assertNotNull(createdPatient);
        assertEquals("John Doe", createdPatient.getName());
        assertEquals(30.0, createdPatient.getAge());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void testGetPatientById() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));

        Patient fetchedPatient = patientService.getPatientById(1L);

        assertNotNull(fetchedPatient);
        assertEquals("John Doe", fetchedPatient.getName());
        assertEquals(30.0, fetchedPatient.getAge());
    }

    @Test
    void testUpdatePatient() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        patient.setName("Updated Name");
        patient.setAge(31.0);

        Patient updatedPatient = patientService.updatePatient(1L, patient);

        assertNotNull(updatedPatient);
        assertEquals("Updated Name", updatedPatient.getName());
        assertEquals(31.0, updatedPatient.getAge());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void testPatientNotFoundForUpdate() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());

        Patient updatedPatient = patientService.updatePatient(1L, patient);

        assertNull(updatedPatient);
    }

    
    
    
    
    
}
