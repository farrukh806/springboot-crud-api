package com.example.crudapp.student;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student studentData) throws IllegalAccessException {
        Optional<Student> student = studentRepository.findByEmail(studentData.getEmail());
        if(student.isPresent()){
            throw new IllegalAccessException("User already exists with the given email");
        }
        return studentRepository.save(studentData);
    }

    public boolean removeStudentById(Long id) {
        boolean isExist = studentRepository.existsById(id);
        if(isExist) {
            studentRepository.deleteById(id);
        }
        return isExist;
    }

    @Transactional
    public Student updateStudent(Long id, Student studentData){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("student with given id not found"));

        if(
                (studentData.getName() != null) &&
                (studentData.getName().length() > 0) &&
                !(studentData.getName().equals(student.getName()))
        )
            student.setName(studentData.getName());


        if(
                (studentData.getEmail() != null) &&
                (studentData.getEmail().length() > 0) &&
                !(studentData.getEmail().equals(student.getEmail()))
        )
            student.setEmail(studentData.getEmail());


        if(
                (studentData.getDob() != null) &&
                !(studentData.getDob().equals(student.getDob()))
        )
            student.setDob(studentData.getDob());

        return student;
    }

}