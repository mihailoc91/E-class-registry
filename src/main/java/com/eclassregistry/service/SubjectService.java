/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.service;

import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.shared.dto.SubjectDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface SubjectService {

    SubjectDto save(SubjectDto subjectDto);

    List<SubjectDto> getAllSubjects(int pageNumber, int membersNumber);

    SubjectDto getSubject(int id);

    void deleteSubject(int id);

    SubjectEntity getSubjectById(int id);

    List<SubjectDto> getAllSubjects();

    List<SubjectDto> getAllSubjectsFromTeacherId(int Id);
}
