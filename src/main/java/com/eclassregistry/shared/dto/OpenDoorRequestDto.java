/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Grupa1
 */

public class OpenDoorRequestDto implements Serializable{
    
    private int requestId;
    
    
    private OpenDoorDto openDoor;
    
    
    private UserDto parent;
    
    private LocalDateTime date;
    
    private boolean active;
    private boolean confirmed;
    
    private int openDoorId;
    private int parentId;
    
    private String chosenWeek;
    private String chosenTime;
    private int startWeek;
    
    private String firstNameAndLastName;
    private String formatedDate;
    private String formatedTime;
    private String statusOfRequest;

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
    public OpenDoorDto getOpenDoor() {
        return openDoor;
    }

    /**
     * @param openDoor the openDoor to set
     */
    public void setOpenDoor(OpenDoorDto openDoor) {
        this.openDoor = openDoor;
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
     * @return the chosenWeek
     */
    public String getChosenWeek() {
        return chosenWeek;
    }

    /**
     * @param chosenWeek the chosenWeek to set
     */
    public void setChosenWeek(String chosenWeek) {
        this.chosenWeek = chosenWeek;
    }

    /**
     * @return the chosenTime
     */
    public String getChosenTime() {
        return chosenTime;
    }

    /**
     * @param chosenTime the chosenTime to set
     */
    public void setChosenTime(String chosenTime) {
        this.chosenTime = chosenTime;
    }

    /**
     * @return the startWeek
     */
    public int getStartWeek() {
        return startWeek;
    }

    /**
     * @param startWeek the startWeek to set
     */
    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    /**
     * @return the firstNameAndLastName
     */
    public String getFirstNameAndLastName() {
        return firstNameAndLastName;
    }

    /**
     * @param firstNameAndLastName the firstNameAndLastName to set
     */
    public void setFirstNameAndLastName(String firstNameAndLastName) {
        this.firstNameAndLastName = firstNameAndLastName;
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

    /**
     * @return the formatedTime
     */
    public String getFormatedTime() {
        return formatedTime;
    }

    /**
     * @param formatedTime the formatedTime to set
     */
    public void setFormatedTime(String formatedTime) {
        this.formatedTime = formatedTime;
    }

    /**
     * @return the statusOfRequest
     */
    public String getStatusOfRequest() {
        return statusOfRequest;
    }

    /**
     * @param statusOfRequest the statusOfRequest to set
     */
    public void setStatusOfRequest(String statusOfRequest) {
        this.statusOfRequest = statusOfRequest;
    }

}
