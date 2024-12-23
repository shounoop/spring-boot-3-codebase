package com.codebase.model.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Integer id;
    private String studentName;
    private String email;
    private String mobileNo;
}