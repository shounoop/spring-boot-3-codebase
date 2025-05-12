package com.codebase.service.impl;

import com.codebase.model.primary.dto.StudentDto;
import com.codebase.service.ExcelExportService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    private Sheet sheet;

    private void writeHeader(SXSSFWorkbook workbook) {
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) (13 * 20));
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Student Name", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Mobile No.", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write(List<StudentDto> students, SXSSFWorkbook workbook) throws IOException {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeight((short) (14 * 20));
        style.setFont(font);

        for (StudentDto student : students) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, student.getId(), style);
            createCell(row, columnCount++, student.getStudentName(), style);
            createCell(row, columnCount++, student.getEmail(), style);
            createCell(row, columnCount, student.getMobileNo(), style);

            // Flush memory every 100 rows
            if (rowCount % 100 == 0) {
                ((SXSSFSheet) sheet).flushRows(100);
            }
        }
    }

    public void exportStudentToExcel(HttpServletResponse response, List<StudentDto> students) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100);  // Keep only 100 rows in memory
             ServletOutputStream outputStream = response.getOutputStream()) {

            writeHeader(workbook);
            write(students, workbook);
            workbook.write(outputStream);
        }
    }
}
