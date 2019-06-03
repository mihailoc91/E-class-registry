/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.parent;

import com.eclassregistry.service.impl.OpenDoorRequestServiceImpl;
import com.eclassregistry.service.impl.OpenDoorServiceImpl;
import com.eclassregistry.service.impl.TeacherServiceImpl;
import com.eclassregistry.shared.dto.OpenDoorRequestDto;
import com.eclassregistry.shared.dto.UserDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Grupa1
 */
@Controller
@RequestMapping("/parent")
@SessionAttributes("loggedInUser")
public class OpenDoorParentController {

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    @Autowired
    OpenDoorRequestServiceImpl openDoorRequestServiceImpl;

    @Autowired
    OpenDoorServiceImpl openDoorServiceImpl;

    String[] daysOfTheWeek = {"Mondays", "Tuesdays", "Wednesdays", "Thursdays", "Fridays"};

    /**
     * Sends to view List<UserDto> of all teachers that teach
     * loggedInUser(Parents) kids so loggedInUser(Parent) can make a new request
     * for a open door, List<OpenDoorRequestDto> that carry all data about
     * already sent up coming requests.
     *
     * @param model ModelMap object that carry data to the view
     * @param loggedInUser ModelAttribute (Session attribute) that carry data
     * about logged in user (Parent)
     * @return View in folder parent with name opendoors
     */
    @GetMapping("/opendoors")
    public String openDoorsPage(ModelMap model, @ModelAttribute("loggedInUser") UserDto loggedInUser) {

        List<UserDto> allTeachersForParent = teacherServiceImpl.getTeachersForParent(loggedInUser.getId());
        List<OpenDoorRequestDto> allRequest = openDoorRequestServiceImpl.getAllRequestSortedByDateForParent(loggedInUser.getId(), LocalDateTime.now());

        model.addAttribute("allTeachersForParent", allTeachersForParent);
        model.addAttribute("allRequest", allRequest);
        return "parent/opendoors";
    }

    /**
     * Sends to view data necessary for creating a new open door request. Based
     * on parameters returns informations about open door of that teacher, on
     * which day he holds open door, in which time frame so a parent can set an
     * appointment.
     *
     * @param model ModelMap object that carry data to the view
     * @param loggedInUser ModelAttribute (Session attribute) that carry data
     * about logged in user (Parent)
     * @param teacherId Integer that represents the unique id in database for
     * selected teacher, this parm is required and has default value of 0
     * @return View in folder parent with name opendoor
     */
    @GetMapping("/opendoor")
    public String openDoorPage(ModelMap model, @ModelAttribute("loggedInUser") UserDto loggedInUser,
            @RequestParam(name = "teacher", required = true, defaultValue = "0") Integer teacherId) {
        OpenDoorRequestDto openDoorRequestDto = new OpenDoorRequestDto();

        openDoorRequestDto.setOpenDoor(openDoorServiceImpl.getOpenDoorByTeacher(teacherId));
        openDoorRequestDto.setParentId(loggedInUser.getId());
        openDoorRequestDto.setOpenDoorId(openDoorRequestDto.getOpenDoor().getOpenDoorId());

        LocalDate date = LocalDate.now();
        TemporalField woy = WeekFields.ISO.weekOfWeekBasedYear();
        if (date.getDayOfWeek().getValue() > openDoorRequestDto.getOpenDoor().getDayOfTheWeek()) {
            openDoorRequestDto.setStartWeek(date.get(woy) + 1);
        } else {
            openDoorRequestDto.setStartWeek(date.get(woy));
        }

        String dayOfTheWeek = daysOfTheWeek[openDoorRequestDto.getOpenDoor().getDayOfTheWeek() - 1];
        int year = date.getYear();
        model.addAttribute("openDoorRequestDto", openDoorRequestDto);
        model.addAttribute("dayOfTheWeek", dayOfTheWeek);
        model.addAttribute("year", year);

        return "parent/opendoor";
    }

    /**
     * Based on data gained through UI, saves a open door request to database.
     *
     * @param openDoorRequestDto OpenDoorRequestDto.class Object that carry data
     * about request from view to controller.
     * @param result BindingResult check if binding was done correctly
     * @return Redirects to view in folder parent with name opendoors
     */
    @PostMapping("/opendoorrequest")
    public String saveOpenDoorRequest(@ModelAttribute("openDoorRequestDto") OpenDoorRequestDto openDoorRequestDto, BindingResult result) {

        if (result.hasErrors()) {
            throw new RuntimeException("Wrong input!!! Try again!");
        }

        openDoorRequestServiceImpl.saveOpenDoorRequest(openDoorRequestDto);

        return "redirect:/parent/opendoors";
    }

}
