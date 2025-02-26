package com.codebase.controller.internal.impl;

import com.codebase.component.response.ApiResponseFactory;
import com.codebase.controller.internal.ExcelController;
import com.codebase.enums.DomainCode;
import com.codebase.exception.model.AppException;
import com.codebase.model.dto.StudentDto;
import com.codebase.model.response.ApiResponse;
import com.codebase.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExcelControllerImpl implements ExcelController {
    private final ExcelService excelService;

    private final ApiResponseFactory apiResponseFactory;

    @Override
    public ResponseEntity<ApiResponse> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<List<String>> data = excelService.importExcel(file);

            return apiResponseFactory.success(data);
        } catch (IOException e) {
            throw new AppException(DomainCode.INVALID_PARAMETER);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> importStudent(MultipartFile file) {
        List<StudentDto> students = excelService.importStudent(file);

        return apiResponseFactory.success(students);
    }
}
