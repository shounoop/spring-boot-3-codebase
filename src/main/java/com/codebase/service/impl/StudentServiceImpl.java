package com.codebase.service.impl;

import com.codebase.enums.DomainCode;
import com.codebase.exception.model.AppException;
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
    public void importStudents(MultipartFile file) {
        try {
            List<StudentDto> studentDtoList = ExcelUtility.excelToStuList(file.getInputStream(), file.getOriginalFilename());

            stuRepo.saveAll(StudentMapper.INSTANCE.toEntityList(studentDtoList));
        } catch (IOException ex) {
            throw new AppException(DomainCode.INVALID_PARAMETER);
        }
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = stuRepo.findAll();

        return StudentMapper.INSTANCE.toDtoList(students);
    }
}