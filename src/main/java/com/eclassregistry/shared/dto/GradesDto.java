/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import com.eclassregistry.model.entity.StudentEntity;
import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public class GradesDto implements Serializable{

    private int id;
    
    private TeacherEntity teacher;
  
    private SubjectEntity subject;
    
    private StudentEntity student;
    
    private LocalDateTime date;
            
    private int grade;
    
    private Boolean finalGrade;
    
    private List<GradesDto> grades;
    
    private String formatedDate;
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<GradesDto> getGrades() {
        return grades;
    }

    public void setGrades(List<GradesDto> grades) {
        this.grades = grades;
    }

    public Boolean getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Boolean finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getFormatedDate() {
        return formatedDate;
    }

    public void setFormatedDate(String formatedDate) {
        this.formatedDate = formatedDate;
    }
    
    
    
}
