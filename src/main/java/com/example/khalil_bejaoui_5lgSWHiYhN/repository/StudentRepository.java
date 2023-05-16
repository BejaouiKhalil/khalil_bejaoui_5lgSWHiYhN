package com.example.khalil_bejaoui_5lgSWHiYhN.repository;

import com.example.khalil_bejaoui_5lgSWHiYhN.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    @EntityGraph(attributePaths = {"studentClass"})
    @Override
    Page<Student> findAll(Specification<Student> spec, Pageable pageable);
}
