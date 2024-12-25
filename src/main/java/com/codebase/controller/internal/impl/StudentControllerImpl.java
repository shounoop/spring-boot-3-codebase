package com.codebase.controller.internal.impl;

import com.codebase.component.response.ApiResponseFactory;
import com.codebase.controller.internal.interfaces.StudentController;
import com.codebase.enums.DomainCode;
import com.codebase.exception.model.AppException;
import com.codebase.model.dto.StudentDto;
import com.codebase.model.response.ApiResponse;
import com.codebase.service.interfaces.ExcelExportService;
import com.codebase.service.interfaces.StudentService;
import com.codebase.util.ExcelUtility;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StudentControllerImpl implements StudentController {
    private final StudentService stuService;
    private final ExcelExportService excelExportService;

    private final ApiResponseFactory apiResponseFactory;

    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        if (ExcelUtility.hasExcelFormat(file)) {
            stuService.importStudents(file);
            String message = "The Excel file is uploaded: " + file.getOriginalFilename();

            return apiResponseFactory.success(message);
        }

        String message = "Please upload an excel file!";
        log.error(message);

        throw new AppException(DomainCode.INVALID_PARAMETER);
    }

    public ResponseEntity<ApiResponse> getStudents() {
        List<StudentDto> students = stuService.findAll();

        return apiResponseFactory.success(students);
    }

    @Override
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<StudentDto> students = stuService.findAll();
        excelExportService.exportStudentToExcel(response, students);
    }
}