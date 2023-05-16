package com.example.khalil_bejaoui_5lgSWHiYhN.mapper;

import com.example.khalil_bejaoui_5lgSWHiYhN.domain.Student;
import com.example.khalil_bejaoui_5lgSWHiYhN.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);


    List<StudentDTO> toStudentDTOList(List<Student> students);
}
