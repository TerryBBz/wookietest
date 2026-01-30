package com.ldnr.wookieAirlines.controllers;

import com.ldnr.wookieAirlines.models.FlyingCrew;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import com.ldnr.wookieAirlines.models.enums.CrewStatusEnum;
import com.ldnr.wookieAirlines.filters.CrewFilter;
import com.ldnr.wookieAirlines.services.FighterService;
import com.ldnr.wookieAirlines.services.FlyingCrewService;
import com.ldnr.wookieAirlines.services.AssignedPilotService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flyingcrew")
public class FlyingCrewController {

    private final FlyingCrewService flyingCrewService;
    private final AssignedPilotService assignedPilotService;
    private final FighterService fighterService;

    public FlyingCrewController(
            FlyingCrewService flyingCrewService,
            AssignedPilotService assignedPilotService,
            FighterService fighterService
    ) {
        this.flyingCrewService = flyingCrewService;
        this.assignedPilotService = assignedPilotService;
        this.fighterService = fighterService;
    }

    @GetMapping
    public String list(
            @ModelAttribute CrewFilter filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FlyingCrew> flyingCrewsPage = flyingCrewService.findPaginatedWithFilter(filter, pageable);

        model.addAttribute("flyingCrewsPage", flyingCrewsPage);
        model.addAttribute("filter", filter);
        model.addAttribute("statusOptions", CrewStatusEnum.values());

        return "flyingCrew";
    }

    @GetMapping("/add")
    public String addFlyingCrew(Model model) {
        model.addAttribute("flyingCrew", new FlyingCrew());

        // Récupérer uniquement les pilots en attente
        model.addAttribute("availablePilots", assignedPilotService.getByStatus(AssignedPilotStatusEnum.WAITING));

        // Récupérer uniquement les fighters opérationnels ou en maintenance mais opérationnels
        model.addAttribute("availableFighters", fighterService.findAll());

        return "flyingcrew-create-form";
    }

    @PostMapping("/save")
    public String saveFlyingCrew(
            @RequestParam String crewName,
            @RequestParam Long fighterId,
            @RequestParam Long pilot1Id,
            @RequestParam(required = false) Long pilot2Id) {

        flyingCrewService.createCrewWithAssignments(crewName, fighterId, pilot1Id, pilot2Id);

        return "redirect:/flyingcrew";
    }

}
