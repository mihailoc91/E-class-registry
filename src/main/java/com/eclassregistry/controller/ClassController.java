/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller;


import com.eclassregistry.service.impl.ClassServiceImpl;
import com.eclassregistry.service.impl.TeacherServiceImpl;
import com.eclassregistry.shared.dto.ClassDto;
import com.eclassregistry.shared.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mihailo
 */
@Controller
public class ClassController {
    
    @Autowired
    ClassServiceImpl classServiceImpl;

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    /**
     * Shows a page so that administrator can insert data necessary for creating
     * a new class or if that class already exists so it can update it. It also
     * sends to the page information about Teachers that don't have a class.
     *
     * @param id String that needs to be converted later to int, represents id
     * of already existing class. It's not required.
     * @param model ModelMap object that carry data to the view
     * @return Return a view class
     */
    @GetMapping("/class")
    public String classPage(ModelMap model, @RequestParam(name = "id", required = false) String id
    ) {

        ClassDto classDto = new ClassDto();
        List<UserDto> allAvailableTeachers = teacherServiceImpl.allAvailableTeachers();
        //list of all teachers without a class
        classDto.setListOfAvailableTeachers(allAvailableTeachers);
        //Checks if the class already exists, if it does then it populate the fileds with that value
        if (id != null) {
            classDto = classServiceImpl.getClassById(Integer.valueOf(id));
            UserDto userDto = teacherServiceImpl.getTeacherById(classDto.getTeacherId());
            allAvailableTeachers.add(userDto);
            classDto.setListOfAvailableTeachers(allAvailableTeachers);
        }

        model.addAttribute("classDto", classDto);

        return "class";
    }

    /**
     * Page which shows all classes, and it implements paging. It has options
     * for edit or delete of selected class.
     *
     * @param model ModelMap object that carry data to the view
     * @param page String that needs to be converted later to int, represent the
     * desired page number. Default value is 0.
     * @param members String that needs to be converted later to int, represent
     * the number of results shown in one request. Default value will be 10.
     * @return View classes
     */
    @GetMapping("/classes")
    public String probaPage(ModelMap model, @RequestParam(name = "page", defaultValue = "0") String page,
            @RequestParam(name = "members", defaultValue = "2") String members) {

        int pageNumber = Integer.valueOf(page);
        int membersNumber = Integer.valueOf(members);
        List<ClassDto> allClasses = classServiceImpl.getAllClasses(pageNumber, membersNumber);

        model.addAttribute("allClasses", allClasses);

        return "classes";
    }

    /**
     * Receive data trough ClassDto object, check if binding with
     * ModelAtribute(ClassDto object) hasErrors, if not then it saves (create
     * new if it doesn't exist or update it if it does exists in database) Class
     * object to database.
     *
     * @param model ModelMap object that carry data to the view
     * @param classDto Object of ClassDto.class, stores data about new
     * ClassEntity.class that user entered through UI
     * @param result Object of BindingResult that checks if there are errors in
     * binding
     * @return Redirect to view classes
     */
    @PostMapping("/class")
    public String classSave(ModelMap model, ClassDto classDto, BindingResult result) {

        if (result.hasErrors()) {
            return "index";
        }

        classServiceImpl.saveClass(classDto);

        return "redirect:classes";
    }

    /**
     * Deletes the selected class from database.
     *
     * @param model ModelMap object that carry data to the view
     * @param id String that needs to be converted into a int, and it represent
     * the ID of the selected class that needs to be deleted from database.
     * @return Redirect to view classes
     */
    @GetMapping("/class/remove")
    public String deleteClass(ModelMap model,
            @RequestParam(name = "id", required = true) String id) {

        classServiceImpl.deleteClass(Integer.valueOf(id));

        return "redirect:../classes";
    }
            
}
