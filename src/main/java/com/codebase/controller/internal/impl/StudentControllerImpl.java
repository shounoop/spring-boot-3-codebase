package com.codebase.controller.internal.impl;

import com.codebase.component.response.ApiResponseFactory;
import com.codebase.controller.internal.interfaces.StudentController;
import com.codebase.enums.DomainCode;
import com.codebase.model.dto.StudentDto;
import com.codebase.exception.model.AppException;
import com.codebase.model.response.ApiResponse;
import com.codebase.service.interfaces.StudentService;
import com.codebase.util.ExcelUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StudentControllerImpl implements StudentController {
    private final StudentService stuService;

    private final ApiResponseFactory apiResponseFactory;

    @PostMapping("/excel/upload")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        if (ExcelUtility.hasExcelFormat(file)) {
            try {
                stuService.saveStudentsFromFile(file);
                String message = "The Excel file is uploaded: " + file.getOriginalFilename();

                return apiResponseFactory.success(message);
            } catch (Exception exp) {
                String message = "The Excel file is not upload: " + file.getOriginalFilename() + "!";
                log.error(message);

                return apiResponseFactory.failWithDomainException(new AppException(DomainCode.EXPECTATION_FAILED));
            }
        }

        String message = "Please upload an excel file!";
        log.error(message);

        return apiResponseFactory.failWithBadInputParameter(new AppException(DomainCode.INVALID_PARAMETER));
    }

    @GetMapping("/student-list")
    public ResponseEntity<ApiResponse> getStudents() {
        List<StudentDto> students = stuService.findAll();

        return apiResponseFactory.success(students);
    }
}