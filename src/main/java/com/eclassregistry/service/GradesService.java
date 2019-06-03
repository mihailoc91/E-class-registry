/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.shared.dto.GradesDto;
import com.eclassregistry.shared.dto.StudentDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface GradesService {
    
    GradesDto getGradeById(int id);
    
    List<StudentDto> getAllStudentsFromTeacher(int teacherId);
    
    List<GradesDto>getGradesIdByStudentId(int Id);
    
    List<GradesDto>getAllGrades();
    
    List<GradesDto>getAllGradesFromStudentIdAndSubjectId(int studentId,int subjectId);
    
    void deleteGrade(int id);
    
    List<GradesDto> getAllGradesFromStudentId(int studentId);
    
}
