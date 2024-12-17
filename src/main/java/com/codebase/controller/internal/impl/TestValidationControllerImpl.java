package com.codebase.controller.internal.impl;

import com.codebase.controller.internal.interfaces.TestValidationController;
import com.codebase.model.request.TestValidationRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestValidationControllerImpl implements TestValidationController {
//    private final Validator validator;

    @Override
    public List<String> Testing(TestValidationRequest request) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        // Creating object
        Validator validator = factory.getValidator();

        // Provide an empty name and low experience for demonstration
        request.dummy();

        // It will validate each field
        Set<ConstraintViolation<TestValidationRequest>> violations = validator.validate(request);

        // It will validate if provided data belongs to senior or not
        Set<ConstraintViolation<TestValidationRequest>> violations2 = validator.validate(request, TestValidationRequest.Senior.class);

        // Response List
        List<String> Reslist = new ArrayList<>();

        log.info("violations1");
        // Will run for loop to see if there are any validation error
        for (ConstraintViolation<TestValidationRequest> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            Reslist.add(violation.getMessage());
        }

        log.info("violations2");
        for (ConstraintViolation<TestValidationRequest> violation : violations2) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            Reslist.add(violation.getMessage());
        }

        return Reslist;
    }
}