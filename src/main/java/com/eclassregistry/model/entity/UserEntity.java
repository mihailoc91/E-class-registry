/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Mihailo
 */

@Entity(name = "users")
public class UserEntity implements Serializable {
    
    @Id
    
    private long jmbg;
//    @Email
    private String email;
//    @NotEmpty
    private String password;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @NotNull
    private StatusEntity status;

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
     * @return the role
     */
    public StatusEntity getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
