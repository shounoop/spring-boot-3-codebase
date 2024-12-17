package com.codebase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageServiceImpl {
    @Autowired
    private MessageSource messageSource;

    // Fetch messages that are appropriate for the user's locale
    public String getLocalizedMessage(String messageId, Locale locale) {
        return messageSource.getMessage(messageId, null, locale);
    }
}
