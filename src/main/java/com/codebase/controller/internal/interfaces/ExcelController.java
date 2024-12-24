package com.codebase.controller.internal.interfaces;

import com.codebase.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/excel")
public interface ExcelController {
    @PostMapping("/upload")
    ResponseEntity<ApiResponse> uploadExcel(@RequestParam("file") MultipartFile file);

    @PostMapping("/import-student")
    ResponseEntity<ApiResponse> importStudent(@RequestParam("file") MultipartFile file);
}
