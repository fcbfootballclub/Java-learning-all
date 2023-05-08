package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Student;
import org.example.exception.StudentServiceException;
import org.example.payload.StudentRequest;
import org.example.payload.StudentResponse;
import org.example.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentRepository.findAll().stream().map(item -> modelMapper.map(item, StudentResponse.class)).collect(Collectors.toList());
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            return modelMapper.map(student.get(), StudentResponse.class);
        }
        throw new StudentServiceException("Product not found", 404);
    }

    @Override
    public StudentRequest addStudent(StudentRequest studentRequest) {
        Student student = modelMapper.map(studentRequest, Student.class);
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("studentId")
                .withMatcher("email", ignoreCase());
        Example<Student> instance = Example.of(student, modelMatcher);

        if(!studentRepository.exists(instance)) {
            studentRepository.save(student);
            return studentRequest;
        };
        throw new StudentServiceException("Student already exist!", 400);
    }

    @Override
    public StudentRequest updateStudent(StudentRequest studentRequest, Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            BeanUtils.copyProperties(studentRequest, student.get());
            studentRepository.save(student.get());
            return studentRequest;
        }
        throw new StudentServiceException("Student not found!", 404);
    }

    @Override
    public Long deleteStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            studentRepository.delete(student.get());
            return studentId;
        }
        throw new StudentServiceException("Product delete not found!", 404);
    }
}
