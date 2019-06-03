/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.DirectorEntity;
import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.model.repositories.TeacherRepository;
import com.eclassregistry.service.TeacherService;
import com.eclassregistry.shared.dto.SubjectDto;
import com.eclassregistry.shared.dto.UserDto;
import java.math.BigInteger;
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

/**
 *
 * @author Grupa1
 */

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    
    @Autowired
    SubjectServiceImpl subjectServiceImpl;
    
    @Autowired
    UserServiceImpl userServiceImpl;
    
   /**
     * Saves teacher in database,
     * if that teacher does not exists in databases then it creates a new one, 
     * if that teacher exists in database then it updates it.
     *
     * @param userDto Object of class UserDto filled with values that needs to
     * be stored in database
     * @param userEntity Object of class UserEntity filled with values from
     * database
     * @return Object of class UserDto filled with new stored data from database
     */
    @Override
    public UserDto saveTeacher(UserDto userDto, UserEntity userEntity) {

        TeacherEntity teacherEntity = new TeacherEntity();

        BeanUtils.copyProperties(userDto, teacherEntity);
        teacherEntity.setJmbg(userEntity);
        
        Set<SubjectEntity> subjectsEntitys = new HashSet<>();
        
        for(Integer subjectId : userDto.getSubjects()){
            SubjectEntity subjectEntity = subjectServiceImpl.getSubjectById(subjectId);
            
            subjectsEntitys.add(subjectEntity);
        }
        teacherEntity.setSubjects(subjectsEntitys);

        TeacherEntity storedTeacherEntity = teacherRepository.save(teacherEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedTeacherEntity, returnValue);
        returnValue.setEmail(storedTeacherEntity.getJmbg().getEmail());
        returnValue.setJmbg(storedTeacherEntity.getJmbg().getJmbg());
        returnValue.setPassword(storedTeacherEntity.getJmbg().getPassword());
        returnValue.setStatus(storedTeacherEntity.getJmbg().getStatus().getStatusId());
        returnValue.setStatusName(storedTeacherEntity.getJmbg().getStatus().getStatusName());

        return returnValue;
    }

    /**
     * Retrieves all teachers from database and put them in a List<UserDto>,
     * this method has paging.
     *
     * @param pageNumber int that represents the number of a page
     * @param membersNumber int that represents number of members that you want
     * to show (limit)
     * @return List of Objects of type UserDto filled with data from database
     */
    @Override
    public List<UserDto> getAllTeachers(int pageNumber, int membersNumber) {
        List<UserDto> allTeachers = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNumber, membersNumber);
        Page<TeacherEntity> teachersEntityList = teacherRepository.findAll(pageable);

        for (TeacherEntity teacherEntity : teachersEntityList) {
            UserDto userDto = new UserDto();

            BeanUtils.copyProperties(teacherEntity, userDto);
            userDto.setEmail(teacherEntity.getJmbg().getEmail());
            userDto.setJmbg(teacherEntity.getJmbg().getJmbg());
            userDto.setStatus(teacherEntity.getJmbg().getStatus().getStatusId());
            userDto.setStatusName(teacherEntity.getJmbg().getStatus().getStatusName());
            userDto.setNumberOfPages(teachersEntityList.getTotalPages());

            allTeachers.add(userDto);
        }

        return allTeachers;
    }

    /**
     * Retrieves teacher in database with that id.
     *
     * @param id int that represents id that is searched for in database
     * @return Object of type UserDto filled with values from database for that
     * administrator with searched id
     */
    @Override
    public UserDto getTeacherById(int id) {
          UserDto userDto = new UserDto();
      TeacherEntity teacherEntity = teacherRepository.findById(id).get();
       
        BeanUtils.copyProperties(teacherEntity, userDto);
        userDto.setEmail(teacherEntity.getJmbg().getEmail());
        userDto.setJmbg(teacherEntity.getJmbg().getJmbg());
        userDto.setStatus(teacherEntity.getJmbg().getStatus().getStatusId());
        userDto.setStatusName(teacherEntity.getJmbg().getStatus().getStatusName());
        
        Set<Integer> subjectsDto = new HashSet<>();
        //dodajem predmete koje ucitelj ima
        for(SubjectEntity subjectEntity : teacherEntity.getSubjects()){
          
            subjectsDto.add(subjectEntity.getSubjectId());
        }
        
        userDto.setSubjects(subjectsDto);
            
        
        
        return userDto;
    }

    /**
     * Deletes a teacher from database.
     * @param userEntity Object of type UserEntity that represents a unique value in database
     */
    @Override
    public void deleteTeacher(UserEntity userEntity) {
       TeacherEntity teacherEntity = teacherRepository.findByJmbg(userEntity);
        
        if(teacherEntity==null){
            throw new RuntimeException("Teacher with that id does not exists!");
        }
        
        teacherRepository.delete(teacherEntity);
    }


    /**
     * Retrieves all Teachers from database that don't have a class.
     * @return  List of Objects of type UserDto filled with presentational data about those teachers
     */
    @Override
    public List<UserDto> allAvailableTeachers() {
        List<UserDto> returnValue = new ArrayList<>();
        List<Object[]> listOfTeachersAsObjects = teacherRepository.findAllAvailableForClasses();

        for (int i = 0; i < listOfTeachersAsObjects.size(); i++) {
            
            Object[] arr = listOfTeachersAsObjects.get(i);
            
            UserDto userDto = new UserDto();
            
            userDto.setId((Integer) arr[0]);
            userDto.setFirstName((String) arr[1]);
            userDto.setLastName((String) arr[2]);
            userDto.setJmbg(((BigInteger)arr[3]).longValue());
            
            returnValue.add(userDto);
        }

        return returnValue;
    }

    /**
     * Retrieves a teacher from database with that id.
     * @param id int that represents ID from database
     * @return TeacherEntity.class filled with values from database
     */
    @Override
    public TeacherEntity getTeacher(int id) {
        TeacherEntity teacherEntity = teacherRepository.findById(id).get();
        
        return teacherEntity;
    }

 
   
    
}
