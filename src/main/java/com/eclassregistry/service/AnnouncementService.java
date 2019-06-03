package com.eclassregistry.service;

import com.eclassregistry.model.entity.AnnouncementEntity;
import com.eclassregistry.shared.dto.AnnouncementDto;
import java.util.List;

/**
 *
 * @author Grupa1
 */
public interface AnnouncementService {

    AnnouncementDto saveAnnouncement(AnnouncementDto announcementDto);

    AnnouncementDto getAnnouncement(int id);

    AnnouncementDto updateAnnouncement(AnnouncementDto announcementDto);

    void deleteAnnouncement(int id);

    AnnouncementEntity getAnnouncementById(int id);

    List<AnnouncementDto> getAllAnnouncements();

    List<AnnouncementDto> getAllAnnouncements(int pageNumber, int membersNumber);
}
