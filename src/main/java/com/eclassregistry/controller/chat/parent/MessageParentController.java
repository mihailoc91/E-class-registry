/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.chat.parent;

import com.eclassregistry.service.impl.MessageServiceImpl;
import com.eclassregistry.service.impl.TeacherServiceImpl;
import com.eclassregistry.shared.dto.MessageDto;
import com.eclassregistry.shared.dto.UserDto;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Grupa1
 */
@Controller
@SessionAttributes("loggedInUser")
public class MessageParentController {

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    @Autowired
    MessageServiceImpl messageServiceImpl;

    /**
     * Shows all messages between selected teacher and logged in parent, if they
     * exists and allows to a parent to send a message to already selected
     * teacher.
     *
     * @param loggedInUser UserDto.class that represents the logged in user
     * (Parent). This is a session attribute
     * @param model ModelMap object that carry data to the view
     * @param teacherId int that represents the selected teacher
     * @return View in folder parent with name message
     */
    @GetMapping("/parent/message")
    public String messagePage(@ModelAttribute("loggedInUser") UserDto loggedInUser, ModelMap model,
             @RequestParam(name = "teacher", required = true) Integer teacherId) {
        MessageDto messageDto = new MessageDto();
        messageDto.setParentId(loggedInUser.getId());
        messageDto.setSenderStatus(loggedInUser.getStatus());
        messageDto.setTeacherId(teacherId);

        List<MessageDto> allMessagesBetweenParentAndTeacher = messageServiceImpl.getAllMessagesBetweenParentAndTeacher(loggedInUser.getId(), teacherId);

        model.addAttribute("messageDto", messageDto);
        model.addAttribute("allMessagesBetweenParentAndTeacher", allMessagesBetweenParentAndTeacher);
        return "parent/message";
    }

    /**
     * Shows all chats between logged in user (parent) and teachers. If the chat
     * exists then it shows only the name of the teacher with who he (logged in)
     * chatted and the last message that was sent. Also it allows to logged in
     * user(parent) to start a new chat(send a message) to selected teacher.
     *
     * @param loggedInUser UserDto.class that represents the logged in user
     * (Parent). This is a session attribute
     * @param model ModelMap object that carry data to the view
     * @return View in folder parent with name message
     */
    @GetMapping("/parent/messages")
    public String messagesPage(@ModelAttribute("loggedInUser") UserDto loggedInUser, ModelMap model) {

        List<UserDto> allTeachersForMessages = teacherServiceImpl.getTeachersForParent(loggedInUser.getId());
        List<MessageDto> allChats = new ArrayList<>();
        for (int i = 0; i < allTeachersForMessages.size(); i++) {
            MessageDto messageDto = messageServiceImpl.getChatForParent(loggedInUser.getId(), allTeachersForMessages.get(i).getId());
            if (messageDto != null) {
                allChats.add(messageDto);
            }
        }

        model.addAttribute("allTeachersForMessages", allTeachersForMessages);
        model.addAttribute("allChats", allChats);
        return "parent/messages";
    }

    /**
     * Receive data trough MessageDto object, check if binding with
     * ModelAtribute(MessageDto object) hasErrors, if not then it saves (create
     * ) Message object to database.
     *
     * @param model ModelMap object that carry data to the view
     * @param messageDto Object of MessageDto.class, stores data about new
     * MessageEntity.class that user entered through UI
     * @param result Object of BindingResult that checks if there are errors in
     * binding
     * @return Redirect to view parent/message
     */
    @PostMapping("/parent/sendmessage")
    public String sendMessage(ModelMap model, @Valid @ModelAttribute MessageDto messageDto, BindingResult result) {

        if (result.hasErrors()) {
            return "parent/parent";
        }
        messageServiceImpl.saveMessage(messageDto);

        return "redirect:message?teacher=" + messageDto.getTeacherId();
    }
}
