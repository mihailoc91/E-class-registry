/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.ClassEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import com.eclassregistry.model.repositories.ClassRepository;
import com.eclassregistry.model.repositories.TeacherRepository;
import com.eclassregistry.service.ClassService;
import com.eclassregistry.shared.dto.ClassDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Mihailo
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ClassServiceImpl implements ClassService{

    @Autowired
    ClassRepository classRepository;
    
    @Autowired
    TeacherServiceImpl teacherServiceImpl;
    
    @Autowired
    TeacherRepository teacherRepository;
    
    /**
     * Saves a class in database with data that were entered by Administrator. If the class don't exits it creates a new one in database or if it exists then it updates it.This method is transactional, if something goes wrong (exception is thrown) then it rollback but if all goes well then it commits.
     * @param classDto object of type ClassDto.class that carry data from UI that needs to be stored in database
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveClass(ClassDto classDto) {
       
        ClassEntity classEntity = new ClassEntity();
        TeacherEntity teacherEntity = teacherServiceImpl.getTeacher(classDto.getTeacherId());
        //checks if the class dont exist (if its id is 0 then its a new class and it needs to be created in database but if its not 0 then the class already exists and needs to be updated)
        if(classDto.getClassId()!=0)classEntity.setClassId(classDto.getClassId());
        
        classEntity.setClassName(classDto.getClassName());
        classEntity.setTeacherEntity(teacherEntity);
        classRepository.save(classEntity);
    }

    
   /**
    * Retrieves all classes from database and put them in a List<ClassDto>,
     * this method has paging.
    * @param pageNumber int that represents the number of a page
    * @param membersNumber int that represents number of members that you want
     * to show (limit)
    * @return List of Objects of type ClassDto filled with data from database
    */
    @Override
    public List<ClassDto> getAllClasses(int pageNumber, int membersNumber) {
        List<ClassDto> allClasses = new ArrayList<>();
        
        Pageable pageable = PageRequest.of(pageNumber, membersNumber);
        Page <ClassEntity> allClassEntities =   classRepository.findAll(pageable);
        
        //razmisliti o spajanju tabela classes i teahcers jer je upit tako efikasniji
        
        for(ClassEntity classEntity : allClassEntities){
            
            ClassDto classDto = new ClassDto();
            BeanUtils.copyProperties(classEntity, classDto);
            
            classDto.setTeacherId(classEntity.getTeacherEntity().getId());
            classDto.setTeacherFirstName(classEntity.getTeacherEntity().getFirstName());
            classDto.setTeacherLastName(classEntity.getTeacherEntity().getLastName());
            classDto.setNumberOfPages(allClassEntities.getTotalPages());
            
            allClasses.add(classDto);
        }
        
      return allClasses;
    }

    /**
     * Retrieves a class from database with that id.
     * @param id int that represent ID in database of the requested class 
     * @return Object of type ClassDto filled with data from database
     */
    @Override
    public ClassDto getClassById(int id) {
        ClassDto classDto = new ClassDto();
        
        ClassEntity classEntity = classRepository.findByClassId(id);
        
        BeanUtils.copyProperties(classEntity, classDto);
        classDto.setTeacherId(classEntity.getTeacherEntity().getId());
        classDto.setTeacherFirstName(classEntity.getTeacherEntity().getFirstName());
        classDto.setTeacherLastName(classEntity.getTeacherEntity().getLastName());
        
        return classDto;
                }

    /**
     * 
     * Deletes a class from database.
     * @param id int that represents a unique value in database
     *
     *
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void deleteClass(int id) {
       ClassEntity classEntity = classRepository.findByClassId(id);
       if(classEntity==null){
            throw new RuntimeException("Class with that id does not exists!");
        }
       classRepository.delete(classEntity);
    }

    /**
     * Retrieves class from database.
     * @param id int that represent ID in database
     * @return Object of ClassEntity.class filled with data from database
     */
    @Override
    public ClassEntity findClassById(int id) {
        ClassEntity classEntity = classRepository.findByClassId(id);
        return classEntity;
    }

     /**
    * Retrieves all classes from database and put them in a List<ClassDto>.
    * @return List of Objects of type ClassDto filled with data from database
    */
    @Override
    public List<ClassDto> getAllClasses() {
         List<ClassDto> allClasses = new ArrayList<>();
        
        
        List <ClassEntity> allClassEntities =   classRepository.findAll();
        
        //razmisliti o spajanju tabela classes i teahcers jer je upit tako efikasniji
        
        for(ClassEntity classEntity : allClassEntities){
            
            ClassDto classDto = new ClassDto();
            BeanUtils.copyProperties(classEntity, classDto);
            
            classDto.setTeacherId(classEntity.getTeacherEntity().getId());
            classDto.setTeacherFirstName(classEntity.getTeacherEntity().getFirstName());
            classDto.setTeacherLastName(classEntity.getTeacherEntity().getLastName());
           
            
            allClasses.add(classDto);
        }
        
      return allClasses;
    }
    
}
