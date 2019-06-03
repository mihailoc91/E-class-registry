/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.shared.dto.ClassScheduleDto;
import com.eclassregistry.shared.dto.SubjectDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface ClassScheduleService {
    
    void saveClassSchedule(ClassScheduleDto classScheduleDto);
    void deliteSubject(int subjectId, int dayOfTheWeek, int classOrder);
}
