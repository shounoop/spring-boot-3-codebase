package com.codebase.model.second.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
public class SecondEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String studentName;
    private String email;
    private String mobileNo;

    public SecondEntity(String studentName, String email, String mobileNo) {
        this.studentName = studentName;
        this.email = email;
        this.mobileNo = mobileNo;
    }
}