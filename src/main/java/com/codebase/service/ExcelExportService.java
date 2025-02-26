package com.codebase.service;

import com.codebase.model.dto.StudentDto;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface ExcelExportService {

    void exportStudentToExcel(HttpServletResponse response, List<StudentDto> students) throws IOException;
}
