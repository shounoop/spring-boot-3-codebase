package com.codebase.controller.internal.impl;

import com.codebase.controller.internal.interfaces.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class GreetingControllerImpl implements GreetingController {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getGreeting(Locale locale) {
        return messageSource.getMessage("greeting", null, locale);
    }
}