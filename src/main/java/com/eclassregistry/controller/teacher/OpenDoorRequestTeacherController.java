/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.teacher;

import com.eclassregistry.service.impl.OpenDoorRequestServiceImpl;
import com.eclassregistry.shared.dto.OpenDoorRequestDto;
import com.eclassregistry.shared.dto.UserDto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Grupa1
 */
@Controller
@RequestMapping("/teacher")
@SessionAttributes("loggedInUser")
public class OpenDoorRequestTeacherController {

    @Autowired
    OpenDoorRequestServiceImpl openDoorRequestServiceImpl;

    /**
     * Sends data to view, List<OpenDoorRequestDto> filled with all up coming
     * request on which loggedInUser(Teacher) already responded and
     * List<OpenDoorRequestDto> filled with up coming requests on which the
     * teacher didn't respond yet.
     *
     * @param model ModelMap object that carry data to the view
     * @param loggedInUser ModelAttribute (Session attribute) that carry data
     * about logged in user (Teacher)
     * @return View located in folder teacher with name opendoors
     */
    @GetMapping("/opendoors")
    public String openDoorsPage(ModelMap model, @ModelAttribute("loggedInUser") UserDto loggedInUser) {
        List[] listArr = openDoorRequestServiceImpl.getAllRequestSortedByDateForTeacher(loggedInUser.getId(), LocalDateTime.now());

        List<OpenDoorRequestDto> listOfRespondedRequests = listArr[0];
        List<OpenDoorRequestDto> listOfActiveRequests = listArr[1];

        model.addAttribute("listOfRespondedRequests", listOfRespondedRequests);
        model.addAttribute("listOfActiveRequests", listOfActiveRequests);

        return "teacher/opendoors";
    }

    /**
     * Saves a response on request for an open door from a parent.
     *
     * @param answer Boolean that represents teachers answer to a request
     * @param id Integer that represents unique id in database for that request
     * @return Redirect to a view located in folder teacher with name opendoors
     */
    @GetMapping("/opendoorrequest")
    public String openDoorsPage(@RequestParam(name = "ans", required = true) Boolean answer, @RequestParam(name = "id", required = true) Integer id) {

        openDoorRequestServiceImpl.updateOpenDoorRequest(answer, id);

        return "redirect:/teacher/opendoors";
    }
}
