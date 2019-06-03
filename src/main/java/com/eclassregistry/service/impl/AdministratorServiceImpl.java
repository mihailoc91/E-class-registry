/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.AdministratorEntity;
import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.model.repositories.AdministratorRepository;
import com.eclassregistry.service.AdministratorService;
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
public class AdministratorServiceImpl implements AdministratorService{

    @Autowired
    AdministratorRepository administratorRepository;
    
    /**
     * Saves administrator in database,
     * if that administrator does not exists in databases then it creates a new one, 
     * if that administrator exists in database then it updates it.
     *
     * @param userDto Object of class UserDto filled with values that needs to
     * be stored in database
     * @param userEntity Object of class UserEntity filled with values from
     * database
     * @return Object of class UserDto filled with new stored data from database
     */
    @Override
    public UserDto saveAdministrator(UserDto userDto, UserEntity userEntity) {

        AdministratorEntity administratorEntity = new AdministratorEntity();

        BeanUtils.copyProperties(userDto, administratorEntity);
        administratorEntity.setJmbg(userEntity);

        AdministratorEntity storedAdministratorEntity = administratorRepository.save(administratorEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedAdministratorEntity, returnValue);
        returnValue.setEmail(storedAdministratorEntity.getJmbg().getEmail());
        returnValue.setJmbg(storedAdministratorEntity.getJmbg().getJmbg());
        returnValue.setPassword(storedAdministratorEntity.getJmbg().getPassword());
        returnValue.setStatus(storedAdministratorEntity.getJmbg().getStatus().getStatusId());
        returnValue.setStatusName(storedAdministratorEntity.getJmbg().getStatus().getStatusName());

        return returnValue;
    }
    
    /**
     * Retrieves all administrators from database and put them in a
     * List<UserDto>, this method has paging.
     *
     * @param pageNumber int that represents the number of a page
     * @param membersNumber int that represents number of members that you want
     * to show (limit)
     * @return List of Objects of type UserDto filled with data from database
     */
    @Override
    public List<UserDto> getAllAdministrators(int pageNumber, int membersNumber) {
        List<UserDto> allAdministrators = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNumber, membersNumber);
        Page<AdministratorEntity> administratorEntityList = administratorRepository.findAll(pageable);
        
        for (AdministratorEntity administratorEntity : administratorEntityList) {
            UserDto userDto = new UserDto();

            BeanUtils.copyProperties(administratorEntity, userDto);
            userDto.setEmail(administratorEntity.getJmbg().getEmail());
            userDto.setJmbg(administratorEntity.getJmbg().getJmbg());
            userDto.setStatus(administratorEntity.getJmbg().getStatus().getStatusId());
            userDto.setStatusName(administratorEntity.getJmbg().getStatus().getStatusName());
            userDto.setNumberOfPages(administratorEntityList.getTotalPages());
            allAdministrators.add(userDto);
        }

        return allAdministrators;
    }

    /**
     * Retrieves administrator in database with that id.
     *
     * @param id int that represents id that is searched for in database
     * @return Object of type UserDto filled with values from database for that
     * administrator with searched id
     */
    @Override
    public UserDto getAdministratorById(int id) {
        UserDto userDto = new UserDto();
        AdministratorEntity administratorEntity = administratorRepository.findById(id).get();

        BeanUtils.copyProperties(administratorEntity, userDto);
        userDto.setEmail(administratorEntity.getJmbg().getEmail());
        userDto.setJmbg(administratorEntity.getJmbg().getJmbg());
        userDto.setStatus(administratorEntity.getJmbg().getStatus().getStatusId());
        userDto.setStatusName(administratorEntity.getJmbg().getStatus().getStatusName());

        return userDto;
    }

    /**
     * Deletes an administrator from database.
     * @param jmbg Object of type UserEntity that represents a unique value in database
     */
    @Override
    public void deleteAdministrator(UserEntity userEntity) {
        AdministratorEntity administratorEntity = administratorRepository.findByJmbg(userEntity);
        
        if(administratorEntity==null){
            throw new RuntimeException("Administrator with that id does not exists!");
        }
        
        administratorRepository.delete(administratorEntity);
    }

  
}
