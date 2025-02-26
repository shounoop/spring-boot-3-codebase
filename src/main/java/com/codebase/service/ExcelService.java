package com.codebase.service;

import com.codebase.model.dto.StudentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    List<List<String>> importExcel(MultipartFile file) throws IOException;

    List<StudentDto> importStudent(MultipartFile file);
}
