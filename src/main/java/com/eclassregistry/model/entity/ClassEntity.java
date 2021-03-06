/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Grupa1
 */
@Entity(name = "classes")
public class ClassEntity implements Serializable{
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;
//    @NotEmpty
    private String className;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
//    @NotNull
    private TeacherEntity teacherEntity;
    
    @ManyToMany(mappedBy = "classes")
    private List<AnnouncementEntity> announcements;

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
     * @return the teacherEntity
     */
    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    /**
     * @param teacherEntity the teacherEntity to set
     */
    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    /**
     * @return the announcements
     */
    public List<AnnouncementEntity> getAnnouncements() {
        return announcements;
    }

    /**
     * @param announcements the announcements to set
     */
    public void setAnnouncements(List<AnnouncementEntity> announcements) {
        this.announcements = announcements;
    }
    
}
