/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.parent;

import com.eclassregistry.model.repositories.AnnouncementRepository;
import com.eclassregistry.model.repositories.ClassRepository;
import com.eclassregistry.service.impl.AnnouncementServiceImpl;
import com.eclassregistry.service.impl.ClassServiceImpl;
import com.eclassregistry.service.impl.StudentServiceImpl;
import com.eclassregistry.shared.dto.AnnouncementDto;
import com.eclassregistry.shared.dto.StudentDto;
import com.eclassregistry.shared.dto.UserDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class AnnouncementParentController {
    
    @Autowired
    AnnouncementServiceImpl announcementServiceImpl;
    
    @Autowired
    AnnouncementRepository announcementRepository;
    
    @Autowired
    StudentServiceImpl studentServiceImpl;
    
    @Autowired
    ClassServiceImpl classServiceImpl;
    
    @Autowired
    ClassRepository classRepository;
    
    @GetMapping("/announcement")
    public String parentAnnouncementPage(ModelMap model, @ModelAttribute("loggedInUser") UserDto loggedInUser){
    
        List<StudentDto> allStudentsFromParent =  studentServiceImpl.getAllStudentsFromParentId(loggedInUser.getId());
        List<AnnouncementDto> listOfAnnouncement = new ArrayList<>();
        Set<Integer> announcementIdSet = new HashSet<>();
        
        Set<Integer> classId = new HashSet<>();
      
        
        for (StudentDto studentDto : allStudentsFromParent) {
                classId.add(studentDto.getClassId());
        }
        
        for (Integer integer : classId) {
          List<Integer> bla = announcementRepository.getAnnouncementIdWhereClassId(integer);
            announcementIdSet.addAll(bla);
        }
        
        for (Integer integer1 : announcementIdSet) {
                AnnouncementDto announcementDto = announcementServiceImpl.getAnnouncement(integer1);
                List<Integer> classesFromAnnouncement = announcementRepository.getClassIdFromAnnouncementId(integer1);
                List<String> classesNames = new ArrayList<>();
                
                for (Integer integer : classesFromAnnouncement) {
                    classesNames.add(classRepository.getClassNameFromClassId(integer));
                }
                announcementDto.setClassesName(classesNames);
                announcementDto.setClasses(classesFromAnnouncement);
                listOfAnnouncement.add(announcementDto);
            }
        
        model.addAttribute("listOfAnnouncement",listOfAnnouncement); 
        
        return "parent/announcements";
    }
}
