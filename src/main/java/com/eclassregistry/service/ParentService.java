/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.ParentEntity;
import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface ParentService {

    UserDto saveParent(UserDto userDto, UserEntity userEntity);

    List<UserDto> getAllParents(int pageNumber, int membersNumber);

    List<UserDto> getAllParents();

    UserDto getParentById(int id);

    ParentEntity findParentById(int id);

    void deleteParent(UserEntity userEntity);

    UserDto getParentByJmbg(UserEntity userEntity);

    List<UserDto> getParentsForTeacher(int teacherId);

}
