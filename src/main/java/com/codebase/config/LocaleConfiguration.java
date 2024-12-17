package com.codebase.config;

import com.codebase.constant.LocalizationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Slf4j
@Configuration
public class LocaleConfiguration {

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        // Determine the locale of each request that comes into your application

        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        // localeResolver.setDefaultLocale(Locale.US); // Default to US English
        localeResolver.setDefaultLocale(new Locale(LocalizationConstants.VI_LANG));

        return localeResolver;
    }
}