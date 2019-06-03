/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.admin;

import com.eclassregistry.service.impl.ClassServiceImpl;
import com.eclassregistry.service.impl.ParentServiceImpl;
import com.eclassregistry.service.impl.StudentServiceImpl;
import com.eclassregistry.shared.dto.ClassDto;
import com.eclassregistry.shared.dto.StudentDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Grupa1
 */
@Controller
@RequestMapping("/admin")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Autowired
    ClassServiceImpl classServiceImpl;

    @Autowired
    ParentServiceImpl parentServiceImpl;

    /**
     * Populates the view with data necessary for entering new/update student.
     * Sends to a view List of all parents and classes available and
     * StudentDto.class that can be empty (if its a new student) or populated
     * with data for already existing student from database that needs to be
     * updated.
     *
     * @param model ModelMap object that carry data to the view
     * @param id int that represents id of already existing student in database,
     * not required, necessary for update of the student
     * @return View in folder admin with name student
     */
    @GetMapping("/student")
    public String studentPage(ModelMap model, @RequestParam(name = "id", required = false) String id) {

        StudentDto studentDto = new StudentDto();
        List<ClassDto> allClasses = classServiceImpl.getAllClasses();
        List<UserDto> allParents = parentServiceImpl.getAllParents();

        if (id != null) {
            studentDto = studentServiceImpl.getStudentById(Integer.valueOf(id));
        }
        model.addAttribute("allParents", allParents);
        model.addAttribute("allClasses", allClasses);
        model.addAttribute("studentDto", studentDto);
        return "admin/student";
    }

    /**
     * Populates the view with data about all students through a
     * List<StudentDto>. This method has paging, default value will be 10
     * members per page.
     *
     * @param model ModelMap object that carry data to the view
     * @param page Integer that represent current page, default value is 0
     * @param members Integer that represents number of students showed to a
     * user per page, default value will be 10
     * @return View in folder admin with name students
     */
    @GetMapping("/students")
    public String getStudentsPage(ModelMap model,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "members", defaultValue = "2") Integer members) {

        List<StudentDto> allStudentsDto = studentServiceImpl.getAllStudents(page, members);
        model.addAttribute("allStudentsDto", allStudentsDto);

        return "admin/students";
    }

    /**
     * Deletes selected student with sent Id.
     *
     * @param model ModelMap object that carry data to the view
     * @param id Integer that represents unique id in database for selected
     * student, it is required
     * @return Redirects to view in folder admin with name students
     */
    @GetMapping("/student/remove")
    public String deleteStudent(ModelMap model, @RequestParam(name = "id", required = true) Integer id) {

        if (id != null && id != 0) {
            studentServiceImpl.deleteStudent(id);
        }

        return "redirect:../admin/students";
    }

    /**
     * Saves a student with data entered through UI by admin. Data is carried
     * through ModelAttribute StudentDto.class, first it checks if
     * ModelAttribute is valid and if the binding has errors, if not then it
     * saves values to databse.
     *
     * @param model ModelMap object that carry data to the view
     * @param studentDto StudentDto.class that carry all data necessary for
     * saving student to database
     * @param result BindingResult that checks if binding has errors
     * @return Redirects to view in folder admin with name students
     */
    @PostMapping("/student")
    public String saveStudent(ModelMap model, @Valid @ModelAttribute StudentDto studentDto, BindingResult result) {

        if (result.hasErrors()) {
            throw new RuntimeException("Wrong input for student!");
        }

        studentServiceImpl.saveStudent(studentDto);

        return "redirect:../admin/students";
    }

}
