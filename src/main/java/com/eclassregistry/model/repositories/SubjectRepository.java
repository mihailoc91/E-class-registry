/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.SubjectEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */
@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer>{
    SubjectEntity findBySubjectId(int id);
    @Query(value="SELECT teachers_subjects.subject_id FROM teachers_subjects WHERE teachers_subjects.teacher_id = ?1",nativeQuery = true)
    List<Integer> findAllSubjectFromteacherId(int id);
}
