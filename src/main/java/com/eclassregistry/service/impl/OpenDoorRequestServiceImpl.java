/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.OpenDoorEntity;
import com.eclassregistry.model.entity.OpenDoorRequestEntity;
import com.eclassregistry.model.entity.ParentEntity;
import com.eclassregistry.model.repositories.OpenDoorRequestRepository;
import com.eclassregistry.service.OpenDoorRequestService;
import com.eclassregistry.shared.dto.OpenDoorDto;
import com.eclassregistry.shared.dto.OpenDoorRequestDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grupa1
 */
@Service
public class OpenDoorRequestServiceImpl implements OpenDoorRequestService {

    @Autowired
    OpenDoorServiceImpl openDoorServiceImpl;

    @Autowired
    OpenDoorRequestRepository openDoorRequestRepository;

    @Autowired
    ParentServiceImpl parentServiceImpl;

    String[] statusOfRequest = {"Confirmed", "Pending", "Rejected"};
    String[] daysOfTheWeek = {"Mondays", "Tuesdays", "Wednesdays", "Thursdays", "Fridays"};

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Retrieve open door request from database.
     *
     * @param teacherId int that represents unique id of teacher in database
     * @return OpenDoorRequestDto.class filled with data from database if the
     * Open Door exists in database for that teacher if not then it returns null
     */
    @Override
    public OpenDoorRequestDto getOpenDoorRequestByTeacher(int teacherId) {
        OpenDoorDto openDoorDto = openDoorServiceImpl.getOpenDoorByTeacher(teacherId);

        if (openDoorDto != null) {
            OpenDoorRequestDto openDoorRequestDto = new OpenDoorRequestDto();
            openDoorRequestDto.setOpenDoor(openDoorDto);

            return openDoorRequestDto;
        }

        return null;
    }

    /**
     * Saves open door request.
     *
     * @param openDoorRequestDto OpenDoorRequestDto.class filled with data
     * gained through UI
     */
    @Override
    public void saveOpenDoorRequest(OpenDoorRequestDto openDoorRequestDto) {
        OpenDoorRequestEntity openDoorRequestEntity = new OpenDoorRequestEntity();

        ParentEntity parentEntity = parentServiceImpl.findParentById(openDoorRequestDto.getParentId());

        OpenDoorEntity openDoorEntity = openDoorServiceImpl.getOpenDoor(openDoorRequestDto.getOpenDoorId());

        String date = openDoorRequestDto.getChosenWeek() + "-" + openDoorEntity.getDayOfTheWeek();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_WEEK_DATE;
        LocalDate localDate = LocalDate.parse(date, dtf);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.parse(openDoorRequestDto.getChosenTime()));

        openDoorRequestEntity.setRequestId(openDoorRequestDto.getRequestId());
        openDoorRequestEntity.setParent(parentEntity);
        openDoorRequestEntity.setOpenDoor(openDoorEntity);
        openDoorRequestEntity.setDate(localDateTime);
        openDoorRequestEntity.setActive(true);
        openDoorRequestEntity.setConfirmed(false);

        openDoorRequestRepository.save(openDoorRequestEntity);

    }

    /**
     * Retrieves all requests that are up coming for that parent.
     *
     * @param parentId int that represents unique id in database for that parent
     * @param currentDate LocalDateTime object that represents current date
     * @return List<OpenDoorRequestDto> filled with all requests made by that
     * parent
     */
    @Override
    public List<OpenDoorRequestDto> getAllRequestSortedByDateForParent(int parentId, LocalDateTime currentDate) {
        List<OpenDoorRequestDto> allRequests = new ArrayList<>();

        ParentEntity parentEntity = parentServiceImpl.findParentById(parentId);

        List<OpenDoorRequestEntity> allRequestsEntities = openDoorRequestRepository.findAllByParentAndDateGreaterThanOrderByDateAsc(parentEntity, currentDate);

        for (OpenDoorRequestEntity entity : allRequestsEntities) {
            OpenDoorRequestDto openDoorRequestDto = new OpenDoorRequestDto();

            openDoorRequestDto.setRequestId(entity.getRequestId());
            openDoorRequestDto.setFirstNameAndLastName((entity.getOpenDoor().getTeacher().getFirstName() + " " + entity.getOpenDoor().getTeacher().getLastName()));
            openDoorRequestDto.setFormatedDate(entity.getDate().toLocalDate().format(dateFormatter));
            openDoorRequestDto.setFormatedTime(entity.getDate().toLocalTime().format(timeFormatter));

            if (!entity.isActive()) {
                if (entity.isConfirmed()) {
                    openDoorRequestDto.setStatusOfRequest(statusOfRequest[0]);
                } else {
                    openDoorRequestDto.setStatusOfRequest(statusOfRequest[2]);
                }
            } else {
                openDoorRequestDto.setStatusOfRequest(statusOfRequest[1]);
            }

            allRequests.add(openDoorRequestDto);
        }

        return allRequests;
    }

    /**
     * Retrieves all requests that are up coming for that teacher.
     *
     * @param teacherId int that represents unique id in database for that
     * teacher
     * @param currentDate LocalDateTime object that represents current date
     * @return List<OpenDoorRequestDto> filled with all requests for that
     * teacher
     */
    @Override
    public List[] getAllRequestSortedByDateForTeacher(int teacherId, LocalDateTime currentDate) {
        List[] listArr = new List[2];
        OpenDoorEntity openDoorEntity = openDoorServiceImpl.getOpenDoorByTeacherId(teacherId);

        List<OpenDoorRequestEntity> allRequestEntities = openDoorRequestRepository.findAllByOpenDoorAndDateGreaterThanOrderByDateAsc(openDoorEntity, currentDate);
        List<OpenDoorRequestDto> listOfActiveRequests = new ArrayList<>();
        List<OpenDoorRequestDto> listOfRespondedRequests = new ArrayList<>();
        for (OpenDoorRequestEntity entity : allRequestEntities) {

            OpenDoorRequestDto openDoorRequestDto = new OpenDoorRequestDto();

            openDoorRequestDto.setRequestId(entity.getRequestId());
            openDoorRequestDto.setOpenDoorId(entity.getOpenDoor().getOpenDoorId());
            openDoorRequestDto.setParentId(entity.getParent().getId());
            openDoorRequestDto.setDate(entity.getDate());
            openDoorRequestDto.setActive(entity.isActive());
            openDoorRequestDto.setConfirmed(entity.isConfirmed());

            openDoorRequestDto.setFormatedDate(entity.getDate().toLocalDate().format(dateFormatter));
            openDoorRequestDto.setFormatedTime(entity.getDate().toLocalTime().format(timeFormatter));
            openDoorRequestDto.setFirstNameAndLastName((entity.getParent().getFirstName() + " " + entity.getParent().getLastName()));

            if (entity.isActive()) {
                listOfActiveRequests.add(openDoorRequestDto);
            } else {
                if (entity.isConfirmed()) {
                    openDoorRequestDto.setStatusOfRequest(statusOfRequest[0]);
                } else {
                    openDoorRequestDto.setStatusOfRequest(statusOfRequest[2]);
                }
                listOfRespondedRequests.add(openDoorRequestDto);
            }
        }

        listArr[0] = listOfRespondedRequests;
        listArr[1] = listOfActiveRequests;
        return listArr;
    }

    /**
     * Saves a response for an open door visit, teacher can accept or reject the
     * request. When the teacher answer the request is considered as not active.
     *
     * @param answer boolean response to a request
     * @param openDoorId int that represents unique id in database for that open
     * door request
     */
    @Override
    public void updateOpenDoorRequest(boolean answer, int openDoorId) {
        OpenDoorRequestEntity openDoorRequestEntity = openDoorRequestRepository.findByRequestId(openDoorId);

        openDoorRequestEntity.setActive(false);
        openDoorRequestEntity.setConfirmed(answer);

        openDoorRequestRepository.save(openDoorRequestEntity);
    }

}
