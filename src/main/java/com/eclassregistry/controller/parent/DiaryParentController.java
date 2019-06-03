/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.parent;

import com.eclassregistry.model.repositories.GradesRepository;
import com.eclassregistry.service.impl.GradesServiceImpl;
import com.eclassregistry.service.impl.StudentServiceImpl;
import com.eclassregistry.service.impl.SubjectServiceImpl;
import com.eclassregistry.shared.dto.GradesDto;
import com.eclassregistry.shared.dto.StudentDto;
import com.eclassregistry.shared.dto.SubjectDto;
import com.eclassregistry.shared.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Goran
 */



@Controller
@RequestMapping("/parent")
@SessionAttributes("loggedInUser")
public class DiaryParentController {
    
    @Autowired
    StudentServiceImpl studentServiceImpl;
    
     @Autowired
    SubjectServiceImpl subjectServiceImpl;
     
     @Autowired
    GradesServiceImpl gradesServiceImpl;
     
      @Autowired
    GradesRepository gradesRepository;
    
    @GetMapping("/diary")
    public String studentPage(ModelMap model, @ModelAttribute("loggedInUser") UserDto loggedInUser){
        
        
        List<StudentDto> listOfStudents = studentServiceImpl.getAllStudentsFromParentId(loggedInUser.getId());
        List<SubjectDto> allSubjects = subjectServiceImpl.getAllSubjects();
        List<GradesDto> allGrades = new ArrayList<>();
        List<GradesDto> allFinalGrades = new ArrayList<>();
      
        
        for (StudentDto studentDto : listOfStudents) {
            List<GradesDto> grades = gradesServiceImpl.getAllGradesFromStudentId(studentDto.getStudentId());
            for (GradesDto grade : grades) {
                if (grade.getFinalGrade()==false) {
                    allGrades.add(grade);
                }else{
                    allFinalGrades.add(grade);
                }
            }
        }
        
       model.addAttribute("allFinalGrades",allFinalGrades); 
       model.addAttribute("allSubjects",allSubjects); 
       model.addAttribute("allGrades",allGrades);
       model.addAttribute("listOfStudents",listOfStudents);
      
        
        return "parent/diary";
    }
}
