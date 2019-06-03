/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Grupa1
 */

@Entity(name = "class_schedule")
public class ClassScheduleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int classScheduleId;
    
    private int dayOfTheWeek;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "subject")
    private SubjectEntity subject;
    
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_entity", nullable = false)
    private ClassEntity classEntity;
    
    private int classOrder;
    
    /**
     * @return the classScheduleId
     */
    public int getClassScheduleId() {
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
     * @return the subject
     */
    public SubjectEntity getSubject(){
        return subject;
    }
    
    /**
     * @param subject the subject to set
     */
    public void setSubject(SubjectEntity subject){
        this.subject = subject;
    }
    
    /**
     * @return the classEntity
     */
    public ClassEntity getClassEntity(){
        return classEntity;
    }
    
    /**
     * @param classEntity the classEntity to set
     */
    public void setClassEntity(ClassEntity classEntity){
        this.classEntity = classEntity;
    }
    
    /**
     * @return the classOrder
     */
    public int getClassOrder(){
        return classOrder;
    }
    
    /**
     * @param classOrder the classOrder to set
     */
    public void setClassOrder(int classOrder){
        this.classOrder = classOrder;
    }
    
    
}
