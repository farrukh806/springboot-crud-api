package com.example.crudapp.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        System.out.println("Running");
        return studentService.getStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student studentData) throws IllegalAccessException {
        return studentService.createStudent(studentData);
    }

    @PutMapping(path = "{id}")
    public Student updateStudentById(@PathVariable("id") Long id, @RequestBody Student studentData){
        return  studentService.updateStudent(id, studentData);
    }
    @DeleteMapping(path = "{id}")
    public boolean removeStudent(@PathVariable("id") Long id) {

      return studentService.removeStudentById(id);

    }
}