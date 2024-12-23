package com.codebase.service.impl;

import com.codebase.model.dto.StudentDto;
import com.codebase.model.entity.Student;
import com.codebase.model.mapper.StudentMapper;
import com.codebase.repository.StudentRepository;
import com.codebase.service.interfaces.StudentService;
import com.codebase.util.ExcelUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository stuRepo;

    @Override
    public void saveStudentsFromFile(MultipartFile file) {
        try {
            List<StudentDto> studentDtoList = ExcelUtility.excelToStuList(file.getInputStream());
            stuRepo.saveAll(StudentMapper.INSTANCE.toEntityList(studentDtoList));
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = stuRepo.findAll();

        return StudentMapper.INSTANCE.toDtoList(students);
    }
}