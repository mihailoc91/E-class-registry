/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import com.eclassregistry.model.repositories.SubjectRepository;
import com.eclassregistry.service.SubjectService;
import com.eclassregistry.shared.dto.SubjectDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Grupa1
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    /**
     * Saves a subject into database, if that subject doesn't exists in database
     * then it create a new one, if it does then it updates it with new data.
     *
     * @param subjectDto Object of class SubjectDto filled with data that needs
     * to be saved in database
     * @return Object of class SubjectDto with stored data from database
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SubjectDto save(SubjectDto subjectDto) {
        SubjectEntity subjectEntity = new SubjectEntity();
        BeanUtils.copyProperties(subjectDto, subjectEntity);

        Set<TeacherEntity> teachers = new HashSet<>();
        //Takes all id's of teachers from subjectDto, retrives them from database and store them into Set<TeacherEntity>
        for (Integer teacherId : subjectDto.getTeachers()) {
            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity = teacherServiceImpl.getTeacher(teacherId);

            teachers.add(teacherEntity);
        }
        subjectEntity.setTeachers(teachers);

        SubjectEntity returnEntity = subjectRepository.save(subjectEntity);

        SubjectDto returnValue = new SubjectDto();
        BeanUtils.copyProperties(returnEntity, returnValue);
        //sets the same teachers from subjectDto so I dont convert them again
        returnValue.setTeachers(subjectDto.getTeachers());

        return returnValue;
    }

    /**
     * Retrieves all subjects in a List<SubjectDto> from database depending on
     * page number and number of rows per page. Does not have Set<Integer> of
     * teachers id's.
     *
     * @param pageNumber int that represents the desired page
     * @param membersNumber int that represents number of rows per page
     * @return List<SubjectDto> filled with data from database
     */
    @Override
    public List<SubjectDto> getAllSubjects(int pageNumber, int membersNumber) {
        List<SubjectDto> allSubjects = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNumber, membersNumber);
        Page<SubjectEntity> allSubjectsEntity = subjectRepository.findAll(pageable);

        for (SubjectEntity subject : allSubjectsEntity) {
            SubjectDto subjectDto = new SubjectDto();
            BeanUtils.copyProperties(subject, subjectDto);

            allSubjects.add(subjectDto);
        }

        return allSubjects;
    }

    /**
     * Retrieves a subject with that id from database.
     *
     * @param id int that represent id in database
     * @return Object of class SubjectDto filled with data from database
     */
    @Override
    public SubjectDto getSubject(int id) {
        SubjectEntity subjectEntity = subjectRepository.getOne(id);

        SubjectDto returnValue = new SubjectDto();
        BeanUtils.copyProperties(subjectEntity, returnValue);

        return returnValue;
    }

    /**
     * Deletes a subject with that id.
     *
     * @param id int that represents id of the subject that needs to be deleted
     * from the database.
     */
    @Override
    public void deleteSubject(int id) {
        subjectRepository.deleteById(id);
    }

    /**
     * Returns a SubjectEntity with that id.
     *
     * @param id int that represents Id of subject in database
     * @return SubjectEntity
     */
    @Override
    public SubjectEntity getSubjectById(int id) {
        SubjectEntity subjectEntity = subjectRepository.findById(id).get();
        return subjectEntity;
    }

    /**
     * Returns all subjects from database, with Set<Integer> teachers that has
     * teachers id's for a current subject.
     *
     * @return List<SubjectDto> filled with data for all subjects from database
     */
    @Override
    public List<SubjectDto> getAllSubjects() {
        List<SubjectDto> allSubjectsDto = new ArrayList<>();

        List<SubjectEntity> allSubjectsEntitys = subjectRepository.findAll();
        //populate SubjectDto with data from database
        for (SubjectEntity subjectEntity : allSubjectsEntitys) {
            SubjectDto subjectDto = new SubjectDto();
            Set<Integer> teachers = new HashSet<>();

            BeanUtils.copyProperties(subjectEntity, subjectDto);
            //populate SubjectDto.teachers with Id's of the teachers that have current subject 
            for (TeacherEntity teacherEntity : subjectEntity.getTeachers()) {
                teachers.add(teacherEntity.getId());
            }
            subjectDto.setTeachers(teachers);

            allSubjectsDto.add(subjectDto);
        }

        return allSubjectsDto;
    }

    /**
     * Retrieves all subjects that a selected teacher has.
     *
     * @param Id int that represents unique id in database for selected teacher
     * @return List<SubjectDto> filled with data from database
     */
    @Override
    public List<SubjectDto> getAllSubjectsFromTeacherId(int Id) {

        List<SubjectDto> allSubjects = new ArrayList<>();

        List<Integer> list = subjectRepository.findAllSubjectFromteacherId(Id);

        for (int i = 0; i < list.size(); i++) {

            SubjectDto subjectDto = new SubjectDto();

            SubjectEntity subjectEntity = getSubjectById(list.get(i));

            BeanUtils.copyProperties(subjectEntity, subjectDto);

            allSubjects.add(subjectDto);
        }
        return allSubjects;
    }

}
