/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.TeacherEntity;
import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface TeacherService {
    
    UserDto saveTeacher (UserDto userDto, UserEntity userEntity);
    TeacherEntity getTeacher(int id);
    List <UserDto> getAllTeachers(int pageNumber, int membersNumber);
    UserDto getTeacherById(int id);
    void deleteTeacher(UserEntity userEntity);
    List<UserDto> allAvailableTeachers();
}
