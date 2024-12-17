package com.codebase.controller.internal.interfaces;

import com.codebase.model.request.TestValidationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TestValidationController {
    @PostMapping("/api/v1/test-validation")
    public List<String> Testing(@Valid @RequestBody TestValidationRequest request);
}
