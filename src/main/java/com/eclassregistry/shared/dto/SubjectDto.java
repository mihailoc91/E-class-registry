/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Grupa1
 */
public class SubjectDto implements Serializable {
    
    private int subjectId;
    @NotBlank
    private String subjectName;
    private Set<Integer> teachers;

    /**
     * @return the subjectId
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @return the teachers
     */
    public Set<Integer> getTeachers() {
        return teachers;
    }

    /**
     * @param teachers the teachers to set
     */
    public void setTeachers(Set<Integer> teachers) {
        this.teachers = teachers;
    }
    
}
