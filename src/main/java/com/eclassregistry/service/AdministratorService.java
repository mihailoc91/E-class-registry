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
public interface AdministratorService {
   UserDto saveAdministrator(UserDto userDto, UserEntity userEntity);
   List <UserDto> getAllAdministrators(int pageNumber, int membersNumber);
   UserDto getAdministratorById(int id);
   void deleteAdministrator(UserEntity userEntity);
}
