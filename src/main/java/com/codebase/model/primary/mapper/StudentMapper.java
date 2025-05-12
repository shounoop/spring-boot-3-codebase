package com.codebase.model.primary.mapper;

import com.codebase.model.primary.dto.StudentDto;
import com.codebase.model.primary.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentDto studentDto);

    StudentDto toDto(Student student);

    List<Student> toEntityList(List<StudentDto> studentDtoList);

    List<StudentDto> toDtoList(List<Student> studentList);

}