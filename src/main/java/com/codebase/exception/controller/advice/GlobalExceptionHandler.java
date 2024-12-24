package com.codebase.exception.controller.advice;

import com.codebase.component.response.ApiResponseFactory;
import com.codebase.exception.model.AppException;
import com.codebase.model.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ApiResponseFactory apiResponseFactory;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
        return apiResponseFactory.failWithBadInputParameter(ex);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> handleApprovalException(AppException ex) {
        return apiResponseFactory.failWithDomainException(ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return apiResponseFactory.failWithInternalException(ex);
    }
}