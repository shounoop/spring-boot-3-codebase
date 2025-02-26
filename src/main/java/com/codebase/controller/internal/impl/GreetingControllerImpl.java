package com.codebase.controller.internal.impl;

import com.codebase.component.response.ApiResponseFactory;
import com.codebase.controller.internal.GreetingController;
import com.codebase.model.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Tag(name = "Greeting API", description = "API for Greeting")
public class GreetingControllerImpl implements GreetingController {

    private final ApiResponseFactory apiResponseFactory;

    private final MessageSource messageSource;

    @Override
    @Operation(summary = "getGreeting", description = "getGreeting description")
    public ResponseEntity<ApiResponse> getGreeting(Locale locale) {
        return apiResponseFactory.success(messageSource.getMessage("greeting", null, locale));
    }
}