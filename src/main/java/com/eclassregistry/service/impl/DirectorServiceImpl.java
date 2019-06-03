/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.AdministratorEntity;
import com.eclassregistry.model.entity.DirectorEntity;
import com.eclassregistry.model.entity.UserEntity;
import com.eclassregistry.model.repositories.DirectorRepository;
import com.eclassregistry.service.DirectorService;
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
public class DirectorServiceImpl implements DirectorService{

    @Autowired
    DirectorRepository directorRepository;
    
    /**
     * Saves director in database,
     * if that director does not exists in databases then it creates a new one, 
     * if that director exists in database then it updates it.
     *
     * @param userDto Object of class UserDto filled with values that needs to
     * be stored in database
     * @param userEntity Object of class UserEntity filled with values from
     * database
     * @return Object of class UserDto filled with new stored data from database
     */
    @Override
    public UserDto saveDirector(UserDto userDto, UserEntity userEntity) {

        DirectorEntity directorEntity = new DirectorEntity();

        BeanUtils.copyProperties(userDto, directorEntity);
        directorEntity.setJmbg(userEntity);

        DirectorEntity storedDirectorEntity = directorRepository.save(directorEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedDirectorEntity, returnValue);
        returnValue.setEmail(storedDirectorEntity.getJmbg().getEmail());
        returnValue.setJmbg(storedDirectorEntity.getJmbg().getJmbg());
        returnValue.setPassword(storedDirectorEntity.getJmbg().getPassword());
        returnValue.setStatus(storedDirectorEntity.getJmbg().getStatus().getStatusId());
        returnValue.setStatusName(storedDirectorEntity.getJmbg().getStatus().getStatusName());


        return returnValue;
    }

    /**
     * Retrieves all directors from database and put them in a List<UserDto>,
     * this method has paging.
     *
     * @param pageNumber int that represents the number of a page
     * @param membersNumber int that represents number of members that you want
     * to show (limit)
     * @return List of Objects of type UserDto filled with data from database
     */
    @Override
    public List<UserDto> getAllDirectors(int pageNumber, int membersNumber) {
        List<UserDto> allDirectors = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNumber, membersNumber);
        Page<DirectorEntity> directorEntityList = directorRepository.findAll(pageable);

        for (DirectorEntity directorEntity : directorEntityList) {
            UserDto userDto = new UserDto();

            BeanUtils.copyProperties(directorEntity, userDto);
            userDto.setEmail(directorEntity.getJmbg().getEmail());
            userDto.setJmbg(directorEntity.getJmbg().getJmbg());
            userDto.setStatus(directorEntity.getJmbg().getStatus().getStatusId());
            userDto.setStatusName(directorEntity.getJmbg().getStatus().getStatusName());
            userDto.setNumberOfPages(directorEntityList.getTotalPages());
            allDirectors.add(userDto);
        }

        return allDirectors;
    }

    /**
     * Retrieves director in database with that id.
     *
     * @param id int that represents id that is searched for in database
     * @return Object of type UserDto filled with values from database for that
     * administrator with searched id
     */
    @Override
    public UserDto getDirectorById(int id) {
      
       UserDto userDto = new UserDto();
      DirectorEntity directorEntity = directorRepository.findById(id).get();
       
        BeanUtils.copyProperties(directorEntity, userDto);
        userDto.setEmail(directorEntity.getJmbg().getEmail());
        userDto.setJmbg(directorEntity.getJmbg().getJmbg());
        userDto.setStatus(directorEntity.getJmbg().getStatus().getStatusId());
        userDto.setStatusName(directorEntity.getJmbg().getStatus().getStatusName());
       
        return userDto;
    
    }

    /**
     * Deletes a director from database.
     * @param jmbg Object of type UserEntity that represents a unique value in database
     */
    @Override
    public void deleteDirector(UserEntity userEntity) {
        DirectorEntity directorEntity = directorRepository.findByJmbg(userEntity);
        
        if(directorEntity==null){
            throw new RuntimeException("Director with that id does not exists!");
        }
        
        directorRepository.delete(directorEntity);
    }
    
    
}
