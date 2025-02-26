package com.codebase.controller.internal;

import com.codebase.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@RequestMapping("/api/v1/greeting")
public interface GreetingController {
    @GetMapping("/")
    ResponseEntity<ApiResponse> getGreeting(Locale locale);
}
