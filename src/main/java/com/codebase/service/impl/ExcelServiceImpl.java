package com.codebase.service.impl;

import com.codebase.enums.DomainCode;
import com.codebase.exception.model.AppException;
import com.codebase.model.dto.StudentDto;
import com.codebase.service.interfaces.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public List<List<String>> importExcel(MultipartFile file) throws IOException {
        List<List<String>> data = new ArrayList<>();
        Workbook workbook;

        // Detect the file format
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new IllegalArgumentException("File name is missing.");
        }
        if (fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("Unsupported file format. Please upload a .xls or .xlsx file.");
        }

        // Read data from the first sheet
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                rowData.add(getCellValue(cell));
            }
            data.add(rowData);
        }
        workbook.close();
        return data;
    }

    public List<StudentDto> importStudent(MultipartFile file) {
        // Validate the file
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty. Please upload a valid Excel file.");
        }
        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
            throw new IllegalArgumentException("Invalid file format. Only .xls and .xlsx files are supported.");
        }

        try (Workbook workbook = fileName.endsWith(".xlsx")
                ? new XSSFWorkbook(file.getInputStream())
                : new HSSFWorkbook(file.getInputStream())) {

            List<StudentDto> stuList = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip the header row
                    continue;
                }

                StudentDto studentDto = new StudentDto();

                // Safely extract and set each cell value
                studentDto.setStudentName(getCellValue(row.getCell(1)));
                studentDto.setEmail(getCellValue(row.getCell(2)));
                studentDto.setMobileNo(getCellValue(row.getCell(3)));

                // Validate required fields
                if (studentDto.getStudentName().isEmpty() || studentDto.getEmail().isEmpty() || studentDto.getMobileNo().isEmpty()) {
                    throw new IllegalArgumentException("Missing required fields in row " + (row.getRowNum() + 1));
                }

                stuList.add(studentDto);
            }

            return stuList;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage(), e);
        }
    }

    private String getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue().toString();
                }
                yield String.valueOf(cell.getNumericCellValue());
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
