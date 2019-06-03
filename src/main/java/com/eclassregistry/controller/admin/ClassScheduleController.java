/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.admin;

import com.eclassregistry.model.entity.ClassEntity;
import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.model.repositories.ClassRepository;
import com.eclassregistry.model.repositories.SubjectRepository;
import com.eclassregistry.service.impl.SubjectServiceImpl;
import com.eclassregistry.shared.dto.ClassDto;
import com.eclassregistry.shared.dto.ClassScheduleDto;
import com.eclassregistry.shared.dto.SubjectDto;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Grupa1
 */

@Controller
@RequestMapping("/admin")
public class ClassScheduleController {
    
    @Autowired
    SubjectRepository subjectRepository;
    
    @Autowired
    ClassRepository classRepository;
    
    
    
    @GetMapping("/class_schedule")
    public String classScheduleSubject(ModelMap model) {
        
        List<SubjectEntity> allSubjectsEntities = subjectRepository.findAll();
        
        List<ClassEntity> allClassEntities = classRepository.findAll();
        
        
        model.addAttribute("allSubjectsEntities", allSubjectsEntities);
        model.addAttribute("allClassEntities", allClassEntities);
        
        return "admin/class_schedule";
    }
    
    @PostMapping("/class_schedule")
    public String saveClassSchedule(ModelMap model, @Valid @ModelAttribute ClassScheduleDto classScheduleDto, BindingResult result){
        
        
        
        return null;
    }
    
    @PostMapping("/schedule")
    public String getDataFromJS(@RequestBody int[][] niz){
        System.out.println(niz[0]);
        return "admin/admin";
    }
    
//        @ResponseBody
//@RequestMapping(value = "/search/api/getSearchResult", method = RequestMethod.POST)
//public String getSearchResultViaAjax(@RequestBody UserId user) {
//    System.out.println("come to ajax" + user.getId());
//    return "hello";
//}
    
    public String classSchedule() {

        return "admin/class_schedule";
    }
}
