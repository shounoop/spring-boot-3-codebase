package com.codebase.model.primary.request;

import lombok.Data;

@Data
public class SubscribeRequest {
    private String token;
    private String topic;
}
