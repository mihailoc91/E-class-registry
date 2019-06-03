/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface DirectorService {
    
    UserDto saveDirector (UserDto userDto, UserEntity userEntity);
    List<UserDto> getAllDirectors(int pageNumber, int membersNumber);
    UserDto getDirectorById(int id);
    void deleteDirector(UserEntity userEntity);
}
