package com.example.khalil_bejaoui_5lgSWHiYhN.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student {
    @Id @GeneratedValue private Long id;

    private String firstName;
    private String lastName;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "class_id") private Class studentClass;


    public Long getId() {
        return id;
    }

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

    public Class getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Class studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return getId().equals(student.getId()) && Objects.equals(getFirstName(), student.getFirstName()) &&
              Objects.equals(getLastName(), student.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' +
              ", studentClass=" + studentClass + '}';
    }
}
