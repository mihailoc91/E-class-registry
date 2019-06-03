
package com.eclassregistry.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "announcements") 
public class AnnouncementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int announcementId;
    
    private String title;
    private String announcement;
    private LocalDateTime date;
    
        @ManyToMany
       @JoinTable(name = "announcements_classes",
               joinColumns = @JoinColumn (name="announcement_id"),
               inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<ClassEntity> classes;
    
    
    /**
     * @return the announcement_id
     */
    public int getAnnouncementId() {
        return announcementId;
    }

    /**
     * @param announcement_id the announcement_id to set
     */
    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

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
     * @return the classes
     */
    public List<ClassEntity> getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }

    
    
}
