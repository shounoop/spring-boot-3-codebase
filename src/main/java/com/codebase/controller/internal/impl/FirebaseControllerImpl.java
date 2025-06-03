package com.codebase.controller.internal.impl;

import com.codebase.component.response.ApiResponseFactory;
import com.codebase.controller.internal.FirebaseController;
import com.codebase.enums.DomainCode;
import com.codebase.exception.model.AppException;
import com.codebase.model.primary.request.SubscribeRequest;
import com.codebase.model.primary.response.ApiResponse;
import com.codebase.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FirebaseControllerImpl implements FirebaseController {

    private final ApiResponseFactory apiResponseFactory;

    private final FirebaseMessagingService firebaseService;

    @PostMapping("/subscribe")
    public ResponseEntity<ApiResponse> subscribeToTopic(@RequestBody SubscribeRequest request) {
        try {
            firebaseService.subscribeTokenToTopic(request.getToken(), request.getTopic());
            return apiResponseFactory.success("Subscribed successfully");
        } catch (FirebaseMessagingException e) {
            throw new AppException(DomainCode.INTERNAL_SERVICE_ERROR);
        }
    }
}
