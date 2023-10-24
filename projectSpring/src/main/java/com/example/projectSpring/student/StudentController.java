package com.example.projectSpring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentsService studentsService;

    @Autowired
    public StudentController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    /*return list of all students*/
    @GetMapping
    public List<Student> getStudents() {
        return studentsService.getStudents();
    }

    /*searching student by email and return student if exist*/
    @GetMapping(path = "{id}")
    public Optional<Student> getStudent(@PathVariable("id") Long id) {
        return studentsService.isStudentExist(id);
    }

    /*Add a new student.*/
    @PostMapping
    public void addNewStudent(@RequestBody Student student) {
        studentsService.addNewStudent(student);
    }

    /*delete student by id*/
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentsService.deleteStudent(studentId);
    }

    /*Updating student data by ID.*/
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentsService.updateStudent(studentId, name, email);
    }
}
