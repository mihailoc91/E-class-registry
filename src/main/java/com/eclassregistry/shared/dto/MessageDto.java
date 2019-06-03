/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Grupa1
 */
public class MessageDto {
    
    private int communicationId;
    private String message;
    private LocalDateTime date;
    private UserDto parent;
    private UserDto teacher;
    private int parentId;
    private int teacherId;
    private int senderStatus;
    private String formatedDate;
   
    /**
     * @return the communicationId
     */
    public int getCommunicationId() {
        return communicationId;
    }

    /**
     * @param communicationId the communicationId to set
     */
    public void setCommunicationId(int communicationId) {
        this.communicationId = communicationId;
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

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * @return the parent
     */
    public UserDto getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(UserDto parent) {
        this.parent = parent;
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
     * @return the parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
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
     * @return the senderStatus
     */
    public int getSenderStatus() {
        return senderStatus;
    }

    /**
     * @param senderStatus the senderStatus to set
     */
    public void setSenderStatus(int senderStatus) {
        this.senderStatus = senderStatus;
    }

    /**
     * @return the formatedDate
     */
    public String getFormatedDate() {
        return formatedDate;
    }

    /**
     * @param formatedDate the formatedDate to set
     */
    public void setFormatedDate(String formatedDate) {
        this.formatedDate = formatedDate;
    }
    
    
    
}
