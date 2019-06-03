/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.OpenDoorEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import com.eclassregistry.model.repositories.OpenDoorRepository;
import com.eclassregistry.service.OpenDoorService;
import com.eclassregistry.shared.dto.OpenDoorDto;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grupa1
 */
@Service
public class OpenDoorServiceImpl implements OpenDoorService {

    @Autowired
    OpenDoorRepository openDoorRepository;

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Saves an open door with data gained through UI.
     *
     * @param openDoorDto OpenDoor.class carry data from UI.
     */
    @Override
    public void saveOpenDoor(OpenDoorDto openDoorDto) {
        OpenDoorEntity openDoorEntity = new OpenDoorEntity();

        BeanUtils.copyProperties(openDoorDto, openDoorEntity);
        openDoorEntity.setStartTime(LocalTime.parse(openDoorDto.getStartTime(), timeFormatter));
        openDoorEntity.setEndTime(LocalTime.parse(openDoorDto.getEndTime(), timeFormatter));
        openDoorEntity.setTeacher(teacherServiceImpl.getTeacher(openDoorDto.getTeacherId()));

        openDoorRepository.save(openDoorEntity);

    }

    /**
     * Retrieves open door filled with data from database for selected teacher.
     *
     * @param teacherId int that represents unique id in database for selected
     * teacher
     * @return OpenDoorDto.class filled with data from database if they exists,
     * if not returns null
     */
    @Override
    public OpenDoorDto getOpenDoorByTeacher(int teacherId) {
        if (teacherId == 0) {
            return null;
        }
        TeacherEntity teacherEntity = teacherServiceImpl.getTeacher(teacherId);
        Optional<OpenDoorEntity> openDoorEntity = openDoorRepository.findByTeacher(teacherEntity);
        OpenDoorDto openDoorDto = new OpenDoorDto();
        if (openDoorEntity.isPresent()) {
            BeanUtils.copyProperties(openDoorEntity.get(), openDoorDto);
            openDoorDto.setTeacherId(teacherEntity.getId());
            openDoorDto.setStartTime(openDoorEntity.get().getStartTime().toString());
            openDoorDto.setEndTime(openDoorEntity.get().getEndTime().toString());
            return openDoorDto;
        }
        return null;
    }

    /**
     * Retrieves open door from database.
     *
     * @param openDoorId int that represents unique id in database for selected
     * open door
     * @return OpenDoorEntity.class filled with data from database
     */
    @Override
    public OpenDoorEntity getOpenDoor(int openDoorId) {

        Optional<OpenDoorEntity> openDoorEntity = openDoorRepository.findById(openDoorId);

        return openDoorEntity.get();
    }

    /**
     * Retrieves open door from database.
     *
     * @param teacherId int that represents unique id in database for selected
     * teacher
     * @return OpenDoorEntity.class filled with data from database if they
     * exists, if not then it returns null
     */
    @Override
    public OpenDoorEntity getOpenDoorByTeacherId(int teacherId) {
        TeacherEntity teacherEntity = teacherServiceImpl.getTeacher(teacherId);
        Optional<OpenDoorEntity> openDoorEntity = openDoorRepository.findByTeacher(teacherEntity);

        if (openDoorEntity.isPresent()) {

            return openDoorEntity.get();
        }
        return null;
    }

}
