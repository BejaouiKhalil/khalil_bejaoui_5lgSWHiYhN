package com.example.khalil_bejaoui_5lgSWHiYhN.domain;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Class {
    @Id @GeneratedValue private Long id;

    private String name;
    @ManyToOne @JoinColumn(name = "teacher_id") private Teacher teacher;
    @OneToMany(mappedBy = "studentClass") private List<Student> students;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Class aClass = (Class) o;
        return getId().equals(aClass.getId()) && Objects.equals(getName(), aClass.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Class{" + "id=" + id + ", name='" + name + '\'' + ", teacher=" + teacher + '}';
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
