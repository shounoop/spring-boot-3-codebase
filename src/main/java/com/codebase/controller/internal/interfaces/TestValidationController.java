package com.codebase.controller.internal.interfaces;

import com.codebase.model.request.TestValidationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1")
public interface TestValidationController {
    @PostMapping("/test-validation")
    public List<String> Testing(@Valid @RequestBody TestValidationRequest request);
}
