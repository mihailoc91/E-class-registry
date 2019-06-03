/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.TeacherEntity;
import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */
@Repository
public interface TeacherRepository extends PagingAndSortingRepository<TeacherEntity, Integer> {
    
    TeacherEntity findByJmbg(UserEntity userEntity);
  /**
   * Finds all teachers that exists in database and don't have a class.
   * @return List filled with Object [] , toString of each member of Object [] is some value from the query 
   * ex: value of the teacher.id from first row is List.get(0).[0]
   */
    @Query(value="SELECT teachers.id, teachers.first_name, teachers.last_name, teachers.jmbg_jmbg FROM teachers LEFT JOIN classes ON teachers.id=classes.teacher_id WHERE classes.teacher_id IS NULL",nativeQuery = true)
    List<Object[]> findAllAvailableForClasses(); 
    
    
}
