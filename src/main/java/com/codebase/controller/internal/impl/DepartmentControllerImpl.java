package com.codebase.controller.internal.impl;

import com.codebase.component.response.ApiRespFactory;
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

    private final ApiRespFactory apiRespFactory;

    public ResponseEntity<ApiResponse> saveDepartment(@RequestBody Department department) {
        return apiRespFactory.success(departmentService.saveDepartment(department));
    }

    public ResponseEntity<ApiResponse> fetchDepartmentList() {
        return apiRespFactory.success(departmentService.fetchDepartmentList());
    }

    public ResponseEntity<ApiResponse> updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId) {
        return apiRespFactory.success(departmentService.updateDepartment(department, departmentId));
    }

    public ResponseEntity<ApiResponse> deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return apiRespFactory.success("Deleted Successfully");
    }
}
