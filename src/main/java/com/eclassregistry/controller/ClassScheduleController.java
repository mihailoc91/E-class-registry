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
public class ClassScheduleController {
    @GetMapping("/class_schedule")
    public String classSchedule() {

        return "class_schedule";
    }
}
