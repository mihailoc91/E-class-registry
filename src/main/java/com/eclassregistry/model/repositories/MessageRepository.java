/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.MessageEntity;
import com.eclassregistry.model.entity.ParentEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */
@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    MessageEntity findFirstByTeacherAndParentOrderByDateDesc(TeacherEntity teacherEntity, ParentEntity parentEntity);
    List<MessageEntity> findAllByTeacherAndParent(TeacherEntity teacherEntity, ParentEntity parentEntity);
}
