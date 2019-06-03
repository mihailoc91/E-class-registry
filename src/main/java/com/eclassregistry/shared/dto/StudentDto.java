/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Grupa1
 */

public class StudentDto implements Serializable {
    
    private int studentId;
    private String firstName;
    private String lastName;
    
    private ClassDto classEntity;
    
    private int classId;
    
    private List<Integer> parents;
    
    private List<UserDto> parentsDto;
    
    private int numberOfPages;


    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the classEntity
     */
    public ClassDto getClassEntity() {
        return classEntity;
    }

    /**
     * @param classEntity the classEntity to set
     */
    public void setClassEntity(ClassDto classEntity) {
        this.classEntity = classEntity;
    }

    /**
     * @return the classId
     */
    public int getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * @return the parents
     */
    public List<Integer> getParents() {
        return parents;
    }

    /**
     * @param parents the parents to set
     */
    public void setParents(List<Integer> parents) {
        this.parents = parents;
    }

    /**
     * @return the numberOfPages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * @return the parentsDto
     */
    public List<UserDto> getParentsDto() {
        return parentsDto;
    }

    /**
     * @param parentsDto the parentsDto to set
     */
    public void setParentsDto(List<UserDto> parentsDto) {
        this.parentsDto = parentsDto;
    }


    
}
