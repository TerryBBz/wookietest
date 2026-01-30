package com.ldnr.wookieAirlines.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import com.ldnr.wookieAirlines.filters.MissionFilter;
import com.ldnr.wookieAirlines.models.Mission;
import com.ldnr.wookieAirlines.models.MissionType;
import com.ldnr.wookieAirlines.models.enums.MissionStatusEnum;
import com.ldnr.wookieAirlines.services.MissionService;
import com.ldnr.wookieAirlines.services.MissionTypeService;
import com.ldnr.wookieAirlines.services.FlyingCrewService;

/**
 *
 * @author Terry et Auriane
 */
@Controller
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;
    private final MissionTypeService missionTypeService;
    private final FlyingCrewService flyingCrewService;

    public MissionController(MissionService missionService,
                           MissionTypeService missionTypeService,
                           FlyingCrewService flyingCrewService) {
        this.missionService = missionService;
        this.missionTypeService = missionTypeService;
        this.flyingCrewService = flyingCrewService;
    }
    @GetMapping
    public String list(
            @ModelAttribute MissionFilter filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> missionPage = missionService.findPaginatedWithFilter(filter, pageable);

        model.addAttribute("missionPage", missionPage);
        model.addAttribute("filter", filter);
        model.addAttribute("statusOptions", MissionStatusEnum.values());

        return "mission";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("mission", new Mission());
        model.addAttribute("types", missionTypeService.findAll());
        model.addAttribute("flyingCrews", flyingCrewService.findAll());
        return "mission-create-form";
    }

    @PostMapping("/add")
    public String addMission(
            @ModelAttribute Mission mission,
            @RequestParam(value = "flyingCrewIds", required = false) String[] flyingCrewIds) {

        mission.setStatus(MissionStatusEnum.IN_PROGRESS);

        // Ajouter seulement les équipages réellement sélectionnés (non vides)
        if (flyingCrewIds != null) {
            for (String crewId : flyingCrewIds) {
                if (crewId != null && !crewId.trim().isEmpty()) {
                    // Convertir String "5" vers Long 5 (requis par getById)
                    Long id = Long.parseLong(crewId);
                    mission.getFlyingCrewList().add(flyingCrewService.getById(id));
                }
            }
        }

        missionService.save(mission);
        return "redirect:/mission";
    }

    @GetMapping("/{id}/finish")
    public String showFinishMission(@PathVariable Long id, Model model) {
        Mission mission = missionService.findById(id);
        if (mission.getStatus() != MissionStatusEnum.IN_PROGRESS) {
            return "redirect:/mission";
        }
        model.addAttribute("mission", mission);
        return "mission-done";
    }

    @PostMapping("/{id}/finish")
    public String finishMission(
            @PathVariable Long id,
            @RequestParam int flightTime,
            @RequestParam double successRate,
            @RequestParam Map<String, String> allParams
    ) {
        missionService.finishMissionWithIndividualStatuses(id, flightTime, successRate, allParams);
        return "redirect:/mission";
    }

    @GetMapping("/{id}/info")
    public String viewMission(@PathVariable Long id, Model model) {
        Mission mission = missionService.findById(id);
        model.addAttribute("mission", mission);
        return "mission-infos";
    }

}
