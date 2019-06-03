/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.ClassEntity;
import com.eclassregistry.model.entity.ClassScheduleEntity;
import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.model.repositories.ClassScheduleRepository;
import com.eclassregistry.service.ClassScheduleService;
import com.eclassregistry.shared.dto.ClassScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grupa1
 */
//ssadsasdasdasdasdasdasdas

@Service
public class ClassScheduleServiceImpl implements ClassScheduleService{
    
    @Autowired
    ClassServiceImpl classServiceImpl;
    
    @Autowired
    SubjectServiceImpl subjectServiceImpl;
    
    @Autowired
    ClassScheduleRepository classScheduleRepository;
    
    @Override
    public void saveClassSchedule(ClassScheduleDto classScheduleDto) {
        
        ClassScheduleEntity classScheduleEntity = new ClassScheduleEntity();
        
        ClassEntity classEntity = classServiceImpl.findClassById(classScheduleDto.getClassId());
        SubjectEntity subjectEntity = subjectServiceImpl.getSubjectById(classScheduleDto.getSubjectId());
        
        classScheduleEntity.setClassEntity(classEntity);
        classScheduleEntity.setSubject(subjectEntity);
        classScheduleEntity.setClassOrder(classScheduleDto.getClassOrder());
        classScheduleEntity.setDayOfTheWeek(classScheduleDto.getDayOfTheWeek());
        
        classScheduleRepository.save(classScheduleEntity);
        
        
    }

    @Override
    public void deliteSubject(int subjectId, int dayOfTheWeek, int classOrder) {
        
    }

    
}
