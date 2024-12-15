package com.patientInfo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HeartRateValidator implements ConstraintValidator<ValidHeartRate, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // Allow heart rate between 40 and 100
        return value != null && value >= 40 && value <= 100;
    }

	
}
