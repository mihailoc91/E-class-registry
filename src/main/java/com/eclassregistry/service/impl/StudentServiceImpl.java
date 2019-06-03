/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.ClassEntity;
import com.eclassregistry.model.entity.ParentEntity;
import com.eclassregistry.model.entity.StudentEntity;
import com.eclassregistry.model.repositories.StudentRepository;
import com.eclassregistry.service.StudentService;
import com.eclassregistry.shared.dto.ClassDto;
import com.eclassregistry.shared.dto.StudentDto;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassServiceImpl classServiceImpl;

    @Autowired
    ParentServiceImpl parentServiceImpl;

    /**
     * Saves a student into database, if the student didn't exists before in
     * database it creates a new one if it did exists in database before, it
     * updates it.
     *
     * @param studentDto Object of StudentDto.class that carry data insert by
     * Administrator through UI
     */
    @Override
    public void saveStudent(StudentDto studentDto) {

        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentDto, studentEntity);

        ClassEntity classEntity = classServiceImpl.findClassById(studentDto.getClassId());
        studentEntity.setClassEntity(classEntity);

        List<ParentEntity> parentEntitys = new ArrayList<>();
        //adding parents from studentDto and adding them into parentEntitys
        for (Integer parentId : studentDto.getParents()) {
            ParentEntity parentEntity = parentServiceImpl.findParentById(parentId);

            parentEntitys.add(parentEntity);
        }
        studentEntity.setParents(parentEntitys);

        studentRepository.save(studentEntity);
    }

    /**
     * Retrieves student from database with that id.
     *
     * @param id int that represents id in database
     * @return StudentEntity.class filled with data from database
     */
    @Override
    public StudentEntity findStudentById(int id) {
        StudentEntity studentEntity = studentRepository.getOne(id);

        return studentEntity;
    }

    /**
     * Retrieves all students from database, implements paging.
     *
     * @param pageNumber int that represent wanted page number
     * @param membersNumber int that represents number of results per page
     * @return LIst<StudentDto> filled with data from database
     */
    @Override
    public List<StudentDto> getAllStudents(int pageNumber, int membersNumber) {
        List<StudentDto> allStudents = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNumber, membersNumber);
        Page<StudentEntity> studentsEntityList = studentRepository.findAll(pageable);

        for (StudentEntity studentEntity : studentsEntityList) {
            StudentDto studentDto = new StudentDto();

            BeanUtils.copyProperties(studentEntity, studentDto);
            studentDto.setClassEntity(new ClassDto(studentEntity.getClassEntity()));
            studentDto.setClassId(studentEntity.getClassEntity().getClassId());
            List<UserDto> parents = new ArrayList<>();
            for (ParentEntity parent : studentEntity.getParents()) {
                UserDto parentDto = new UserDto(parent.getId(), parent.getFirstName(), parent.getLastName(), parent.getJmbg().getJmbg());
                parents.add(parentDto);
            }

            studentDto.setParentsDto(parents);
            studentDto.setNumberOfPages(studentsEntityList.getTotalPages());

            allStudents.add(studentDto);
        }

        return allStudents;
    }

    /**
     * Retrieves student from database.
     *
     * @param id int that represents unique id in database for wanted student
     * @return StudentDto.class filled with data from database
     */
    @Override
    public StudentDto getStudentById(int id) {
        StudentDto studentDto = new StudentDto();

        StudentEntity studentEntity = studentRepository.getOne(id);

        BeanUtils.copyProperties(studentEntity, studentDto);
        studentDto.setClassEntity(new ClassDto(studentEntity.getClassEntity()));
        studentDto.setClassId(studentEntity.getClassEntity().getClassId());
        List<UserDto> parents = new ArrayList<>();
        List<Integer> parentsIds = new ArrayList<>();
        for (ParentEntity parent : studentEntity.getParents()) {
            UserDto parentDto = new UserDto(parent.getId(), parent.getFirstName(), parent.getLastName(), parent.getJmbg().getJmbg());
            parents.add(parentDto);
            parentsIds.add(parent.getId());
        }

        studentDto.setParentsDto(parents);
        studentDto.setParents(parentsIds);

        return studentDto;

    }

    /**
     * Deletes the student from database.
     *
     * @param id int that represents unique id of selected student in database
     */
    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    /**
     * Retrieves all students who's parent is selected.
     *
     * @param id int that represents unique id in database for that parent
     * @return List<StudentDto> filled with data from database
     */
    @Override
    public List<StudentDto> getAllStudentsFromParentId(int id) {

        List<StudentDto> listOfStudents = new ArrayList<>();

        List<Integer> studentId = studentRepository.getAllStudentsWhereParentId(id);

        for (Integer integer : studentId) {
            listOfStudents.add(getStudentById(integer));
        }

        return listOfStudents;
    }

}
