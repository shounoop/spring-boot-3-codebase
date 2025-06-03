package com.codebase.controller.internal;

import com.codebase.model.primary.request.SubscribeRequest;
import com.codebase.model.primary.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/firebase")
public interface FirebaseController {

    @PostMapping("/subscribe")
    ResponseEntity<ApiResponse> subscribeToTopic(@RequestBody SubscribeRequest request);
}
