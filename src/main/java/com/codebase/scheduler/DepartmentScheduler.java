package com.codebase.scheduler;

import com.codebase.model.primary.entity.Department;
import com.codebase.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DepartmentScheduler {

    private final DepartmentService departmentService;

    @Scheduled(fixedRate = 6000) // every 6 seconds
    public void scheduledSaveDepartment() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("123 Main Street")
                .departmentCode("IT001")
                .build();

        departmentService.saveDepartment(department);
        log.info("✅ Department saved at: {}", LocalDateTime.now());
    }
}
