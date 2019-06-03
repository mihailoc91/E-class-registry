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

/**
 *
 * @author Grupa1
 */
@Entity(name = "open_door_requests")
public class OpenDoorRequestEntity  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false,targetEntity = OpenDoorEntity.class)
    @JoinColumn(name = "open_door", nullable = false)
    private OpenDoorEntity openDoor;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false,targetEntity = ParentEntity.class)
    @JoinColumn(name = "parent", nullable = false)
    private ParentEntity parent;
    
    private LocalDateTime date;
    
    private boolean active;
    private boolean confirmed;

    /**
     * @return the requestId
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the openDoor
     */
    public OpenDoorEntity getOpenDoor() {
        return openDoor;
    }

    /**
     * @param openDoor the openDoor to set
     */
    public void setOpenDoor(OpenDoorEntity openDoor) {
        this.openDoor = openDoor;
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
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the confirmed
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * @param confirmed the confirmed to set
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
