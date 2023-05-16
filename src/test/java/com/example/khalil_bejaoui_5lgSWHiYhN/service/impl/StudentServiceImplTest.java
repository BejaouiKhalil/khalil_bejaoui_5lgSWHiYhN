package com.example.khalil_bejaoui_5lgSWHiYhN.service.impl;

import com.example.khalil_bejaoui_5lgSWHiYhN.domain.Class;
import com.example.khalil_bejaoui_5lgSWHiYhN.domain.Student;
import com.example.khalil_bejaoui_5lgSWHiYhN.domain.Teacher;
import com.example.khalil_bejaoui_5lgSWHiYhN.dto.StudentDTO;
import com.example.khalil_bejaoui_5lgSWHiYhN.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock StudentRepository studentRepository;

    @InjectMocks StudentServiceImpl studentService;

    @Test
    public void should_return_list_of_students() {
        Student student = getStudent();
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<Student> studentPage = new PageImpl<>(List.of(student));


        when(studentRepository.findAll((Specification<Student>) any(), eq(pageable))).thenReturn(studentPage);

        Page<StudentDTO> result = studentService.findAllStudent(null, null, null, pageable);

        assertEquals(studentPage.getTotalElements(), result.getTotalElements());
        assertThat(result.getContent().get(0)).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);

    }


    @Test
    public void should_return_list_of_students_byClassName() {
        Student student = getStudent();
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<Student> studentPage = new PageImpl<>(List.of(student));

        when(studentRepository.findAll((Specification<Student>) any(), eq(pageable))).thenReturn(studentPage);

        Page<StudentDTO> result = studentService.findAllStudent("className", null, null, pageable);

        assertEquals(studentPage.getTotalElements(), result.getTotalElements());
        assertThat(result.getContent().get(0)).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);

    }

    @Test
    public void should_throw_IllegalArgumentException_when_we_dont() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        Exception exception = assertThrows(IllegalArgumentException.class,
              () -> studentService.findAllStudent("className", "firstName", null, pageable));

        assertEquals("You need to provide both first name and last name for the teacher.", exception.getMessage());

    }

    private Student getStudent() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("teacherFirstName");
        teacher.setLastName("teacherLastName");

        Class classroom = new Class();
        classroom.setName("className");
        classroom.setTeacher(teacher);

        Student student = new Student();
        student.setLastName("test");
        student.setFirstName("test");
        student.setStudentClass(classroom);
        return student;
    }

}
