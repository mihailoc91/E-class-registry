/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.OpenDoorEntity;
import com.eclassregistry.shared.dto.OpenDoorDto;

/**
 *
 * @author Grupa1
 */
public interface OpenDoorService {

    void saveOpenDoor(OpenDoorDto openDoorDto);

    OpenDoorDto getOpenDoorByTeacher(int teacherId);

    OpenDoorEntity getOpenDoor(int openDoorId);

    OpenDoorEntity getOpenDoorByTeacherId(int teacherId);
}
