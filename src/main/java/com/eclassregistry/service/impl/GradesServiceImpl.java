/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.GradesEntity;
import com.eclassregistry.model.entity.StudentEntity;
import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.model.repositories.GradesRepository;
import com.eclassregistry.model.repositories.StudentRepository;
import com.eclassregistry.service.GradesService;
import com.eclassregistry.shared.dto.ClassDto;
import com.eclassregistry.shared.dto.GradesDto;
import com.eclassregistry.shared.dto.StudentDto;
import com.eclassregistry.shared.dto.SubjectDto;
import com.eclassregistry.shared.dto.UserDto;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grupa1
 */
@Service
public class GradesServiceImpl implements GradesService{
    
    @Autowired
    GradesRepository gradesRepository;
    
    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    TeacherServiceImpl teacherServiceImpl;
    
    @Autowired
    SubjectServiceImpl subjectServiceImpl;
    
    @Autowired
    StudentServiceImpl studentServiceImpl;
    
    @Autowired
    ClassServiceImpl classServiceImpl;

    @Override
    public GradesDto getGradeById(int id) {
        
        GradesDto gradesDto = new GradesDto();
        
        GradesEntity gradesEntity = gradesRepository.getOne(id);
        
        BeanUtils.copyProperties(gradesEntity, gradesDto);
       
        return gradesDto;
    }

    @Override
    public List<StudentDto> getAllStudentsFromTeacher(int teacherId) {
        
        UserDto teacher = new UserDto();
        List<StudentDto> students = new ArrayList<>();
        ClassDto classOfTeacher = new ClassDto();
   
        int classId = gradesRepository.findClassForTeacherWithId(teacherId);
        
        List<Object[]> listOfAllStudentsByClass = studentRepository.getAllStudentWhereClassId(classId);

        for (int i = 0; i < listOfAllStudentsByClass.size(); i++) {
            
            Object[] arr = listOfAllStudentsByClass.get(i);
            
            StudentDto studentDto = new StudentDto();
            
            studentDto.setStudentId((Integer) arr[0]);
            studentDto.setFirstName((String) arr[1]);
            studentDto.setLastName((String) arr[2]);

            students.add(studentDto);
        }
        
        
        return students;
    }

    @Override
    public List<GradesDto> getGradesIdByStudentId(int Id) {
        
        
        List<GradesDto> grades = new ArrayList<>();
   
        List<Object[]> list = gradesRepository.getGradesIdByStudentId(Id);
        
        for (int i = 0; i < list.size(); i++) {
            
            Object[] arr = list.get(i);
            
            GradesDto dto = new GradesDto();
            
            dto.setId((Integer) arr[0]);
            dto.setTeacher(teacherServiceImpl.getTeacher((Integer) arr[1]));
            dto.setSubject(subjectServiceImpl.getSubjectById((Integer) arr[2]));
            dto.setStudent(studentServiceImpl.findStudentById(Id));

            dto.setGrade((Integer) arr[4]);

            grades.add(dto);
            
            System.out.println(dto.getId());
            System.out.println(dto.getTeacher().getFirstName());
            System.out.println(dto.getSubject().getSubjectName());
            System.out.println(dto.getDate());
        }
 
        return grades;
    }

    @Override
    public List<GradesDto> getAllGrades() {
        
        List<GradesDto> allGrades = new ArrayList<>();
        
        
        List<GradesEntity> gradesEntity = gradesRepository.findAll();
        
        for (GradesEntity gradesEntity1 : gradesEntity) {
            
            GradesDto gradesDto = new GradesDto();
            
            BeanUtils.copyProperties(gradesEntity1, gradesDto);
            
            gradesDto.getId();
            gradesDto.getGrade();
          
        }
  
        return allGrades;
    }

    public void saveGrade(GradesDto gradesDto) {
            
        LocalDateTime then = LocalDateTime.now();
 
        GradesEntity gradesEntity = new GradesEntity();
        BeanUtils.copyProperties(gradesDto, gradesEntity);
        gradesEntity.setDate(then);
        gradesRepository.save(gradesEntity);
    }

    @Override
    public List<GradesDto> getAllGradesFromStudentIdAndSubjectId(int studentId, int subjectId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        
        
        List<GradesDto> allGradesFromStudent = new ArrayList<>();
        
        StudentEntity studentEntity =  studentServiceImpl.findStudentById(studentId);
        
        
        
        SubjectDto subjectDto = subjectServiceImpl.getSubject(subjectId);
        SubjectEntity subjectEntity = new SubjectEntity();
        BeanUtils.copyProperties(subjectDto, subjectEntity);
        
        List<Object[]> listOfAllGrades = gradesRepository.getAllGradesFromStudentIdAndSubjectID(studentId, subjectId);
        
        for (int i = 0; i < listOfAllGrades.size(); i++) {
            
            Object[] arr = listOfAllGrades.get(i);
            
            GradesDto gradesDto = new GradesDto();
            
            gradesDto.setId((Integer) arr[0]);
            gradesDto.setFinalGrade((Boolean) arr[1]);
            gradesDto.setGrade((Integer) arr[2]);
            gradesDto.setDate(((Timestamp) arr[3]).toLocalDateTime());
            gradesDto.setStudent(studentEntity);
            gradesDto.setSubject(subjectEntity);
            gradesDto.setTeacher(teacherServiceImpl.getTeacher((Integer) arr[4]));
            gradesDto.setFormatedDate((((Timestamp) arr[3]).toLocalDateTime()).format(formatter));
            
            allGradesFromStudent.add(gradesDto);
        }
        
       return allGradesFromStudent;
            
    }

    @Override
    public void deleteGrade(int id) {
       gradesRepository.deleteById(id);
    }
    
      @Override
    public List<GradesDto> getAllGradesFromStudentId(int studentId) {
        
         List<GradesDto> allGradesFromStudent = new ArrayList<>();   
        
         List<Object[]> listOfAllGrades = gradesRepository.getAllGradesFromStudentId(studentId);
         
          for (int i = 0; i < listOfAllGrades.size(); i++) {
            
            Object[] arr = listOfAllGrades.get(i);
            
            GradesDto gradesDto = new GradesDto();
            
            gradesDto.setId((Integer) arr[0]);
            gradesDto.setFinalGrade((Boolean) arr[1]);
            gradesDto.setGrade((Integer) arr[2]);
            gradesDto.setDate(((Timestamp) arr[3]).toLocalDateTime());
            gradesDto.setStudent(studentRepository.getOne(studentId));
            gradesDto.setSubject(subjectServiceImpl.getSubjectById((Integer) arr[5]));
            gradesDto.setTeacher(teacherServiceImpl.getTeacher((Integer) arr[4]));
       
            allGradesFromStudent.add(gradesDto);
        }
          
          return allGradesFromStudent;
          
    }
    
    
    
}
