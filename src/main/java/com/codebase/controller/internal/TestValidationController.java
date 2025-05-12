package com.codebase.controller.internal;

import com.codebase.model.primary.request.TestValidationRequest;
import com.codebase.model.primary.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/test-validation")
public interface TestValidationController {
    @PostMapping("/")
    ResponseEntity<ApiResponse> testing(@Valid @RequestBody TestValidationRequest request);
}
