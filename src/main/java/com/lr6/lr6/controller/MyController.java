package com.lr6.lr6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lr6.lr6.entity.Student;
import com.lr6.lr6.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> allStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);

        if (student == null) {
            return new ResponseEntity<>("Студент с ID " + id + " не найден", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student newStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PutMapping("/students")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return new ResponseEntity<>("Студент успешно обновлен", HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);

        if (student == null) {
            return new ResponseEntity<>("Невозможно удалить: Студент с ID " + id + " не найден", HttpStatus.NOT_FOUND);
        }

        studentService.deleteStudent(id);
        return new ResponseEntity<>("Студент с ID " + id + " был удален", HttpStatus.OK);
    }
}