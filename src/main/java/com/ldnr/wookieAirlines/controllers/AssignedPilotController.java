package com.ldnr.wookieAirlines.controllers;

import com.ldnr.wookieAirlines.filters.AssignedPilotFilter;
import com.ldnr.wookieAirlines.models.AssignedPilot;
import com.ldnr.wookieAirlines.models.Pilot;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;
import com.ldnr.wookieAirlines.services.AssignedPilotService;
import com.ldnr.wookieAirlines.services.PilotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assignedpilot")
public class AssignedPilotController {

    private final AssignedPilotService assignedPilotService;
    private final PilotService pilotService;

    public AssignedPilotController(AssignedPilotService assignedPilotService, PilotService pilotService) {
        this.assignedPilotService = assignedPilotService;
        this.pilotService = pilotService;
    }

    @GetMapping
    public String listAssignedPilots(@ModelAttribute("assignedPilotFilter") AssignedPilotFilter assignedPilotFilter, Model model) {
        AssignedPilotStatusEnum statusFilter = assignedPilotFilter.getStatus();
        List<AssignedPilot> assignedPilots;

        if (statusFilter != null) {
            assignedPilots = assignedPilotService.getByStatus(statusFilter);
        } else {
            assignedPilots = assignedPilotService.getAll();
        }

        model.addAttribute("assignedPilots", assignedPilots);
        model.addAttribute("statuses", AssignedPilotStatusEnum.values());
        return "assignedpilot";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        // Récupérer seulement les pilotes disponibles (AVAILABLE et WOUNDED_BUT_AVAILABLE)
        List<Pilot> availablePilots = ((List<Pilot>) pilotService.findAll()).stream()
            .filter(pilot -> pilot.getPilotStatus() == PilotStatusEnum.AVAILABLE ||
                           pilot.getPilotStatus() == PilotStatusEnum.WOUNDED_BUT_AVAILABLE)
            .toList();

        model.addAttribute("assignedPilot", new AssignedPilot());
        model.addAttribute("availablePilots", availablePilots);
        return "assignedpilot-create-form";
    }

    @PostMapping("/create")
    public String createAssignedPilot(@ModelAttribute AssignedPilot assignedPilot) {
        // Récupérer le pilote complet depuis la base de données
        if (assignedPilot.getPilot() != null && assignedPilot.getPilot().getIdPilot() != null) {
            Pilot pilot = pilotService.getById(assignedPilot.getPilot().getIdPilot());

            // Vérifier que le pilote est bien disponible
            if (pilot.getPilotStatus() == PilotStatusEnum.AVAILABLE ||
                pilot.getPilotStatus() == PilotStatusEnum.WOUNDED_BUT_AVAILABLE) {

                // Associer le pilote complet
                assignedPilot.setPilot(pilot);
                // Définir le status par défaut à "En attente"
                assignedPilot.setStatus(AssignedPilotStatusEnum.WAITING);
                assignedPilotService.create(assignedPilot);
            }
        }

        return "redirect:/assignedpilot";
    }

    @PostMapping("/{id}/update-status")
    public String updateStatus(@PathVariable Long id,
                              @RequestParam AssignedPilotStatusEnum status) {
        AssignedPilot assignedPilot = assignedPilotService.getById(id);
        assignedPilot.setStatus(status);
        assignedPilotService.update(id, assignedPilot);

        return "redirect:/assignedpilot";
    }

}