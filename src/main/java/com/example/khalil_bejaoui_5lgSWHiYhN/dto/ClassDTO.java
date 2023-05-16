package com.example.khalil_bejaoui_5lgSWHiYhN.dto;

import com.example.khalil_bejaoui_5lgSWHiYhN.domain.Teacher;

public class ClassDTO {
    private String name;
    private TeacherDTO teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }
}
