package com.example.mediumbatis.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Student {
    private Integer idStudent;
    private String name;
    private int age;
    private int sex;

    public Student(Integer idStudent, String name, int age, int sex) {
        this.idStudent = idStudent;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Student() {
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

}
