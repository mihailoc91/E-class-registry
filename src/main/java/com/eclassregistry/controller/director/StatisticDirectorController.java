/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.director;

import com.eclassregistry.model.repositories.GradesRepository;
import com.eclassregistry.model.repositories.StudentRepository;
import com.eclassregistry.service.impl.ClassServiceImpl;
import com.eclassregistry.service.impl.SubjectServiceImpl;
import com.eclassregistry.shared.dto.ClassDto;
import com.eclassregistry.shared.dto.SubjectDto;
import com.eclassregistry.shared.dto.UserDto;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Goran
 */
@Controller
@RequestMapping("/director")
public class StatisticDirectorController {
    
     @Autowired
     ClassServiceImpl classServiceImpl;
     
     @Autowired
     StudentRepository studentRepository;
     
     @Autowired
     GradesRepository gradesRepository;
     
     @Autowired
     SubjectServiceImpl subjectServiceImpl;
    
    @GetMapping("/chartDetail")
    public String directorChartPageSelect(ModelMap model,
            @RequestParam(name = "subject", required = true) String subject,
            @ModelAttribute("loggedInUser") UserDto loggedInUser){
       
        DecimalFormat df2 = new DecimalFormat("#.##");
        List<ClassDto> chartClassesAndGrades =  new ArrayList<>();
        
        List<SubjectDto> allSubjects = subjectServiceImpl.getAllSubjects();
        List<ClassDto> classDtos = classServiceImpl.getAllClasses();
        
        
              for (ClassDto classDto : classDtos) {
                 ClassDto tempClassDto = new ClassDto();

                 tempClassDto.setClassName(classDto.getClassName());
                 List<Integer> allGrades = new ArrayList<>();
                 int sum = 0;
                 double finalSum = 0;
                List<Integer> allStudentId = studentRepository.getAllStudentsIdWhereClassId(classDto.getClassId());
          
                
                    for (Integer studentId : allStudentId) {
                            Integer grade = gradesRepository.getAllFinalGradesFromStudentIdAndSubjectId(studentId,Integer.valueOf(subject));
                            if (grade != null) {
                            allGrades.add(grade);        
                        }
                }
                    if (allGrades.isEmpty()) {
                        finalSum=1;
                    }else{
                        for (Integer grade : allGrades) {
                            sum = sum + grade;
                        }
                        finalSum = Double.valueOf(sum) / allGrades.size();
                    }
            Double value = Double.valueOf(df2.format(finalSum));
            tempClassDto.setSumOfGrades(value);
            chartClassesAndGrades.add(tempClassDto);
            }
      
        SubjectDto subjectDto = subjectServiceImpl.getSubject(Integer.valueOf(subject));
        
         model.addAttribute("subjectDto",subjectDto); 
         model.addAttribute("allSubjects",allSubjects); 
         model.addAttribute("chartClassesAndGrades",chartClassesAndGrades); 
         
       return "director/chart";
    }
    
    @GetMapping("/chartSchool")
    public String directorChartPageSuccess(ModelMap model,@ModelAttribute("loggedInUser") UserDto loggedInUser){
        
        DecimalFormat df2 = new DecimalFormat("#.##");
        List<ClassDto> chartClassesAndGrades =  new ArrayList<>();
        
        List<SubjectDto> allSubjects = subjectServiceImpl.getAllSubjects();
        List<ClassDto> classDtos = classServiceImpl.getAllClasses();
       
        
         for (SubjectDto subject : allSubjects) {
                ClassDto tempClassDto = new ClassDto();
                List<Double> totalSum = new ArrayList<>();
            for (ClassDto classDto : classDtos) {
                        
                        tempClassDto.setClassName(classDto.getClassName());
                        tempClassDto.setSubjectName(subject.getSubjectName());
                 
                 List<Integer> allGrades = new ArrayList<>();
                 int sum = 0;
                 double finalSum = 0;
                List<Integer> allStudentId = studentRepository.getAllStudentsIdWhereClassId(classDto.getClassId());
          
                
                    for (Integer studentId : allStudentId) {
                            Integer grade = gradesRepository.getAllFinalGradesFromStudentIdAndSubjectId(studentId,subject.getSubjectId());
                            if (grade != null) {
                            allGrades.add(grade);        
                        }
                }
                    if (allGrades.isEmpty()) {
                        finalSum=1;
                    }else{
                        for (Integer grade : allGrades) {
                            sum = sum + grade;
                        }
                        finalSum = Double.valueOf(sum) / allGrades.size();
                        }
          
                 totalSum.add(finalSum);
            }
             double temp = 0;   
             for (Double doubleNumber : totalSum) {
                 temp = temp + doubleNumber;
             }
             temp = temp / totalSum.size();
                Double value = Double.valueOf(df2.format(temp));
                tempClassDto.setSumOfGrades(value);
            chartClassesAndGrades.add(tempClassDto);
         }
            
         model.addAttribute("allSubjects",allSubjects); 
         model.addAttribute("chartClassesAndGrades",chartClassesAndGrades); 
         
       return "director/chart_school";
    }
    
    @GetMapping("/chart")
    public String directorChartPage(ModelMap model,@ModelAttribute("loggedInUser") UserDto loggedInUser){
       
        DecimalFormat df2 = new DecimalFormat("#.##");
        
        
        List<ClassDto> chartClassesAndGrades =  new ArrayList<>();
        
        List<SubjectDto> allSubjects = subjectServiceImpl.getAllSubjects();
        int id = allSubjects.get(0).getSubjectId();
                
       
        List<ClassDto> classDtos = classServiceImpl.getAllClasses();
        
              for (ClassDto classDto : classDtos) {
                 ClassDto tempClassDto = new ClassDto();
                 tempClassDto.setClassName(classDto.getClassName());
                 List<Integer> allGrades = new ArrayList<>();
                 int sum = 0;
                 double finalSum = 0;
                List<Integer> allStudentId = studentRepository.getAllStudentsIdWhereClassId(classDto.getClassId());
          
                    for (Integer studentId : allStudentId) {
                            Integer grade = gradesRepository.getAllFinalGradesFromStudentIdAndSubjectId(studentId,id);
                            if (grade != null) {
                            allGrades.add(grade);        
                        }
                }
                    if (allGrades.isEmpty()) {
                        finalSum=1;
                        }else{
                            for (Integer grade : allGrades) {
                            sum = sum + grade;
                        }
                        finalSum = Double.valueOf(sum) / allGrades.size();
                    }
            Double value = Double.valueOf(df2.format(finalSum));
            tempClassDto.setSumOfGrades(value);
            chartClassesAndGrades.add(tempClassDto);
            }
      
        SubjectDto subjectDto = subjectServiceImpl.getSubject(id);
        
         model.addAttribute("subjectDto",subjectDto); 
         model.addAttribute("allSubjects",allSubjects); 
         model.addAttribute("chartClassesAndGrades",chartClassesAndGrades); 
         
       return "director/chart";
    }
    

}
