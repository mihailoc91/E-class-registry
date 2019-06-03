/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Grupa1
 */
@Entity(name="grades")
public class GradesEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private int id;
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "teacher", nullable = false)
    private TeacherEntity teacher;
    
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "subject", nullable = false)
    private SubjectEntity subject;
    
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student", nullable = false)
    private StudentEntity student;
    
    @Column(name = "date")
    private LocalDateTime date;
    
    @Column(name = "grade")
    private int grade;
    
    @Column(name = "final")
    private Boolean finalGrade;

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

    public Boolean getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Boolean finalGrade) {
        this.finalGrade = finalGrade;
    }

    
    
        
}
