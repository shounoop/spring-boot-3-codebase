package com.codebase.service.interfaces;

import java.util.Locale;

public interface MessageService {

    String getLocalizedMessage(String messageId, Locale locale);
}
