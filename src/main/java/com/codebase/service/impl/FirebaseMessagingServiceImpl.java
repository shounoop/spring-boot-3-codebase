package com.codebase.service.impl;

import com.codebase.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService {

    public void sendToTopic(String topic, String title, String body) throws FirebaseMessagingException {
        log.info("Preparing to send message to topic: '{}'", topic);

        Message message = Message.builder()
                .setTopic(topic)
                .putData("title", title)
                .putData("body", body)
                .setWebpushConfig(WebpushConfig.builder()
                        .putHeader("TTL", "300")
                        .setNotification(WebpushNotification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build())
                        .build())
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        log.info("Message sent to topic '{}'. Firebase response: {}", topic, response);
    }

    public void subscribeTokenToTopic(String token, String topic) throws FirebaseMessagingException {
        log.info("Subscribing token '{}' to topic '{}'", token, topic);

        FirebaseMessaging.getInstance().subscribeToTopic(Collections.singletonList(token), topic);

        log.info("Successfully subscribed token to topic: '{}'", topic);
    }
}