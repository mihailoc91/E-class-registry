/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.OpenDoorEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */
@Repository
public interface OpenDoorRepository extends JpaRepository<OpenDoorEntity, Integer>{

    Optional<OpenDoorEntity> findByTeacher(TeacherEntity teacher);
}
