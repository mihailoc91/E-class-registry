package com.eclassregistry.controller.admin;

import com.eclassregistry.service.impl.AnnouncementServiceImpl;
import com.eclassregistry.service.impl.ClassServiceImpl;
import com.eclassregistry.shared.dto.AnnouncementDto;
import com.eclassregistry.shared.dto.ClassDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Grupa1
 */
@Controller
@RequestMapping("/admin")

public class AnnouncementController {

    @Autowired
    AnnouncementServiceImpl announcementServiceImpl;

    @Autowired
    ClassServiceImpl classServiceImpl;

    @GetMapping("/announcement")
    public String announcementPage(ModelMap model, @RequestParam(name = "id", required = false) String id) {
        AnnouncementDto announcementDto = new AnnouncementDto();

        if (id != null) {
            announcementDto = announcementServiceImpl.getAnnouncement(Integer.parseInt(id));
        }

        model.addAttribute("announcementDto", announcementDto);
        List<ClassDto> allClasses = classServiceImpl.getAllClasses();
        model.addAttribute("allClasses", allClasses);

        return "admin/announcement";
    }

    @PostMapping("/announcement")
    public String announcementSave(ModelMap model, @ModelAttribute("announcementDto") AnnouncementDto announcementDto, BindingResult result) {

        announcementServiceImpl.saveAnnouncement(announcementDto);
        return "redirect:/admin/announcements";
    }

    @GetMapping("/announcements")
    public String announcementsPage(ModelMap model, @RequestParam(name = "page", defaultValue = "0") String page, @RequestParam(name = "members", defaultValue = "10") String members) {

        List<AnnouncementDto> allAnnouncementsDto = announcementServiceImpl.getAllAnnouncements(Integer.valueOf(page), Integer.valueOf(members));

        model.addAttribute("allAnnouncementsDto", allAnnouncementsDto);

        return "admin/announcements";
    }

    @GetMapping("/announcement/remove")
    public String deleteAnnouncement(ModelMap model, @RequestParam(name = "id", required = true) String id) {

        announcementServiceImpl.deleteAnnouncement(Integer.parseInt(id));
        return "redirect:/admin/announcements";
    }

}
