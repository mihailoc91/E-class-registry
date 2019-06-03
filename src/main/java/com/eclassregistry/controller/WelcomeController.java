/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Grupa1
 */
@Controller
public class WelcomeController {
    
    @GetMapping("/admin")
    public String welcomeAdmin(){
        return "admin";
    }
        /**
     * Administrator start page.
     *
     * @return String that represents administrator start page
     */
    @GetMapping("/")
    public String welcomePage() {

        return "index";
    }
        /**
     * Administrator start page.
     *
     * @return String that represents administrator start page
     */
    @GetMapping("/contact")
    public String contactPage() {

        return "contact";
    }
      /**
     * Administrator start page.
     *
     * @return String that represents administrator start page
     */
     @GetMapping("/about")
    public String aboutPage() {

        return "about";
    }
}

