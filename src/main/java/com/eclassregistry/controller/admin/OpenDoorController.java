/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.admin;

import com.eclassregistry.service.impl.ClassServiceImpl;
import com.eclassregistry.service.impl.OpenDoorServiceImpl;
import com.eclassregistry.shared.dto.ClassDto;
import com.eclassregistry.shared.dto.OpenDoorDto;
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
public class OpenDoorController {

    @Autowired
    ClassServiceImpl classServiceImpl;

    @Autowired
    OpenDoorServiceImpl openDoorServiceImpl;

    /**
     * Sends data to view necessary for showing to admin. This method allows to
     * admin to select desired teacher, if selected teacher already has a open
     * door scheduled, sends data that populate the view, after that admin can
     * enter new values to the view (create new or update already existing term
     * for open door).
     *
     * @param model ModelMap object that carry data to the view
     * @param id int that represent unique id of the selected teacher in
     * database
     * @return view populated with necessary data in folder admin with name
     * opendoor
     */
    @GetMapping("/admin/opendoor")
    public String openDoorPage(ModelMap model, @RequestParam(name = "id", required = true, defaultValue = "0") Integer id) {
        OpenDoorDto openDoorDto;

        openDoorDto = openDoorServiceImpl.getOpenDoorByTeacher(id);

        if (openDoorDto == null) {
            openDoorDto = new OpenDoorDto();
            openDoorDto.setTeacherId(id);
        }

        List<ClassDto> allClassesAndTeachers = classServiceImpl.getAllClasses();
        model.addAttribute("allClassesAndTeachers", allClassesAndTeachers);
        model.addAttribute("openDoorDto", openDoorDto);
        return "admin/opendoor";
    }

    /**
     * Receives data entered through UI by admin, checks if binding with
     * ModelAtrribute (OpenDoorDto.class) has errors, if not it saves the term
     * for an open door.
     *
     * @param model ModelMap object that carry data to the view
     * @param openDoorDto OpenDoorDto.class that carry all informations from UI
     * to this method necessary for saving open door to database
     * @param result Redirects to the same page opened on the saved teacher
     * @return
     */
    @PostMapping("/admin/opendoor")
    public String saveOpenDoor(ModelMap model, @ModelAttribute("openDoorDto") OpenDoorDto openDoorDto, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/opendoor";
        }

        openDoorServiceImpl.saveOpenDoor(openDoorDto);

        return "redirect:/admin/opendoor?id=" + openDoorDto.getTeacherId();
    }

}
