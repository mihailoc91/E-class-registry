/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.shared.dto.OpenDoorRequestDto;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface OpenDoorRequestService {

    OpenDoorRequestDto getOpenDoorRequestByTeacher(int teacherId);

    void saveOpenDoorRequest(OpenDoorRequestDto openDoorRequestDto);

    List<OpenDoorRequestDto> getAllRequestSortedByDateForParent(int parentId, LocalDateTime currentDate);

    List[] getAllRequestSortedByDateForTeacher(int teacherId, LocalDateTime currentDate);

    void updateOpenDoorRequest(boolean answer, int openDoorId);
}
