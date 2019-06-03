/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import com.eclassregistry.model.entity.ClassEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public class ClassDto implements Serializable {
    
    private int classId;
    private String className;
    private int teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private List<UserDto> listOfAvailableTeachers;
    private int numberOfPages;
    
    public ClassDto(){}
    
    public ClassDto(ClassEntity classEntity){
        this.classId=classEntity.getClassId();
        this.className=classEntity.getClassName();
        this.teacherId=classEntity.getTeacherEntity().getId();
        this.teacherFirstName=classEntity.getTeacherEntity().getFirstName();
        this.teacherLastName=classEntity.getTeacherEntity().getLastName();
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
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the teacherId
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return the listOfAvailableTeachers
     */
    public List<UserDto> getListOfAvailableTeachers() {
        return listOfAvailableTeachers;
    }

    /**
     * @param listOfAvailableTeachers the listOfAvailableTeachers to set
     */
    public void setListOfAvailableTeachers(List<UserDto> listOfAvailableTeachers) {
        this.listOfAvailableTeachers = listOfAvailableTeachers;
    }

    /**
     * @return the teacherFirstName
     */
    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    /**
     * @param teacherFirstName the teacherFirstName to set
     */
    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    /**
     * @return the teacherLastName
     */
    public String getTeacherLastName() {
        return teacherLastName;
    }

    /**
     * @param teacherLastName the teacherLastName to set
     */
    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
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
    
}
