/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public class AnnouncementDto implements Serializable{
    
    private int announcementId;
    private String title;
    private String announcement;
    private LocalDateTime date;
    
    private List<Integer> classes;
    private ClassDto classEntity;
    
    private int classId;
    
    private int numberOfPages;
    
    private String formatedDate;
    
    private List<String> classesName;

    /**
     * @return the tittle
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param tittle the tittle to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the announcement
     */
    public String getAnnouncement() {
        return announcement;
    }

    /**
     * @param announcement the announcement to set
     */
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
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
     * @return the anouncementId
     */
    public int getAnnouncementId() {
        return announcementId;
    }

    /**
     * @param anouncementId the anouncementId to set
     */
    public void setAnnouncementId(int anouncementId) {
        this.announcementId = anouncementId;
    }

    /**
     * @return the classes
     */
    public List<Integer> getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(List<Integer> classes) {
        this.classes = classes;
    }

    public String getFormatedDate() {
        return formatedDate;
    }

    public void setFormatedDate(String formatedDate) {
        this.formatedDate = formatedDate;
    }

    public List<String> getClassesName() {
        return classesName;
    }

    public void setClassesName(List<String> classesName) {
        this.classesName = classesName;
    }
    
    
}
