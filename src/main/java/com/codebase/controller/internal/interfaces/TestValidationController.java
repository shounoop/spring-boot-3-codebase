package com.codebase.controller.internal.interfaces;

import com.codebase.model.request.TestValidationRequest;
import com.codebase.model.response.ApiResponse;
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
