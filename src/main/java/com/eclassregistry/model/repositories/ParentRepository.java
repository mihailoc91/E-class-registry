/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.ParentEntity;
import com.eclassregistry.model.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */
@Repository
public interface ParentRepository  extends JpaRepository<ParentEntity, Integer>{
    
    ParentEntity findByJmbg(UserEntity userEntity);
    
     /**
     * Finds all parents whose kids go to a class in which current teacher teach.
     *
     * @param teacherId Represents the current teacher id
     * @return Parents whose kids go to a class in which this teacher teach. List filled
     * with Object [] , toString of each member of Object [] is some value from
     * the query .
     */
    @Query(value = "select distinct parents.id , parents.first_name, parents.last_name from classes join students on classes.class_id=students.class join            students_parents on students.student_id = students_parents.student_id join parents on students_parents.parent_id= parents.id where classes.teacher_id=?",
           nativeQuery = true)
    List<Object[]> findAllForMessagesByTeacherId(int teacherId);
}
