/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.StudentEntity;
import com.eclassregistry.shared.dto.StudentDto;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface StudentService {

    void saveStudent(StudentDto studentDto);

    StudentEntity findStudentById(int id);

    List<StudentDto> getAllStudents(int pageNumber, int membersNumber);

    StudentDto getStudentById(int id);

    void deleteStudent(int id);

    List<StudentDto> getAllStudentsFromParentId(int id);
}
