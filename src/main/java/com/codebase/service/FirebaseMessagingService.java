package com.codebase.service;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface FirebaseMessagingService {

    void sendToTopic(String topic, String title, String body) throws FirebaseMessagingException;

    void subscribeTokenToTopic(String token, String topic) throws FirebaseMessagingException;
}