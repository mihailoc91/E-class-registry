
package com.eclassregistry.service.impl;

import com.eclassregistry.model.entity.AnnouncementEntity;
import com.eclassregistry.model.entity.ClassEntity;
import com.eclassregistry.model.repositories.AnnouncementRepository;
import com.eclassregistry.service.AnnouncementService;
import com.eclassregistry.shared.dto.AnnouncementDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Grupa1
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AnnouncementServiceImpl implements AnnouncementService{
    
    @Autowired
    AnnouncementRepository announcementRepository;
    
    @Autowired
    ClassServiceImpl classServiceImpl;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    
    @Override
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false)
    public AnnouncementDto saveAnnouncement(AnnouncementDto announcementDto) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        
        BeanUtils.copyProperties(announcementDto, announcementEntity);
        announcementEntity.setAnnouncement(announcementDto.getAnnouncement().toString());
        announcementEntity.setDate(LocalDateTime.now());
        
        List<ClassEntity> classes = new ArrayList<>();
        for(Integer i : announcementDto.getClasses()){
            ClassEntity classEntity = classServiceImpl.findClassById(i);
            classes.add(classEntity);
        }
        announcementEntity.setClasses(classes);
        
        AnnouncementEntity storedAnnouncementEntity = announcementRepository.save(announcementEntity);
        
        AnnouncementDto returnValue = new AnnouncementDto();
        BeanUtils.copyProperties(storedAnnouncementEntity, returnValue);
        returnValue.setTitle(storedAnnouncementEntity.getTitle());
        return returnValue;
    }

    
    
    @Override
    public AnnouncementDto getAnnouncement(int id) {
        AnnouncementEntity announcementEntity = announcementRepository.getOne(id);

        AnnouncementDto announcementDto = new AnnouncementDto();
        BeanUtils.copyProperties(announcementEntity, announcementDto);
        
        
        List<Integer> integers = announcementRepository.getClassIdFromAnnouncementId(id);
        announcementDto.setClasses(integers);
        
        announcementDto.setFormatedDate(announcementEntity.getDate().format(formatter));
        
        return announcementDto;
    }

    @Override
    public AnnouncementDto updateAnnouncement(AnnouncementDto announcementDto) {
       return null;
    }

    @Override
    public void deleteAnnouncement(int id) {
       announcementRepository.deleteById(id);
    }

    @Override
    public AnnouncementEntity getAnnouncementById(int id) {
       
        
       return null;
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncements(int pageNumber, int membersNumber) {
        List<AnnouncementDto> allAnnouncements = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNumber, membersNumber);
        Page<AnnouncementEntity> allAnnouncementsEntity = announcementRepository.findAll(pageable);

        for (AnnouncementEntity announcement : allAnnouncementsEntity) {
            AnnouncementDto announcementDto = new AnnouncementDto();
            BeanUtils.copyProperties(announcement, announcementDto);
            
            announcementDto.setFormatedDate(announcement.getDate().format(formatter));
            
            allAnnouncements.add(announcementDto);
        }

        return allAnnouncements;
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncements() {
        List<AnnouncementDto> allAnnouncementsDto = new ArrayList<>();

        List<AnnouncementEntity> allAnnouncementsEntitys = (List<AnnouncementEntity>) announcementRepository.findAll();
        //populate AnnouncementDto with data from database
        for (AnnouncementEntity announcementEntity : allAnnouncementsEntitys) {
            AnnouncementDto announcementDto = new AnnouncementDto();
            List<Integer> classes = new ArrayList<>();

            BeanUtils.copyProperties(announcementEntity, announcementDto);
            //populate AnnouncementDto.classes with Id's of the classes that have current announcement 
            for (ClassEntity classEntity : announcementEntity.getClasses()) {
                classes.add(classEntity.getClassId());
            }
            announcementDto.setClasses(classes);

            allAnnouncementsDto.add(announcementDto);
        }

        return allAnnouncementsDto;
    }

    
}
