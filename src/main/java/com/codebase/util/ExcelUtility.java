package com.codebase.util;

import com.codebase.enums.DomainCode;
import com.codebase.exception.model.AppException;
import com.codebase.model.primary.dto.StudentDto;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

@Slf4j
@UtilityClass
public class ExcelUtility {

    private static final String XLSX_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String XLS_TYPE = "application/vnd.ms-excel";

    public static boolean hasExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return XLSX_TYPE.equals(contentType) || XLS_TYPE.equals(contentType);
    }

    public static List<StudentDto> excelToStuList(InputStream is, String fileName) {
        try (Workbook workbook = fileName.endsWith(".xlsx") ? new XSSFWorkbook(is) : new HSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                log.error("Sheet named {} not found", sheet.getSheetName());
                throw new AppException(DomainCode.INVALID_PARAMETER);
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
            log.error("Failed to parse Excel file: {} exception: {}", e.getMessage(), e.toString());

            throw new AppException(DomainCode.INTERNAL_SERVICE_ERROR);
        }
    }

    private static StudentDto parseRowToStudent(Row row) {
        StudentDto student = new StudentDto();

        for (Cell cell : row) {
            switch (cell.getColumnIndex()) {
                case 1 -> student.setStudentName(getCellValueAsString(cell));
                case 2 -> student.setEmail(getCellValueAsString(cell));
                case 3 -> student.setMobileNo(getCellValueAsString(cell));
                default -> {
                    // Handle unexpected columns gracefully
                }
            }
        }

        return student;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
