package com.codebase.component.response;

import com.codebase.enums.DomainCode;
import com.codebase.model.primary.dto.FieldErrorDto;
import com.codebase.exception.model.AppException;
import com.codebase.model.primary.response.ApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.codebase.enums.DomainCode.CONFLICT_ERROR;
import static com.codebase.enums.DomainCode.INVALID_PARAMETER;
import static com.codebase.enums.DomainCode.SUCCESS;

@Component
@RequiredArgsConstructor
public class ApiResponseFactory {

    private final MessageSource messageSource;

    public ResponseEntity<ApiResponse> failWithDomainException(AppException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(e.getDomainCode().getCode());
        apiResponse.setMessage(this.messageSource.getMessage(e.getDomainCode().getCode(), e.getArgs(), this.locale()));
        return ResponseEntity.status(e.getDomainCode().getStatus()).body(apiResponse);
    }

    public ResponseEntity<ApiResponse> success(Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(SUCCESS.getCode());
        apiResponse.setMessage(this.messageSource.getMessage(SUCCESS.getCode(), null, this.locale()));
        apiResponse.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    public ResponseEntity<ApiResponse> success(DomainCode domainCode, Object... args) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(domainCode.getCode());
        apiResponse.setMessage(this.messageSource.getMessage(domainCode.getCode(), args, this.locale()));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    public ResponseEntity<ApiResponse> success(DomainCode domainCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(domainCode.getCode());
        apiResponse.setMessage(this.messageSource.getMessage(domainCode.getCode(), null, this.locale()));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    public ResponseEntity<ApiResponse> success() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(SUCCESS.getCode());
        apiResponse.setMessage(this.messageSource.getMessage(SUCCESS.getCode(), null, this.locale()));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    public ResponseEntity<ApiResponse> failWithInternalException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse().withCode(DomainCode.INTERNAL_SERVICE_ERROR.getCode()).withMessage(messageSource.getMessage(INVALID_PARAMETER.getCode(), null, this.locale())).withFieldErrors(parseErrorData(e)));
    }

    public ResponseEntity<ApiResponse> failWithBadInputParameter(Exception e) {
        Set<FieldErrorDto> fieldErrors = parseErrorData(e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse().withCode(INVALID_PARAMETER.getCode()).withMessage(messageSource.getMessage(INVALID_PARAMETER.getCode(), null, this.locale())).withFieldErrors(fieldErrors));
    }

    public Locale locale() {
        return LocaleContextHolder.getLocale();
    }

    private Set<FieldErrorDto> parseErrorData(Exception e) {
        Set<FieldErrorDto> errorSet = ConcurrentHashMap.newKeySet();

        if (e instanceof HttpMessageNotReadableException) {
            InvalidFormatException formatException = (InvalidFormatException) e.getCause();

            formatException.getPath().parallelStream().forEach(err -> {
                if (StringUtils.isNotEmpty(err.getFieldName())) {
                    FieldErrorDto errorDTO = new FieldErrorDto();
                    errorDTO.setErrorMessage(err.getFieldName());
                    errorDTO.setFieldName(formatException.getMessage());
                    errorSet.add(errorDTO);
                }
            });
        }

        if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> set = ((ConstraintViolationException) e).getConstraintViolations();
            for (ConstraintViolation<?> next : set) {
                FieldErrorDto errorDTO = new FieldErrorDto();
                String field = String.valueOf(next.getPropertyPath());
                errorDTO.setErrorMessage(next.getMessage());
                errorDTO.setFieldName(field);
                errorSet.add(errorDTO);
            }
        }

        if (e instanceof MethodArgumentNotValidException) {

            List<FieldError> errors = ((MethodArgumentNotValidException) e).getFieldErrors();

            errors.parallelStream().forEach(err -> {
                FieldErrorDto errorDTO = new FieldErrorDto();
                errorDTO.setErrorMessage(err.getField());
                errorDTO.setFieldName(err.getDefaultMessage());
                errorSet.add(errorDTO);
            });
        }
        return errorSet;
    }

    public ResponseEntity<ApiResponse> failWithConflictException(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse().withCode(DomainCode.CONFLICT_ERROR.getCode())
                        .withMessage(messageSource.getMessage(CONFLICT_ERROR.getCode(), null, this.locale()))
                        .withFieldErrors(parseErrorData(e))
                );
    }
}
