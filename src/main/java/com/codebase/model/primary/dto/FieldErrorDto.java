package com.codebase.model.primary.dto;

import lombok.Data;

@Data
public class FieldErrorDto {
    private String fieldName;
    private String errorMessage;
}
