package com.codebase.controller.internal.impl;

import com.codebase.model.entity.Department;
import com.codebase.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codebase.controller.internal.interfaces.DepartmentController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {
    private final DepartmentService departmentService;

    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    public List<Department> fetchDepartmentList() {
        return departmentService.fetchDepartmentList();
    }

    public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId) {
        return departmentService.updateDepartment(department, departmentId);
    }

    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Deleted Successfully";
    }
}
