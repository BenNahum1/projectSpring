package com.example.projectSpring.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
//    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findStudentsById(Long id);
    Optional<Student> findStudentsByEmail(String email);

}
