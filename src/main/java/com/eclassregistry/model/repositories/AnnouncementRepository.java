
package com.eclassregistry.model.repositories;

import com.eclassregistry.model.entity.AnnouncementEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grupa1
 */

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer> {
    @Query(value = "Select announcements_classes.class_id FROM announcements_classes WHERE announcements_classes.announcement_id =?1",nativeQuery = true)
    List<Integer> getClassIdFromAnnouncementId(int announcementId);
    
    @Query(value = "Select announcements_classes.announcement_id FROM announcements_classes WHERE announcements_classes.class_id =?1",nativeQuery = true)
    List<Integer>getAnnouncementIdWhereClassId(int classId);
}
