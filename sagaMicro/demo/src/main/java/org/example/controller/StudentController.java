package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.payload.StudentRequest;
import org.example.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "student")
public class StudentController {
    private final StudentService studentService;

    @RequestMapping(path = "")
    public ResponseEntity<?> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @RequestMapping(path = "{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addStudent(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.addStudent(studentRequest));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.updateStudent(studentRequest, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }
}
