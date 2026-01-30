package com.ldnr.wookieAirlines.controllers;

import com.ldnr.wookieAirlines.models.Pilot;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;
import com.ldnr.wookieAirlines.services.PilotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemorialController {

    private final PilotService pilotService;

    public MemorialController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping("/memorial")
    public String showMemorial(Model model) {

        // Récupérer tous les pilotes morts
        List<Pilot> deadPilots = pilotService.findByStatus(
                PilotStatusEnum.DEAD,
                null    // Pas de pagination → on veut toute la liste
        ).getContent();

        // Calculer le grade de chaque pilote
        Map<Long, String> pilotGrades = new HashMap<>();
        for (Pilot pilot : deadPilots) {
            String grade = pilotService.calculateGrade(pilot);
            pilotGrades.put(pilot.getIdPilot(), grade);
        }

        model.addAttribute("pilots", deadPilots);
        model.addAttribute("pilotGrades", pilotGrades);

        return "memorial";
    }
}
