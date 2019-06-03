/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.entity;

import java.io.Serializable;
import java.time.LocalTime;
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
@Entity(name="open_doors")
public class OpenDoorEntity implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int openDoorId;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false,targetEntity = TeacherEntity.class)
    @JoinColumn(name = "teacher", nullable = false)
    private TeacherEntity teacher;
    @Column(name = "day")
    private int dayOfTheWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * @return the openDoorId
     */
    public int getOpenDoorId() {
        return openDoorId;
    }

    /**
     * @param openDoorId the openDoorId to set
     */
    public void setOpenDoorId(int openDoorId) {
        this.openDoorId = openDoorId;
    }

    /**
     * @return the teacher
     */
    public TeacherEntity getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
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
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
