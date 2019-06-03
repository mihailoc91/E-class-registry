/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller;

import com.eclassregistry.service.impl.UserServiceImpl;
import com.eclassregistry.shared.dto.UserDto;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Grupa1
 */
@SessionAttributes("loggedInUser")
@Controller
public class WelcomeController {

    @Autowired
    UserServiceImpl userServiceImpl;

    //Adding model attribute - stores data about loggeding user
    @ModelAttribute("loggedInUser")
    public UserDto setUpLoggedInUser() {
        return new UserDto();
    }

    Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    /**
     * Administrator welcome page
     *
     * @return String that represents administrators welcome page
     */
    @GetMapping("/admin/welcome")
    public String welcomeAdmin() {

        return "admin/admin";
    }
    
    /**
     * Director welcome page
     *
     * @return String that represents directors welcome page
     */
    @GetMapping("/director/welcome")
    public String welcomeDirector() {

        return "director/director";
    }

    /**
     * Parent welcome page
     *
     * @return String that represents parents welcome page
     */
    @GetMapping("/parent/welcome")
    public String welcomeParent() {

        return "parent/parent";
    }

    /**
     * Teacher welcome page
     *
     * @return String that represents parents welcome page
     */
    @GetMapping("/teacher/welcome")
    public String welcomeTeacher() {

        return "teacher/teacher";
    }

    /**
     * Index page.
     *
     * @return String that represents index page
     */
    @GetMapping("/")
    public String welcomePage() {

        return "index";
    }

    /**
     * Contact page
     *
     * @return String that represents contact page
     */
    @GetMapping("/contact")
    public String contactPage() {

        return "contact";
    }

    /**
     * About page
     *
     * @return String that represents about page
     */
    @GetMapping("/about")
    public String aboutPage() {

        return "about";
    }

    /**
     * After successful login, based on status of logged in user redirects it to
     * proper welcome page and adds data about logged in user in form of
     * UserDto.class to session attribute
     *
     * @param user Logged in principal
     * @param model ModelMap object that carry data to the view
     * @param loggedInUser UserDto object thats placed as session attribute
     * @return Redirects to a suiting welcome page for logged in status
     */
    @GetMapping("/welcome")
    public String welcome(@AuthenticationPrincipal User user, ModelMap model, @ModelAttribute("loggedInUser") UserDto loggedInUser) {

        //smestanje podataka o ulogovanom korisniku u session atribut
        loggedInUser = userServiceImpl.getUserByEmail(user.getUsername());
        model.addAttribute("loggedInUser", loggedInUser);

        //redirekcija ka welcom page u odnosu na status ulogovanog korisnika
        switch (loggedInUser.getStatus()) {
            case 1:
                return "redirect:admin/welcome";
            case 2:
                return "redirect:director/welcome";
            case 3:
                return "redirect:teacher/welcome";
            case 4:
                return "redirect:parent/welcome";

        }

        return "index";

    }

    /**
     * Sign out method, when activated it sets cookies as blank, invalidate the
     * current session, clears the model and logout the user. It redirects to
     * index page.
     *
     * @param model ModelMap object that carry data to the view
     * @param loggedInUser UserDto object thats placed as session attribute
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return Redirects to index page.
     */
    @PostMapping("/signout")
    public String signout(ModelMap model, @ModelAttribute("loggedInUser") UserDto loggedInUser,
            HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        request.getSession().invalidate();
        response.addCookie(new Cookie("JSESSIONID", ""));
        response.addCookie(new Cookie("remember-me", ""));
        model.clear();

        return "redirect:/";

    }

    /**
     * About page
     *
     * @return String that represents about page
     */
    @GetMapping("/welcome_director")
    public String wdirectorPage() {

        return "welcome_director";
    }

}
