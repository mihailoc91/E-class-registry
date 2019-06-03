/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.MessageEntity;
import com.eclassregistry.model.entity.ParentEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import com.eclassregistry.model.repositories.MessageRepository;
import com.eclassregistry.service.MessageService;
import com.eclassregistry.shared.dto.MessageDto;
import com.eclassregistry.shared.dto.UserDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grupa1
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    @Autowired
    ParentServiceImpl parentServiceImpl;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Saves a message to database.
     *
     * @param messageDto Object of MessageDto.class that carries data gained
     * through UI from user
     */
    @Override
    public void saveMessage(MessageDto messageDto) {
        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setMessage(messageDto.getMessage());
        messageEntity.setParent(parentServiceImpl.findParentById(messageDto.getParentId()));
        messageEntity.setTeacher(teacherServiceImpl.getTeacher(messageDto.getTeacherId()));
        messageEntity.setDate(LocalDateTime.now());
        messageEntity.setSenderStatus(messageDto.getSenderStatus());

        messageRepository.save(messageEntity);
    }

    /**
     * Returns chat (if it exists) from database for a parent/teacher, returns
     * the name of Teacher/Parent with who logged in Parent/Teacher chatted, and
     * last message of their chat.
     *
     * @param parentId
     * @param teacherId
     * @return
     */
    @Override
    public MessageDto getChatForParent(int parentId, int teacherId) {
        TeacherEntity teacherEntity = teacherServiceImpl.getTeacher(teacherId);
        ParentEntity parentEntity = parentServiceImpl.findParentById(parentId);
        MessageEntity messageEntity = messageRepository.findFirstByTeacherAndParentOrderByDateDesc(teacherEntity, parentEntity);

        if (messageEntity == null) {
            return null;
        }
        UserDto teacherDto = new UserDto();
        BeanUtils.copyProperties(teacherEntity, teacherDto);

        UserDto parentDto = new UserDto();
        BeanUtils.copyProperties(parentEntity, parentDto);

        MessageDto messageDto = new MessageDto();
        messageDto.setCommunicationId(messageEntity.getCommunicationId());
        messageDto.setDate(messageEntity.getDate());
        messageDto.setMessage(messageEntity.getMessage());
        messageDto.setSenderStatus(messageEntity.getSenderStatus());
        messageDto.setParent(parentDto);
        messageDto.setTeacher(teacherDto);

        return messageDto;
    }

    /**
     * Returns List<MessageDto> that represents all messages exchanged between
     * Parent and Teacher.
     *
     * @param parentId int that represents unique ID of ParentEntity.class
     * @param teacherId int that represents unique ID of TeacherEntity.class
     * @return List<MessageDto> all messages between them
     */
    @Override
    public List<MessageDto> getAllMessagesBetweenParentAndTeacher(int parentId, int teacherId) {
        TeacherEntity teacherEntity = teacherServiceImpl.getTeacher(teacherId);
        ParentEntity parentEntity = parentServiceImpl.findParentById(parentId);
        List<MessageEntity> messageEntitys = messageRepository.findAllByTeacherAndParent(teacherEntity, parentEntity);

        List<MessageDto> allMessagesBetweenParentAndTeacher = new ArrayList<>();

        for (MessageEntity messageEntity : messageEntitys) {
            if (messageEntity == null) {
                continue;
            }
            UserDto teacherDto = new UserDto();
            BeanUtils.copyProperties(teacherEntity, teacherDto);

            UserDto parentDto = new UserDto();
            BeanUtils.copyProperties(parentEntity, parentDto);

            MessageDto messageDto = new MessageDto();
            messageDto.setCommunicationId(messageEntity.getCommunicationId());
            messageDto.setDate(messageEntity.getDate());
            messageDto.setFormatedDate(messageEntity.getDate().format(formatter));
            messageDto.setMessage(messageEntity.getMessage());
            messageDto.setSenderStatus(messageEntity.getSenderStatus());
            messageDto.setParent(parentDto);
            messageDto.setTeacher(teacherDto);

            allMessagesBetweenParentAndTeacher.add(messageDto);
        }

        return allMessagesBetweenParentAndTeacher;
    }

}
