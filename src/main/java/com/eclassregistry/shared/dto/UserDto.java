/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.shared.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mihailo
 */
@Component
public class UserDto implements Serializable {
    
   
    private int id;
   
    @Min(1)
    private long jmbg;
    @Email()
    private String email;
    @Size (min = 6, max = 60)
    private String password;

    @Pattern(regexp = "[0-9]+")
    private int status;
    private String statusName;
//    @Size(min = 3, max = 60)
    @Size(min = 3, max = 60)
    private String firstName;
    @Size(min = 3, max = 60)
    private String lastName;
    private int numberOfPages;
    private Set<Integer> subjects;
    private List<Integer> kids;

    public UserDto(){}
    public UserDto(int id,String firstName,String lastName,long jmbg){
    this.id =id;
    this.firstName=firstName;
    this.lastName=lastName;
    this.jmbg=jmbg;
    }
    /**
     * @return the Jmbg
     */
    public long getJmbg() {
        return jmbg;
    }

    /**
     * @param Jmbg the Jmbg to set
     */
    public void setJmbg(long Jmbg) {
        this.jmbg = Jmbg;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the statusName
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * @param statusName the statusName to set
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
     * @return the subjects
     */
    public Set<Integer> getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(Set<Integer> subjects) {
        this.subjects = subjects;
    }

    /**
     * @return the kids
     */
    public List<Integer> getKids() {
        return kids;
    }

    /**
     * @param students the kids to set
     */
    public void setKids(List<Integer> students) {
        this.kids = students;
    }
}
