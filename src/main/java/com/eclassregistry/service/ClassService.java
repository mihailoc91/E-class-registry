/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.ClassEntity;
import com.eclassregistry.shared.dto.ClassDto;
import java.util.List;

/**
 *
 * @author Mihailo
 */
public interface ClassService {
    void saveClass (ClassDto classDto);
    List<ClassDto> getAllClasses(int pageNumber, int membersNumber);
    List<ClassDto> getAllClasses();
    ClassDto getClassById(int id);
    ClassEntity findClassById(int id);
    void deleteClass(int id);
}
