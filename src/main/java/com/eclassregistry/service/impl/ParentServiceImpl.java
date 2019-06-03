/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.ParentEntity;
import com.eclassregistry.model.entity.StudentEntity;
import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.model.repositories.ParentRepository;
import com.eclassregistry.service.ParentService;
import com.eclassregistry.shared.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
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
public class ParentServiceImpl implements ParentService {

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    StudentServiceImpl studentServiceImpl;

    /**
     * Saves parent in database, if that parent does not exists in databases
     * then it creates a new one, if that parent exists in database then it
     * updates it.
     *
     * @param userDto Object of class UserDto filled with values that needs to
     * be stored in database
     * @param userEntity Object of class UserEntity filled with values from
     * database
     * @return Object of class UserDto filled with new stored data from database
     */
    @Override
    public UserDto saveParent(UserDto userDto, UserEntity userEntity) {

        ParentEntity parentEntity = new ParentEntity();

        BeanUtils.copyProperties(userDto, parentEntity);
        parentEntity.setJmbg(userEntity);

        List<StudentEntity> kidsEntitys = new ArrayList<>();

        for (Integer kidId : userDto.getKids()) {
            StudentEntity studentEntity = studentServiceImpl.findStudentById(kidId);

            kidsEntitys.add(studentEntity);
        }
        parentEntity.setKids(kidsEntitys);

        ParentEntity storedParentEntity = parentRepository.save(parentEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedParentEntity, returnValue);
        returnValue.setEmail(storedParentEntity.getJmbg().getEmail());
        returnValue.setJmbg(storedParentEntity.getJmbg().getJmbg());
        returnValue.setPassword(storedParentEntity.getJmbg().getPassword());
        returnValue.setStatus(storedParentEntity.getJmbg().getStatus().getStatusId());
        returnValue.setStatusName(storedParentEntity.getJmbg().getStatus().getStatusName());

        return returnValue;
    }

    /**
     * Retrieves all parents from database and put them in a List<UserDto>, this
     * method has paging.
     *
     * @param pageNumber int that represents the number of a page
     * @param membersNumber int that represents number of members that you want
     * to show (limit)
     * @return List of Objects of type UserDto filled with data from database
     */
    @Override
    public List<UserDto> getAllParents(int pageNumber, int membersNumber) {
        List<UserDto> allParents = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNumber, membersNumber);
        Page<ParentEntity> parentsEntityList = parentRepository.findAll(pageable);

        for (ParentEntity parentEntity : parentsEntityList) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(parentEntity, userDto);
            userDto.setEmail(parentEntity.getJmbg().getEmail());
            userDto.setJmbg(parentEntity.getJmbg().getJmbg());
            userDto.setStatus(parentEntity.getJmbg().getStatus().getStatusId());
            userDto.setStatusName(parentEntity.getJmbg().getStatus().getStatusName());
            userDto.setNumberOfPages(parentsEntityList.getTotalPages());
            allParents.add(userDto);
        }

        return allParents;
    }

    /**
     * Retrieves parent in database with that id.
     *
     * @param id int that represents id that is searched for in database
     * @return Object of type UserDto filled with values from database for that
     * administrator with searched id
     */
    @Override
    public UserDto getParentById(int id) {
        UserDto userDto = new UserDto();
        ParentEntity parentEntity = parentRepository.findById(id).get();

        BeanUtils.copyProperties(parentEntity, userDto);
        userDto.setEmail(parentEntity.getJmbg().getEmail());
        userDto.setJmbg(parentEntity.getJmbg().getJmbg());
        userDto.setStatus(parentEntity.getJmbg().getStatus().getStatusId());
        userDto.setStatusName(parentEntity.getJmbg().getStatus().getStatusName());

        List<Integer> kidsDto = new ArrayList<>();
        //adding kids of this parent
        for (StudentEntity studentEntity : parentEntity.getKids()) {

            kidsDto.add(studentEntity.getStudentId());
        }
        userDto.setKids(kidsDto);

        return userDto;
    }

    /**
     * Deletes a parent from database.
     *
     * @param userEntity Object of type UserEntity that represents a unique
     * value in database
     */
    @Override
    public void deleteParent(UserEntity userEntity) {
        ParentEntity parentEntity = parentRepository.findByJmbg(userEntity);

        if (parentEntity == null) {
            throw new RuntimeException("Parent with that id does not exists!");
        }

        parentRepository.delete(parentEntity);
    }

    /**
     * Retrieves parent from database.
     *
     * @param id int that represents Id in database
     * @return ParentEntity.class filled with data from database
     */
    @Override
    public ParentEntity findParentById(int id) {
        ParentEntity parentEntity = parentRepository.findById(id).get();

        return parentEntity;
    }

    /**
     * Retrieves all parents from database and put them in a List<UserDto>.
     *
     * @return List of Objects of type UserDto filled with data from database
     */
    @Override
    public List<UserDto> getAllParents() {
        List<UserDto> allParents = new ArrayList<>();

        List<ParentEntity> parentsEntityList = parentRepository.findAll();

        for (ParentEntity parentEntity : parentsEntityList) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(parentEntity, userDto);
            userDto.setEmail(parentEntity.getJmbg().getEmail());
            userDto.setJmbg(parentEntity.getJmbg().getJmbg());
            userDto.setStatus(parentEntity.getJmbg().getStatus().getStatusId());
            userDto.setStatusName(parentEntity.getJmbg().getStatus().getStatusName());

            allParents.add(userDto);
        }

        return allParents;
    }

    /**
     * Retrieves parent from database.
     *
     * @param userEntity object of UserEntity.class that represents jmbg from
     * database
     * @return ParentEntity.class filled with data from database
     */
    @Override
    public UserDto getParentByJmbg(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        ParentEntity parentEntity = parentRepository.findByJmbg(userEntity);

        BeanUtils.copyProperties(parentEntity, userDto);
        userDto.setEmail(parentEntity.getJmbg().getEmail());
        userDto.setJmbg(parentEntity.getJmbg().getJmbg());
        userDto.setStatus(parentEntity.getJmbg().getStatus().getStatusId());
        userDto.setStatusName(parentEntity.getJmbg().getStatus().getStatusName());

        List<Integer> kidsDto = new ArrayList<>();
        //adding kids of this parent
        for (StudentEntity studentEntity : parentEntity.getKids()) {

            kidsDto.add(studentEntity.getStudentId());
        }
        userDto.setKids(kidsDto);

        return userDto;
    }

    /**
     * Finds all parents of students who are in class in which current teacher
     * teach.
     *
     * @param teacherId int that represents id of a Teacher
     * @return List<UserDto> filled with data about parents of kids that are in
     * teacher class
     */
    @Override
    public List<UserDto> getParentsForTeacher(int teacherId) {
        List<UserDto> returnValue = new ArrayList<>();
        List<Object[]> listOfParentsAsObjects = parentRepository.findAllForMessagesByTeacherId(teacherId);

        for (int i = 0; i < listOfParentsAsObjects.size(); i++) {

            Object[] arr = listOfParentsAsObjects.get(i);

            UserDto userDto = new UserDto();

            userDto.setId((Integer) arr[0]);
            userDto.setFirstName((String) arr[1]);
            userDto.setLastName((String) arr[2]);

            returnValue.add(userDto);
        }

        return returnValue;
    }

}
