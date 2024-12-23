package com.codebase.util;

import com.codebase.model.dto.StudentDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtility {

    private static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String SHEET = "student";

    private ExcelUtility() {
        // Private constructor to prevent instantiation
    }

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<StudentDto> excelToStuList(InputStream is) {
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet(SHEET);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet named '" + SHEET + "' not found");
            }

            List<StudentDto> stuList = new ArrayList<>();
            Iterator<Row> rows = sheet.iterator();

            // Skip header row
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                StudentDto stu = parseRowToStudent(currentRow);
                stuList.add(stu);
            }

            return stuList;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage(), e);
        }
    }

    private static StudentDto parseRowToStudent(Row row) {
        StudentDto student = new StudentDto();

        for (Cell cell : row) {
            switch (cell.getColumnIndex()) {
                case 1 -> student.setStudentName(cell.getStringCellValue());
                case 2 -> student.setEmail(cell.getStringCellValue());
                case 3 -> student.setMobileNo(cell.getStringCellValue());
                default -> {
                    // Handle unexpected columns gracefully
                }
            }
        }

        return student;
    }
}