package com.codebase.service.interfaces;

import java.util.Locale;

public interface MessageService {

    public String getLocalizedMessage(String messageId, Locale locale);
}
