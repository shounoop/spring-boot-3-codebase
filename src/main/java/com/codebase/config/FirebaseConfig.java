package com.codebase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initializeFirebase() throws IOException {
        log.info("Initializing Firebase...");

        try (InputStream serviceAccount = new ClassPathResource("firebase-service-account.json").getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase has been initialized successfully.");
            } else {
                log.info("Firebase is already initialized.");
            }
        } catch (IOException e) {
            log.error("Failed to initialize Firebase", e);
            throw e;
        }
    }
}