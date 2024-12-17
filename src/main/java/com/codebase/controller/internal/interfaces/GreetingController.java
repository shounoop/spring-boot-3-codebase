package com.codebase.controller.internal.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@RequestMapping("/api/v1")
public interface GreetingController {
    @GetMapping("/greeting")
    public String getGreeting(Locale locale);
}
