package org.example.service;

import org.example.payload.StudentRequest;
import org.example.payload.StudentResponse;

import java.util.List;

public interface StudentService {
    public List<StudentResponse> getAllStudent();
    public StudentResponse getStudentById(Long studentId);
    public StudentRequest addStudent(StudentRequest studentRequest);
    public StudentRequest updateStudent(StudentRequest studentRequest, Long studentId);
    public Long deleteStudentById(Long studentId);
}
