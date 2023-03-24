package com.example.boumidoubatisexample.controller;

import com.example.boumidoubatisexample.entity.Student;
import com.example.boumidoubatisexample.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "")
public class Controller {
    @Autowired
    StudentMapper studentMapper;

    @GetMapping(path = "")
    public List<Student> getAllStudent() {
        return studentMapper.selectList(null);
    }

    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Student studentById = studentMapper.selectById(id);
        return studentById;
    }

//    @PostMapping(path = "")
//    public Student addStudent(@RequestBody Student student) {
//        studentMapper.insertStudent(student);
//        return student;
//    }
//
//    @PutMapping(path = "{id}")
//    public Student updateStudent(@RequestBody Student student, @PathVariable Integer id) {
//        studentMapper.updateStudent(student, id);
//        return student;
//    }
//
//    @DeleteMapping(path = "{id}")
//    public String deleteStudentById(@PathVariable Integer id) {
//        studentMapper.deleteStudent(id);
//        return "success!";
//    }
}

