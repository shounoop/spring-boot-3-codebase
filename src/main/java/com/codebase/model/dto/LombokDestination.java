package com.codebase.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LombokDestination {
    private String name;
    private String description;
}