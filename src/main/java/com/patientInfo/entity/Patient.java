package com.patientInfo.entity;

import java.time.LocalDate;

import com.patientInfo.validation.ValidHeartRate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "{patient.name.notnull}")
	private String name;

	@Min(value = 0, message = "{patient.age.min}")
	private Double age;

	@ValidHeartRate
	private int heartRate;

	private LocalDate registrationDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// Getters and Setters
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Patient{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", heartRate=" + heartRate
				+ ", registrationDate=" + registrationDate + ", user=" + (user != null ? user.getId() : "null") + '}';
	}
}
