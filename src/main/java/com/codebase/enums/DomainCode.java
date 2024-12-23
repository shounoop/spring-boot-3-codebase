package com.codebase.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum DomainCode {
    SUCCESS("TD-000", "Success", HttpStatus.OK.value()),
    INVALID_PARAMETER("TD-001", "Invalid parameter: %s", HttpStatus.BAD_REQUEST.value()),
    INTERNAL_SERVICE_ERROR("TD-002", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    FORBIDDEN("TD-003", "Forbidden", HttpStatus.FORBIDDEN.value()),
    NO_PERMISSION("TD-005", "No permission", HttpStatus.FORBIDDEN.value()),
    DEPLOY_CAMUNDA_FAIL("TD-006", "Deploy camunda fail", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    CONFLICT_ERROR("TD-016", "The resource was modified by another transaction", HttpStatus.CONFLICT.value()),
    EXPECTATION_FAILED("TD-017", "EXPECTATION_FAILED", HttpStatus.EXPECTATION_FAILED.value()),
    ;

    private final String code;
    private final String description;
    private final int status;
}
