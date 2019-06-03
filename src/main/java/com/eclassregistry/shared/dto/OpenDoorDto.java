/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import java.io.Serializable;
import java.time.LocalTime;

/**
 *
 * @author Grupa1
 */
public class OpenDoorDto implements Serializable {
    
    private int openDoorId;
    private UserDto teacher;
    private int teacherId;
    private int dayOfTheWeek;
    private String startTime;
    private String endTime;
    private String message;

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
    public UserDto getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(UserDto teacher) {
        this.teacher = teacher;
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
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
