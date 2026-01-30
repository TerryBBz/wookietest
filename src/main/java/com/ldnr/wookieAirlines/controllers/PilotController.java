package com.ldnr.wookieAirlines.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ldnr.wookieAirlines.filters.PilotFilter;
import com.ldnr.wookieAirlines.models.Pilot;
import com.ldnr.wookieAirlines.models.Mission;
import com.ldnr.wookieAirlines.models.RaceType;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;
import com.ldnr.wookieAirlines.repositories.RaceTypeRepository;
import com.ldnr.wookieAirlines.services.PilotService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pilot")
public class PilotController {

    private final PilotService pilotService;
    private final RaceTypeRepository raceTypeRepository;

    public PilotController(PilotService pilotService, RaceTypeRepository raceTypeRepository) {
        this.pilotService = pilotService;
        this.raceTypeRepository = raceTypeRepository;
    }


    @GetMapping
    public String listPilots(
            @ModelAttribute("pilotFilter") PilotFilter pilotFilter, // filtre rempli à partir du formulaire
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {
        PilotStatusEnum status = pilotFilter.getStatus(); // Requête arrivée on stock dans une var locale
        String search = pilotFilter.getSearch(); // Recherche par nom

        Page<Pilot> pilotPage;

        if (status != null && search != null && !search.trim().isEmpty()) {
            // Filtre par statut ET recherche par nom
            pilotPage = pilotService.findByStatusAndNameContaining(status, search.trim(), PageRequest.of(page, size));
        } else if (status != null) {
            // Filtre selon status AVAILABLE, WOUNDED, etc.
            pilotPage = pilotService.findByStatus(status, PageRequest.of(page, size));
        } else if (search != null && !search.trim().isEmpty()) {
            // Recherche par nom uniquement
            pilotPage = pilotService.findByNameContaining(search.trim(), PageRequest.of(page, size));
        } else {
            // Tous les pilotes
            pilotPage = pilotService.findPaginated(PageRequest.of(page, size));
        }

        // Calcul des grades pour chaque pilote
        Map<Long, String> pilotGrades = new HashMap<>();

        for (Pilot pilot : pilotPage.getContent()) {
            String grade = pilotService.calculateGrade(pilot);
            pilotGrades.put(pilot.getIdPilot(), grade);
        }

        model.addAttribute("pilots", pilotPage.getContent());
        model.addAttribute("page", pilotPage);
        model.addAttribute("pilotFilter", pilotFilter);
        model.addAttribute("pilotGrades", pilotGrades);

        return "pilot";
    }

    @GetMapping("/add")
    public String addPilot(Model model) {
        List<RaceType> raceTypes = raceTypeRepository.findAll();
        model.addAttribute("pilot", new Pilot());
        model.addAttribute("raceTypes", raceTypes);
        return "pilot-create-form";
    }

    @PostMapping("/save")
    public String savePilot(@ModelAttribute Pilot pilot,
                           @RequestParam(required = false) Long raceId) {

        // Gérer la relation race si un ID est fourni
        if (raceId != null && raceId > 0) {
            RaceType race = raceTypeRepository.findById(raceId).orElse(null);
            pilot.setRace(race);
        }

        pilotService.save(pilot);
        return "redirect:/pilot";
    }

    @GetMapping("/view/{id}")
    public String viewPilot(@PathVariable Long id, Model model) {
        List<RaceType> raceTypes = raceTypeRepository.findAll();
        Pilot pilot = pilotService.getById(id);
        String grade = pilotService.calculateGrade(pilot);
        List<Mission> pilotMissions = pilotService.getMissionsForPilot(id);

        model.addAttribute("pilot", pilot);
        model.addAttribute("raceTypes", raceTypes);
        model.addAttribute("pilotGrade", grade);
        model.addAttribute("pilotMissions", pilotMissions);
        return "pilot-update-form";
    }

    @PostMapping("/update")
    public String updatePilot(@ModelAttribute Pilot pilot,
                             @RequestParam(required = false) Long raceId) {

        // Gérer la relation race si un ID est fourni
        if (raceId != null && raceId > 0) {
            RaceType race = raceTypeRepository.findById(raceId).orElse(null);
            pilot.setRace(race);
        }

        pilotService.update(pilot);
        return "redirect:/pilot";
    }

    @GetMapping("/edit/{id}")
    public String editPilot(@PathVariable Long id, Model model) {
        List<RaceType> raceTypes = raceTypeRepository.findAll();
        model.addAttribute("pilot", pilotService.getById(id));
        model.addAttribute("raceTypes", raceTypes);
        return "pilot-create-form";
    }
}
