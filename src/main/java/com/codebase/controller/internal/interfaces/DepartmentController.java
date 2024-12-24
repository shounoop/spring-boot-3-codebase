package com.codebase.controller.internal.interfaces;

import com.codebase.model.entity.Department;
import com.codebase.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/department")
public interface DepartmentController {
    @PostMapping("/create")
    ResponseEntity<ApiResponse> saveDepartment(@RequestBody Department department);

    @GetMapping("/read")
    ResponseEntity<ApiResponse> fetchDepartmentList();

    @PutMapping("/update/{id}")
    ResponseEntity<ApiResponse> updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ApiResponse> deleteDepartmentById(@PathVariable("id") Long departmentId);
}
