package com.codebase.service.interfaces;

import com.codebase.model.dto.StudentDto;
import com.codebase.model.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    void saveStudentsFromFile(MultipartFile file);

    List<StudentDto> findAll();
}