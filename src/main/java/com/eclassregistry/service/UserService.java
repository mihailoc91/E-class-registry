/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Mihailo
 */
//Extends UserDetailsService so i can retrive custom user from database for security login
public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers(int status, int pageNumber, int membersNumber);

    UserDto getUserById(int status, int id);

    UserDto updateUser(UserDto userDto);

    void deleteUser(int status, long jmbg);

    UserEntity getUserByJmbg(long jmbg);

    UserDto getUserByEmail(String email);
}
