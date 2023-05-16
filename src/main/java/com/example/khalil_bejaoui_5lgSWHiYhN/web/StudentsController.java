package com.example.khalil_bejaoui_5lgSWHiYhN.web;

import com.example.khalil_bejaoui_5lgSWHiYhN.domain.Student;
import com.example.khalil_bejaoui_5lgSWHiYhN.dto.StudentDTO;
import com.example.khalil_bejaoui_5lgSWHiYhN.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {
    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<Page<StudentDTO>> getStudents(@RequestParam(required = false) String className,
          @RequestParam(required = false) String teacherFirstName, @RequestParam(required = false) String teacherLastName,
          @PageableDefault(sort = {"firstName"}, direction = Sort.Direction.DESC, value = 20) Pageable pageable) {
        return new ResponseEntity<>(
              studentService.findAllStudent(className, teacherFirstName, teacherLastName, pageable), HttpStatus.OK);
    }
}
