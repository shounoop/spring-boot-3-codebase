package com.codebase.controller.internal.interfaces;

import com.codebase.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/student")
public interface StudentController {
    @PostMapping("/excel/upload")
    ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file);

    @GetMapping("/list")
    ResponseEntity<ApiResponse> getStudents();

}