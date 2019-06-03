/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.shared.dto.MessageDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface MessageService {

    void saveMessage(MessageDto messageDto);

    MessageDto getChatForParent(int parentId, int teacherId);

    List<MessageDto> getAllMessagesBetweenParentAndTeacher(int parentId, int teacherId);
}
