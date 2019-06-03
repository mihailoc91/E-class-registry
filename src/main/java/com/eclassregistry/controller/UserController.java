/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller;

import com.eclassregistry.service.impl.UserServiceImpl;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;
import javax.validation.Valid;
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
 * @author Mihailo
 */
@Controller
public class UserController {
    
    @Autowired
    UserServiceImpl userServiceImpl;

   

    /**
     * Returns a list of UserDto object that represents all
     * Users(Administrators,Directors,Teachers,Parents). It allows administrator
     * to see all Users and update them or delete them. Allows paging.
     *
     * @param model ModelMap object that carry data to the view
     * @param status String that needs to be converted later to int, represent
     * the status/role of desired users
     * @param page String that needs to be converted later to int, represent the
     * desired page number. Default value is 0.
     * @param members String that needs to be converted later to int, represent
     * the number of results shown in one request. Default value will be 10.
     * @return View users
     */
    @GetMapping("/users")
    public String getUsersPage(ModelMap model,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "page", defaultValue = "0") String page,
            @RequestParam(name = "members", defaultValue = "2") String members) {

        if (status != null) {
            int pageNumber = Integer.valueOf(page);
            int membersNumber = Integer.valueOf(members);
            int statusId = Integer.valueOf(status);
            List<UserDto> allUsersDto = userServiceImpl.getAllUsers(statusId, pageNumber, membersNumber);
            model.addAttribute("allUsersDto", allUsersDto);
        }

        return "users";
    }

    /**
     * Shows a page so that administrator can insert data necessary for creating
     * a new user if it didn't exist before, or if the user already exists then
     * it sends data necessary to fill the fields with already existing data so
     * administrator can update them.
     *
     * @param model ModelMap object that carry data to the view
     * @param id String that needs to be converted later to int, represents id
     * of already existing user. It's nor required.
     * @param status String that needs to be converted later to int, represents
     * status of already existing user. It's nor required.
     * @return View user
     */
    @GetMapping("/user")
    public String getUserPage(ModelMap model, @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "status", required = false) String status) {

        UserDto userDto = new UserDto();
        if (id != null && status != null) {
            userDto = userServiceImpl.getUserById(Integer.valueOf(status), Integer.valueOf(id));
        }
        model.addAttribute("userDto", userDto);

        return "user";
    }

    /**
     * Delete selected user.
     *
     * @param model ModelMap object that carry data to view
     * @param id String that needs to be converted to int later, represents the
     * id of the user
     * @param status String that needs to be converted to int later, represents
     * the status of user
     * @return Redirects to view users
     */
    @GetMapping("/user/remove")
    public String deleteUser(ModelMap model,
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "status", required = true) String status) {
        long longId = Long.valueOf(id);
        
        if (id != null && status != null) {
            userServiceImpl.deleteUser(Integer.valueOf(status), longId);
        }

        return ("redirect:../users?status=" + status);
    }

    /**
     * Based on data received from form through @ModelAtribute UserDto userDto,
     * and if its valid, saves user(if that user didn't exist creates a new one,
     * if it did then it updates it).
     *
     * @param model ModelMap object that carry data to view
     * @param userDto UserDto object that carry data that was insert by the
     * administrator
     * @param result BindingResult that checks for errors (validates) userDto
     * object
     * @return Redirect to a view users with status param set to match the
     * status of userDto
     */
    @PostMapping("/user")
    public String saveUser(ModelMap model,@Valid @ModelAttribute UserDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            return "index";
        }

        UserDto returnValue = userDto.getId() == 0 ? userServiceImpl.createUser(userDto) : userServiceImpl.updateUser(userDto);

        return ("redirect:users?status=" + returnValue.getStatus());
    }
    
    
}
