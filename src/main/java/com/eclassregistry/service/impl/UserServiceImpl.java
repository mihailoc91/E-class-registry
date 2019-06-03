/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.model.repositories.StatusRepository;
import com.eclassregistry.model.repositories.UserRepository;
import com.eclassregistry.service.UserService;
import com.eclassregistry.shared.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mihailo
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    StatusRepository statusRepository;
    
    @Autowired
    AdministratorServiceImpl administratorServiceImpl;
    
    @Autowired
    DirectorServiceImpl directorServiceImpl;
    
    @Autowired
    TeacherServiceImpl teacherServiceImpl;
    
    @Autowired
    ParentServiceImpl parentServiceImpl;
    
    
    /**
     * Creates a new user in database with data received from UI trough UserDto
     * object. First it creates and saves a UserEntity and after that it creates
     * and saves in regard to value of attribute status of UserEntity
     * (AdministratorEntity, DirectorEntity, TeacherEntity, ParentEntity). If
     * all pass well the entry is committed if there is an exception then the
     * entry is rolled backed.
     *
     * @param user Object of type UserDto filled with values that needs to be
     * stored in database
     * @return Object of type UserDto filled with stored values in database
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public UserDto createUser(UserDto user) {

        if (userRepository.findByJmbg(user.getJmbg()) != null) {
            throw new RuntimeException("Record already exists!");
        }

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(user, userEntity);
        userEntity.setStatus(statusRepository.findById(user.getStatus()).get());

        UserEntity storedUser = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();

        switch (storedUser.getStatus().getStatusId()) {
            case 1:
                returnValue = administratorServiceImpl.saveAdministrator(user, userEntity);
                break;
            case 2:
                returnValue = directorServiceImpl.saveDirector(user, userEntity);
                break;
            case 3:
                returnValue = teacherServiceImpl.saveTeacher(user, userEntity);
                break;
            case 4:
                returnValue = parentServiceImpl.saveParent(user, userEntity);
                break;
            default:
                throw new RuntimeException("That role do not exists!");
        }

        return returnValue;
    }

    /**
     * Retrieves a list of all users from database, depending of their status (1
     * for Administrators, 2 for Director, 3 for Teachers, 4 for Parents). This
     * list has paging system implemented.
     *
     * @param status int that represents Status of users that you want
     * @param pageNumber int that represents the number of a page
     * @param membersNumber int that represents number of members that you want
     * @return
     */
    @Override
    public List<UserDto> getAllUsers(int status, int pageNumber, int membersNumber) {
        List<UserDto> returnValue = new ArrayList<>();

        switch (status) {
            case 1:
                returnValue = administratorServiceImpl.getAllAdministrators(pageNumber, membersNumber);
                break;
            case 2:
                returnValue = directorServiceImpl.getAllDirectors(pageNumber, membersNumber);
                break;
            case 3:
                returnValue = teacherServiceImpl.getAllTeachers(pageNumber, membersNumber);
                break;
            case 4:
                returnValue = parentServiceImpl.getAllParents(pageNumber, membersNumber);
                break;
            default:
                throw new RuntimeException("Unsupported status!");
        }
        
        return returnValue;
    }

    /**
     * Retrieves user in database with that id and in regard to value of
     * attribute status (AdministratorEntity, DirectorEntity, TeacherEntity,
     * ParentEntity)
     *
     * @param status int that represents status of the desired user (1 for
     * Administrators, 2 for Director, 3 for Teachers, 4 for Parents)
     * @param id int that represents id that is searched for in database
     * @return Object of type UserDto filled with values from database for that
     * user with searched id
     */
    @Override
    public UserDto getUserById(int status, int id) {
        UserDto returnValue = new UserDto();
        switch (status) {
            case 1:
                returnValue = administratorServiceImpl.getAdministratorById(id);
                break;
            case 2:
                returnValue = directorServiceImpl.getDirectorById(id);
                break;
            case 3:
                returnValue = teacherServiceImpl.getTeacherById(id);
                break;
            case 4:
                returnValue = parentServiceImpl.getParentById(id);
                break;
            default:
                throw new RuntimeException("Could not edit that user!!");
        }

        return returnValue;
    }

     /**
     * Updates a user in database with data received from UI trough UserDto
     * object. First it updates a UserEntity and after that it updates in regard
     * to value of attribute status of UserEntity (AdministratorEntity,
     * DirectorEntity, TeacherEntity, ParentEntity). If all pass well the entry
     * is committed if there is an exception then the entry is rolled backed.
     *
     * @param userDto Object of type UserDto filled with values that needs to be
     * stored in database
     * @return Object of type UserDto filled with stored values in database
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.findByJmbg(userDto.getJmbg());
        if (userEntity == null) {
            throw new RuntimeException("User do not exist!");
        }
     
        if (userDto.getStatus() != userEntity.getStatus().getStatusId()) {

            switch (userEntity.getStatus().getStatusId()) {
                case 1:
                    administratorServiceImpl.deleteAdministrator(userEntity);
                    break;
                case 2:
                    directorServiceImpl.deleteDirector(userEntity);
                    break;
                case 3:
                    teacherServiceImpl.deleteTeacher(userEntity);
                    break;
                case 4:
                    parentServiceImpl.deleteParent(userEntity);
                    break;
                default:
                    throw new RuntimeException("That role do not exists!");
            }
        }

        if(userDto.getPassword().isEmpty()){
            userDto.setPassword(userEntity.getPassword());
        }
        
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setStatus(statusRepository.findById(userDto.getStatus()).get());

        UserEntity storedUser = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();

        switch (storedUser.getStatus().getStatusId()) {
            case 1:
                returnValue = administratorServiceImpl.saveAdministrator(userDto, userEntity);
                break;
            case 2:
                returnValue = directorServiceImpl.saveDirector(userDto, userEntity);
                break;
            case 3:
                returnValue = teacherServiceImpl.saveTeacher(userDto, userEntity);
                break;
            case 4:
                returnValue = parentServiceImpl.saveParent(userDto, userEntity);
                break;
            default:
                throw new RuntimeException("That role do not exists!");
        }

        return returnValue;
    }

    /**
     * Deletes a user from database.First it checks if a user with that jmbg
     * exists, if it does exits then it deletes it in regard to value of
     * attribute status of UserEntity (AdministratorEntity, DirectorEntity,
     * TeacherEntity, ParentEntity). If all pass well the entry is committed if
     * there is an exception then the entry is rolled backed.
     *
     * @param status int that represents status of the desired user (1 for
     * Administrators, 2 for Director, 3 for Teachers, 4 for Parents)
     * @param jmbg long that represents a unique value for that user (like id)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUser(int status, long jmbg) {
        UserEntity userEntity = userRepository.findByJmbg(jmbg);

        if (userEntity == null) {
            throw new RuntimeException("User with that id does not exist!");
        }

        switch (userEntity.getStatus().getStatusId()) {
            case 1:
                administratorServiceImpl.deleteAdministrator(userEntity);
                break;
            case 2:
                directorServiceImpl.deleteDirector(userEntity);
                break;
            case 3:
                teacherServiceImpl.deleteTeacher(userEntity);
                break;
            case 4:
                parentServiceImpl.deleteParent(userEntity);
                break;
            default:
                throw new RuntimeException("That role do not exists!");
        }

        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity getUserByJmbg(long jmbg) {
        UserEntity userEntity = new UserEntity();
        
        userEntity = userRepository.findByJmbg(jmbg);
        
        return userEntity;
    }
    
    
    
}
