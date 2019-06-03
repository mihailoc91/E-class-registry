/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.StudentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    @Query(value="SELECT students.student_id, students.first_name, students.last_name FROM students WHERE students.class = ?1" ,nativeQuery = true)
    List<Object[]> getAllStudentWhereClassId(int id);
    
    @Query(value="SELECT students_parents.student_id FROM students_parents WHERE students_parents.parent_id = ?1" ,nativeQuery = true)
    List<Integer>getAllStudentsWhereParentId(int id);
    
    @Query(value="SELECT students.student_id FROM students WHERE students.class = ?1" ,nativeQuery = true)
    List<Integer> getAllStudentsIdWhereClassId(int id);
}
