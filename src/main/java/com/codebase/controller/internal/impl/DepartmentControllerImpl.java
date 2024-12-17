package com.codebase.controller.internal.impl;

import com.codebase.component.response.ApiResponseFactory;
import com.codebase.controller.internal.interfaces.DepartmentController;
import com.codebase.model.entity.Department;
import com.codebase.model.response.ApiResponse;
import com.codebase.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {
    private final DepartmentService departmentService;

    private final ApiResponseFactory apiResponseFactory;

    public ResponseEntity<ApiResponse> saveDepartment(@RequestBody Department department) {
        return apiResponseFactory.success(departmentService.saveDepartment(department));
    }

    public ResponseEntity<ApiResponse> fetchDepartmentList() {
        return apiResponseFactory.success(departmentService.fetchDepartmentList());
    }

    public ResponseEntity<ApiResponse> updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId) {
        return apiResponseFactory.success(departmentService.updateDepartment(department, departmentId));
    }

    public ResponseEntity<ApiResponse> deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return apiResponseFactory.success("Deleted Successfully");
    }
}
