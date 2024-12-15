package com.patientInfo.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.patientInfo.entity.Patient;

public class PatientDTO {
    private Long id;
    private String name;
    private Double age;
    private int heartRate;
    private LocalDate registrationDate;

    private Long userId;
    private String userName;

    // Constructor
    public PatientDTO(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.age = patient.getAge();
        this.heartRate = patient.getHeartRate();
        this.registrationDate = patient.getRegistrationDate();

        if (patient.getUser() != null) {
            this.userId = patient.getUser().getId();
            this.userName = patient.getUser().getName();
        } else {
            this.userId = null;
            this.userName = null;
        }
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Overridden equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO that = (PatientDTO) o;
        return heartRate == that.heartRate &&
               Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(age, that.age) &&
               Objects.equals(registrationDate, that.registrationDate) &&
               Objects.equals(userId, that.userId) &&
               Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, heartRate, registrationDate, userId, userName);
    }
}
