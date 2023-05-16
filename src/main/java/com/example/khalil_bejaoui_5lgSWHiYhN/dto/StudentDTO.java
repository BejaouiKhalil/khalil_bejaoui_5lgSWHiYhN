package com.example.khalil_bejaoui_5lgSWHiYhN.dto;

public class StudentDTO {
    private String firstName;
    private String lastName;
    private ClassDTO studentClass;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ClassDTO getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(ClassDTO studentClass) {
        this.studentClass = studentClass;
    }
}
