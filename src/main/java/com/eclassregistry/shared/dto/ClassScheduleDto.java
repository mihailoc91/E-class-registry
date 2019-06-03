/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import com.eclassregistry.model.entity.ClassScheduleEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public class ClassScheduleDto implements Serializable {
    
    private int classScheduleId;
    private int dayOfTheWeek;
    private int subjectId;
    private String subjectName;
    private List<SubjectDto> listOfAllSubject;
    private int classId;
    private String className;
    private List<ClassDto> listOfAllClasses;
    private int classOrder;
    
    public ClassScheduleDto(){}
    
    public ClassScheduleDto(ClassScheduleEntity classScheduleEntity){
        this.classScheduleId = classScheduleEntity.getClassScheduleId();
        this.dayOfTheWeek = classScheduleEntity.getDayOfTheWeek();
        this.subjectId = classScheduleEntity.getSubject().getSubjectId();
        this.subjectName = classScheduleEntity.getSubject().getSubjectName();
        this.classId = classScheduleEntity.getClassEntity().getClassId();
        this.className = classScheduleEntity.getClassEntity().getClassName();
        this.classOrder = classScheduleEntity.getClassOrder();    
    }
    
    /**
     * @return the classScheduleId
     */
    public int getClassScheduleId(){
        return classScheduleId;
    }
    
    /**
     * @param classScheduleId the classScheduleId to set
     */
    public void setClassScheduleId(int classScheduleId) {
        this.classScheduleId = classScheduleId;
    }
    
    /**
     * @return the dayOfTheWeek
     */
    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }
    
    /**
     * @param dayOfTheWeek the dayOfTheWeek to set
     */
    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
    
    /**
     * @return the subjectId
     */
    public int getSubjectId(){
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
    public String getSubjectName(){
        return subjectName;
    }
    
    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    /**
     * @return the listOfAllSubject
     */
    public List<SubjectDto> getListOfAllSubject() {
        return listOfAllSubject;
    }

    /**
     * @param listOfAllSubject the listOfAllSubject to set
     */
    public void setListOfAllSubject(List<SubjectDto> listOfAllSubject) {
        this.listOfAllSubject = listOfAllSubject;
    }
    
    /**
     * @return the classId
     */
    public int getClassId(){
        return subjectId;
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
    public String getClassName(){
        return className;
    }
    
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
    /**
     * @return the listOfAllClasses
     */
    public List<ClassDto> getListOfAllClasses() {
        return listOfAllClasses;
    }

    /**
     * @param listOfAllClasses the listOfAllClasses to set
     */
    public void setListOfAllClasses(List<ClassDto> listOfAllClasses) {
        this.listOfAllClasses = listOfAllClasses;
    }
    
    /**
     * @return the classOrder
     */
    public int getClassOrder() {
        return classOrder;
    }
    
    /**
     * @param classOrder the classOrder to set
     */
    public void setClassOrder(int classOrder) {
        this.classOrder = classOrder;
    }
    
    
}
