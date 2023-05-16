package com.example.khalil_bejaoui_5lgSWHiYhN.service.impl;

import com.example.khalil_bejaoui_5lgSWHiYhN.domain.Student;
import com.example.khalil_bejaoui_5lgSWHiYhN.dto.StudentDTO;
import com.example.khalil_bejaoui_5lgSWHiYhN.mapper.StudentMapper;
import com.example.khalil_bejaoui_5lgSWHiYhN.repository.StudentRepository;
import com.example.khalil_bejaoui_5lgSWHiYhN.service.StudentService;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<StudentDTO> findAllStudent(String className, String teacherFirstName, String teacherLastName,
          Pageable pageable) {
        Specification<Student> specification = Specification.where(null);

        if (StringUtils.isNotBlank(className)) {
            specification = specification.and(
                  (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("studentClass").get("name"),
                        className));
        }

        if (StringUtils.isNotBlank(teacherFirstName) && StringUtils.isNotBlank(teacherLastName)) {
            specification = specification.and((root, query, criteriaBuilder) ->
                  criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("studentClass").get("teacher").get("firstName"), teacherFirstName),
                        criteriaBuilder.equal(root.get("studentClass").get("teacher").get("lastName"), teacherLastName)
                  )
            );
        } else if (StringUtils.isNotBlank(teacherFirstName) || StringUtils.isNotBlank(teacherLastName)) {
            throw new IllegalArgumentException("You need to provide both first name and last name for the teacher.");
        }

        Page<Student> studentPage = studentRepository.findAll(specification, pageable);
        List<StudentDTO> studentDTOList = StudentMapper.INSTANCE.toStudentDTOList(studentPage.getContent());
        return new PageImpl<>(studentDTOList, pageable, studentPage.getTotalElements());
    }

}
