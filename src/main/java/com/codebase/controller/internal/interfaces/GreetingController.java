package com.codebase.controller.internal.interfaces;

import com.codebase.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@RequestMapping("/api/v1")
public interface GreetingController {
    @GetMapping("/greeting")
    ResponseEntity<ApiResponse> getGreeting(Locale locale);
}
