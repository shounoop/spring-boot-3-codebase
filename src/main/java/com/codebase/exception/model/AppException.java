package com.codebase.exception.model;

import com.codebase.enums.DomainCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class AppException extends RuntimeException {
    private final DomainCode domainCode;
    private transient Object[] args;
    private HttpStatus status;

    public AppException(DomainCode domainCode) {
        this.domainCode = domainCode;
    }

    public AppException(DomainCode domainCode, Object[] args) {
        this.domainCode = domainCode;
        this.args = args;
    }

    public AppException(HttpStatus status, DomainCode domainCode, Object[] args) {
        this.status = status;
        this.domainCode = domainCode;
        this.args = args;
    }

    public AppException(HttpStatus status, DomainCode domainCode) {
        this.status = status;
        this.domainCode = domainCode;
    }
}
