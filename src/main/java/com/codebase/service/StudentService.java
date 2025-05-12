package com.codebase.service;

import com.codebase.model.primary.dto.StudentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    void importStudents(MultipartFile file);

    List<StudentDto> findAll();
}