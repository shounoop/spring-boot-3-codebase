package com.codebase.config;

import com.codebase.constant.LocalizationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfiguration {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        // Retrieve locale-specific messages from property files

        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames(LocalizationConstants.MESSAGE_SOURCE_BASE_NAMES); // base name of the resource bundle
        source.setDefaultEncoding(LocalizationConstants.DEFAULT_ENCODING);
        source.setUseCodeAsDefaultMessage(true);
        source.setCacheSeconds(3600); // Refresh cache once every hour
        return source;
    }
}
