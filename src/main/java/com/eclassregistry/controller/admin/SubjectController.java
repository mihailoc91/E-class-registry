/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.admin;

import com.eclassregistry.service.impl.SubjectServiceImpl;
import com.eclassregistry.shared.dto.SubjectDto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Grupa1
 */
@Controller
@RequestMapping("/admin")
public class SubjectController {

    @Autowired
    SubjectServiceImpl subjectServiceImpl;

    /**
     * Shows a page so that administrator can insert data necessary for creating
     * a new subject if it didn't exist before, or if the subject already exists
     * then it sends data necessary to fill the fields with already existing
     * data so administrator can update them.
     *
     * @param model ModelMap object that carry data to the view
     * @param id String that needs to be converted later to int, represents id
     * of already existing subject. It's not required.
     * @return View subject
     */
    @GetMapping("/subject")
    public String subjectPage(ModelMap model, @RequestParam(name = "id", required = false) String id) {
        SubjectDto subjectDto = new SubjectDto();

        if (id != null) {
            subjectDto = subjectServiceImpl.getSubject(Integer.valueOf(id));
        }

        model.addAttribute("subjectDto", subjectDto);
        return "admin/subject";
    }

    /**
     * Based on data received from form through @ModelAtribute SubjectDto
     * subjectDto, and if its valid, saves a subject(if that subject didn't
     * exist creates a new one, if it did then it updates it).
     *
     * @param model ModelMap object that carry data to view
     * @param subjectDto SubjectDto object that carry data that was insert by
     * the administrator
     * @param result BindingResult that checks for errors (validates) subjectDto
     * object
     * @return Redirect to a view subjects
     */
    @PostMapping("/subject")
    public String subjectSave(ModelMap model, @Valid @ModelAttribute SubjectDto subjectDto, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/subjects";
        }

        subjectServiceImpl.save(subjectDto);

        return "redirect:admin/subjects";
    }

    /**
     * Returns a list of SubjectDto object that represents all Subjects. It
     * allows administrator to see all Subjects and update them or delete them.
     * Allows paging.
     *
     * @param model ModelMap object that carry data to the view
     * @param page String that needs to be converted later to int, represent the
     * desired page number. Default value is 0.
     * @param members String that needs to be converted later to int, represent
     * the number of results shown in one request. Default value will be 10.
     * @return View subjects
     */
    @GetMapping("/subjects")
    public String subjectsPage(ModelMap model, @RequestParam(name = "page", defaultValue = "0") String page,
            @RequestParam(name = "members", defaultValue = "10") String members) {

        List<SubjectDto> allSubjectDto = subjectServiceImpl.getAllSubjects(Integer.valueOf(page), Integer.valueOf(members));

        model.addAttribute("allSubjectDto", allSubjectDto);

        return "/admin/subjects";
    }

    /**
     * Deletes selected subject.
     *
     * @param model ModelMap object that carry data to the view
     * @param id String that needs to be converted later to int, represents the
     * id of subject that needs to be deleted.
     * @return Redirects to a view subjects
     */
    @GetMapping("/subject/remove")
    public String deleteSubject(ModelMap model, @RequestParam(name = "id", required = true) String id) {

        subjectServiceImpl.deleteSubject(Integer.valueOf(id));

        return "redirect:../admin/subjects";
    }
}
