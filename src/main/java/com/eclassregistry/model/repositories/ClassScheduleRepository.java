/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.ClassEntity;
import com.eclassregistry.model.entity.ClassScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */

@Repository
public interface ClassScheduleRepository extends JpaRepository<ClassScheduleEntity, Integer>{
    
}
