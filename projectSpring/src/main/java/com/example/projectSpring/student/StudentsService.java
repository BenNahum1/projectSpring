package com.example.projectSpring.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentsService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /*Returns a list of all students.*/
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    /*If the student exists, return all the details about him.*/
    public Optional<Student> isStudentExist(Long id) {
        Optional<Student> student = studentRepository.findStudentsById(id);
        if (!student.isPresent()){
            throw new IllegalArgumentException("this id: " + id + " dos not exist.");
        }
        return student;
    }

    /*Adds a new student only if the student's ID does not exist in the system.*/
    public void addNewStudent(Student student) {
        Validation.checkValidId(student.getId());
        Validation.isValidEmail(student.getEmail());

        Optional<Student> studentOptional = studentRepository.findStudentsById(student.getId());
        if (studentOptional.isPresent()){
            throw new IllegalArgumentException("this student with this id: " + student.getId() + " already exist.");
        }

        studentRepository.save(student);
    }

    /*Deletes a student only if the student ID card exists in the system.*/
    public void deleteStudent(Long studentId) {
        Validation.checkValidId(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalArgumentException("student with this id: " + studentId + " dose not exists.");
        }
        studentRepository.deleteById(studentId);
    }

    /*Checks if the student exists and if it exists then checks student data.*/
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Validation.checkValidId(studentId);
        Validation.isValidEmail(email);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "student with id "+ studentId + " doesn't exist."));


        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentsByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalArgumentException("email taken");
            }
            student.setEmail(email);
        }

    }

}
