package com.codebase.service.impl;

import com.codebase.enums.DomainCode;
import com.codebase.exception.model.AppException;
import com.codebase.model.primary.entity.Department;
import com.codebase.repository.primary.DepartmentRepository;
import com.codebase.service.DepartmentService;
import com.codebase.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final FirebaseMessagingService firebaseService;

    @Override
    public Department saveDepartment(Department department) {
        log.info("Saving department: {}", department);

        Department saved = departmentRepository.save(department);
        log.info("Department saved successfully with ID: {}", saved.getDepartmentId());

        try {
            firebaseService.sendToTopic("new-x", "New X Created", saved.getDepartmentCode());
            log.info("Notification sent to topic 'new-x' with department code: {}", saved.getDepartmentCode());
        } catch (FirebaseMessagingException e) {
            log.error("Failed to send notification to Firebase for department code: {}", saved.getDepartmentCode(), e);
        }

        return saved;
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department, Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if (optionalDepartment.isEmpty()) {
            throw new AppException(DomainCode.INVALID_PARAMETER);
        }

        Department depDB = optionalDepartment.get();

        if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
            depDB.setDepartmentName(department.getDepartmentName());
        }

        if (Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
            depDB.setDepartmentCode(department.getDepartmentCode());
        }

        return departmentRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
