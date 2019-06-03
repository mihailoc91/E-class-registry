/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Grupa1
 */

@Entity(name = "students")
public class StudentEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
//    @NotEmpty
    private String firstName;
//    @NotEmpty
    private String lastName;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "class", nullable = false)
//    @NotNull
    private ClassEntity classEntity;
    
    @ManyToMany
       @JoinTable(name = "students_parents",
               joinColumns = @JoinColumn (name="student_id"),
               inverseJoinColumns = @JoinColumn(name = "parent_id"))
//    @NotEmpty
    private List<ParentEntity> parents;

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
     * @return the classEntity
     */
    public ClassEntity getClassEntity() {
        return classEntity;
    }

    /**
     * @param classEntity the classEntity to set
     */
    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    /**
     * @return the parents
     */
    public List<ParentEntity> getParents() {
        return parents;
    }

    /**
     * @param parents the parents to set
     */
    public void setParents(List<ParentEntity> parents) {
        this.parents = parents;
    }
}
