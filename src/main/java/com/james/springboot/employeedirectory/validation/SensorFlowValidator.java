package com.james.springboot.employeedirectory.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SensorFlowValidator implements ConstraintValidator<SensorFlow, Integer> {

    @Override
    public void initialize(SensorFlow constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        System.out.println(">> " + context.toString());
        if (value == 3 || value == 15 || value == 50) {
            return true;
        }

        return false;
    }
}
