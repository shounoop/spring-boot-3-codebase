package com.codebase.model.response;

import com.codebase.model.dto.FieldErrorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Set;

@Builder
@With
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String code = "AS-000";
    private String message;
    private Object data;
    private Set<FieldErrorDto> fieldErrors;
}
