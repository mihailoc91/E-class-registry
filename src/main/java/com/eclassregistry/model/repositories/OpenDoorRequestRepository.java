/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.OpenDoorEntity;
import com.eclassregistry.model.entity.OpenDoorRequestEntity;
import com.eclassregistry.model.entity.ParentEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */
@Repository
public interface OpenDoorRequestRepository extends JpaRepository<OpenDoorRequestEntity, Integer>{
    List<OpenDoorRequestEntity> findAllByParentAndDateGreaterThanOrderByDateAsc(ParentEntity parent,LocalDateTime date);
    List<OpenDoorRequestEntity> findAllByOpenDoorAndDateGreaterThanOrderByDateAsc(OpenDoorEntity openDoor,LocalDateTime date);
    OpenDoorRequestEntity findByRequestId (int requestId);
}
