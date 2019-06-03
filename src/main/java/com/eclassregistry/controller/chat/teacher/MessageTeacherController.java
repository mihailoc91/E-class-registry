/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.chat.teacher;

import com.eclassregistry.service.impl.MessageServiceImpl;
import com.eclassregistry.service.impl.ParentServiceImpl;
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
public class MessageTeacherController {

    @Autowired
    ParentServiceImpl parentServiceImpl;

    @Autowired
    MessageServiceImpl messageServiceImpl;

    /**
     * Shows all messages between selected parent and logged in teacher, if they
     * exists and allows to a teacher to send a message to already selected
     * parent.
     *
     * @param loggedInUser UserDto.class that represents the logged in user
     * (Teacher). This is a session attribute
     * @param model ModelMap object that carry data to the view
     * @param parentId int that represents the selected parent
     * @return View in folder teacher with name message
     */
    @GetMapping("/teacher/message")
    public String messagePage(@ModelAttribute("loggedInUser") UserDto loggedInUser, ModelMap model,
             @RequestParam(name = "parent", required = true) Integer parentId) {
        MessageDto messageDto = new MessageDto();
        messageDto.setParentId(parentId);
        messageDto.setSenderStatus(loggedInUser.getStatus());
        messageDto.setTeacherId(loggedInUser.getId());

        List<MessageDto> allMessagesBetweenParentAndTeacher = messageServiceImpl.getAllMessagesBetweenParentAndTeacher(parentId, loggedInUser.getId());

        model.addAttribute("messageDto", messageDto);
        model.addAttribute("allMessagesBetweenParentAndTeacher", allMessagesBetweenParentAndTeacher);
        return "teacher/message";
    }

    /**
     * Shows all chats between logged in user (teacher) and parent. If the chat
     * exists then it shows only the name of the parent with who he (logged in)
     * chatted and the last message that was sent. Also it allows to logged in
     * user(teacher) to start a new chat(send a message) to selected parent.
     *
     * @param loggedInUser UserDto.class that represents the logged in user
     * (Teacher). This is a session attribute
     * @param model ModelMap object that carry data to the view
     * @return View in folder teacher with name message
     */
    @GetMapping("/teacher/messages")
    public String messagesPage(@ModelAttribute("loggedInUser") UserDto loggedInUser, ModelMap model) {

        List<UserDto> allParentsForMessages = parentServiceImpl.getParentsForTeacher(loggedInUser.getId());
        List<MessageDto> allChats = new ArrayList<>();
        for (int i = 0; i < allParentsForMessages.size(); i++) {
            MessageDto messageDto = messageServiceImpl.getChatForParent(allParentsForMessages.get(i).getId(), loggedInUser.getId());
            if (messageDto != null) {
                allChats.add(messageDto);
            }
        }

        model.addAttribute("allParentsForMessages", allParentsForMessages);
        model.addAttribute("allChats", allChats);
        return "teacher/messages";
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
    @PostMapping("/teacher/sendmessage")
    public String sendMessage(ModelMap model, @Valid @ModelAttribute MessageDto messageDto, BindingResult result) {

        if (result.hasErrors()) {
            return "teacher/teacher";
        }
        messageServiceImpl.saveMessage(messageDto);

        return "redirect:message?parent=" + messageDto.getParentId();
    }
}
