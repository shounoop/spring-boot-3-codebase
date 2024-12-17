package com.codebase.controller.internal.impl;

import com.codebase.component.response.ApiRespFactory;
import com.codebase.controller.internal.interfaces.GreetingController;
import com.codebase.model.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class GreetingControllerImpl implements GreetingController {

    private final ApiRespFactory apiRespFactory;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseEntity<ApiResponse> getGreeting(Locale locale) {
        return apiRespFactory.success(messageSource.getMessage("greeting", null, locale));
    }
}