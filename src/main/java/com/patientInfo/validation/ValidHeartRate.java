package com.patientInfo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HeartRateValidator.class)
public @interface ValidHeartRate {
	
    String message() default "Invalid heart rate and heart rate must be between 40bpm to 100 bpm";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

