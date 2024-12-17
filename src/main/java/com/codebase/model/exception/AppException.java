package com.codebase.model.exception;

import com.codebase.enums.DomainCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class AppException extends RuntimeException {
    private final DomainCode code;
    private transient Object[] args;
    private HttpStatus status;

    public AppException(DomainCode code) {
        this.code = code;
    }

    public AppException(DomainCode code, Object[] args) {
        this.code = code;
        this.args = args;
    }

    public AppException(HttpStatus status, DomainCode code, Object[] args) {
        this.status = status;
        this.code = code;
        this.args = args;
    }

    public AppException(HttpStatus status, DomainCode code) {
        this.status = status;
        this.code = code;
    }
}
