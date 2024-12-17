package com.codebase.model.dto;

import lombok.Data;

@Data
public class FieldErrorDto {
    private String fieldName;
    private String errorMessage;
}
