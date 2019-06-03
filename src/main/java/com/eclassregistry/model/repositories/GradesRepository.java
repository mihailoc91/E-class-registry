/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.GradesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *  
 * @author Grupa1
 */
public interface GradesRepository extends JpaRepository<GradesEntity, Integer>{
    
    @Query(value="SELECT classes.class_id FROM classes WHERE classes.teacher_id = ?1" ,nativeQuery = true)
    int findClassForTeacherWithId(int id);
    
    @Query(value = "Select grades.grade_id,grades.teacher,grades.subject,grades.date,grades.grade,grades.final FROM grades WHERE grades.student = ?1",nativeQuery = true)
    List<Object[]> getGradesIdByStudentId(int id);
 
    @Query(value = "Select grades.grade FROM grades WHERE grades.student =?1 AND grades.subject = ?2 AND grades.final=0",nativeQuery = true)
    List<Integer> getGradeFromStudentIdAndSubjectID(int studentId,int subjectId);
    
    @Query(value = "Select grades.grade FROM grades WHERE grades.student =?1 AND grades.subject = ?2 AND grades.final=1" ,nativeQuery = true)
    Integer getFinalGradeFromStudentIdAndSubjectID(int studentId,int subjectId);
    
    @Query(value = "Select grades.grade_id,grade FROM grades WHERE grades.student =?1 AND grades.subject = ?2",nativeQuery = true)
    List<Integer> getGradeAndGradeIdFromStudentIdAndSubjectID(int studentId,int subjectId);
    
    @Query(value = "Select grades.grade_id,grades.final,grades.grade,grades.date,grades.teacher FROM grades WHERE grades.student =?1 AND grades.subject = ?2",nativeQuery = true)
    List<Object[]> getAllGradesFromStudentIdAndSubjectID(int studentId,int subjectId);
    
    @Query(value = "Select grades.grade_id,grades.final,grades.grade,grades.date,grades.teacher,grades.subject FROM grades WHERE grades.student =?1",nativeQuery = true)
    List<Object[]> getAllGradesFromStudentId(int studentId);
    
    @Query(value = "Select grades.grade FROM grades WHERE grades.student =?1 AND grades.subject = ?2 AND grades.final =1",nativeQuery = true)
    Integer getAllFinalGradesFromStudentIdAndSubjectId(int studentId,int subjectId);
}
