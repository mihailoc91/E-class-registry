/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller;

import com.eclassregistry.service.impl.SubjectServiceImpl;
import com.eclassregistry.service.impl.TeacherServiceImpl;
import com.eclassregistry.service.impl.UserServiceImpl;
import com.eclassregistry.shared.dto.SubjectDto;
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
public class TeacherController {
    
    @Autowired
    TeacherServiceImpl teacherServiceImpl;
    
    @Autowired
    SubjectServiceImpl subjectServiceImpl;
    
    @Autowired
    UserServiceImpl userServiceImpl;
    
    /**
     * Shows a page where Administrator can add/remove subjects from selected
     * teacher.
     *
     * @param model ModelMap object that carry data to the view
     * @param id String that needs to be converted later to int, represents id
     * of already existing teacher. It's required.
     * @return View teacher
     */
    @GetMapping("/teacher")
    public String teacherAndSubjectsPage(ModelMap model, @RequestParam(name = "id", required = true) String id) {
        
        UserDto userDto = teacherServiceImpl.getTeacherById(Integer.valueOf(id));
        List<SubjectDto> allSubjects = subjectServiceImpl.getAllSubjects();
       
        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("userDto", userDto);

        return "teacher";
    }
    
    /**
     * Saves teacher and his subjects. Before saving them it checks for binding
     * errors, if they don't exist then it saves the data entered by
     * Administrator to database, if they exist returns Administrator on index
     * page.
     *
     * @param model ModelMap object that carry data to the view
     * @param userDto ModelAttribute UserDto.class that stores UI entered data
     * by Administrator
     * @param result BindingResult checks for any errors with binding
     * @return Redirect to view uspeh
     */
    @PostMapping("/teacher")
    public String saveTeacherAndSubjects(ModelMap model, @ModelAttribute UserDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            return "index";
        }

        userServiceImpl.updateUser(userDto);

        return "redirect:users?status="+userDto.getStatus();
    }
}
