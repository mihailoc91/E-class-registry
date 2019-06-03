/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Grupa1
 */
@Entity(name = "communications")
public class MessageEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int communicationId;
    
    private String message;
    private LocalDateTime date;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = ParentEntity.class)
    @JoinColumn(name = "parent", nullable = false)
    private ParentEntity parent;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false,targetEntity = TeacherEntity.class)
    @JoinColumn(name = "teacher", nullable = false)
    private TeacherEntity teacher;
    
    private int senderStatus;

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
    public ParentEntity getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(ParentEntity parent) {
        this.parent = parent;
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
}
