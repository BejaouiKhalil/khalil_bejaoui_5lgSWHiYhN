package com.example.khalil_bejaoui_5lgSWHiYhN.service;

import com.example.khalil_bejaoui_5lgSWHiYhN.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page<StudentDTO> findAllStudent(String className, String teacherFirstName, String teacherLastName,
          Pageable pageable);
}
