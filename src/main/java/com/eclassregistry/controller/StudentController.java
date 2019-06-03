/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller;

import com.eclassregistry.service.impl.ClassServiceImpl;
import com.eclassregistry.service.impl.ParentServiceImpl;
import com.eclassregistry.service.impl.StudentServiceImpl;
import com.eclassregistry.shared.dto.ClassDto;
import com.eclassregistry.shared.dto.StudentDto;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Grupa1
 */
@Controller
public class StudentController {
    
    @Autowired
    StudentServiceImpl studentServiceImpl;
    
    @Autowired
    ClassServiceImpl classServiceImpl;
    
    @Autowired
    ParentServiceImpl parentServiceImpl;
   
    
    @GetMapping("/student")
    public String studentPage(ModelMap model, @RequestParam(name = "id", required = false) String id){
        
        StudentDto studentDto = new StudentDto();
        List<ClassDto> allClasses = classServiceImpl.getAllClasses();
        List<UserDto> allParents = parentServiceImpl.getAllParents();
        //multiple not working proparly, cant select two parents but selecting one works fine, need to fix it. also should fix so that student cant be created without parent!
        if(id != null){
            studentDto = studentServiceImpl.getStudentById(Integer.valueOf(id));
        }
        model.addAttribute("allParents", allParents);
        model.addAttribute("allClasses", allClasses);
        model.addAttribute("studentDto",studentDto);
        return "student";
    }
    
    
     @GetMapping("/students")
    public String getStudentsPage(ModelMap model,
            @RequestParam(name = "page", defaultValue = "0") String page,
            @RequestParam(name = "members", defaultValue = "2") String members) {

     
            List<StudentDto> allStudentsDto = studentServiceImpl.getAllStudents(Integer.valueOf(page),Integer.valueOf(members));
            model.addAttribute("allStudentsDto", allStudentsDto);
        

        return "students";
    }
    
    @GetMapping("/student/remove")
    public String deleteStudent(ModelMap model, @RequestParam(name = "id", required = true) String id){
    
        Integer studentId = Integer.valueOf(id);
        
        if(studentId !=null){
            studentServiceImpl.deleteStudent(studentId);
        }
        
    return "redirect:../students";
    }
    
    @PostMapping("/student")
    public String saveStudent(ModelMap model, @ModelAttribute StudentDto studentDto, BindingResult result){
        
        if(result.hasErrors()){
            throw new RuntimeException("Wrong input for student!");
        }
       
        
        studentServiceImpl.saveStudent(studentDto);
         
        return "redirect:index";
    }
    
    
}
