package com.ldnr.wookieAirlines.controllers;

import com.ldnr.wookieAirlines.models.FlyingCrew;
import com.ldnr.wookieAirlines.models.Fighter;
import com.ldnr.wookieAirlines.models.AssignedPilot;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import com.ldnr.wookieAirlines.models.enums.CrewStatusEnum;
import com.ldnr.wookieAirlines.filters.CrewFilter;
import com.ldnr.wookieAirlines.services.FighterService;
import com.ldnr.wookieAirlines.services.FlyingCrewService;
import com.ldnr.wookieAirlines.services.AssignedPilotService;

import java.util.List;
import java.util.ArrayList;
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
        try {
            model.addAttribute("flyingCrew", new FlyingCrew());

            // Récupérer les pilotes en attente avec gestion d'erreur
            List<AssignedPilot> availablePilots = assignedPilotService.getByStatus(AssignedPilotStatusEnum.WAITING);
            model.addAttribute("availablePilots", availablePilots != null ? availablePilots : List.of());

            // Récupérer les chasseurs avec gestion d'erreur
            List<Fighter> availableFighters = new ArrayList<>();
            Iterable<Fighter> fightersIterable = fighterService.findAll();
            if (fightersIterable != null) {
                fightersIterable.forEach(availableFighters::add);
            }
            model.addAttribute("availableFighters", availableFighters);

            return "flyingcrew-create-form";
        } catch (Exception e) {
            // En cas d'erreur, rediriger vers la liste avec un message
            model.addAttribute("errorMessage", "Erreur lors du chargement des données: " + e.getMessage());
            return "redirect:/flyingcrew?error=loading";
        }
    }

    @PostMapping("/save")
    public String saveFlyingCrew(
            @RequestParam String crewName,
            @RequestParam Long fighterId,
            @RequestParam Long pilot1Id,
            @RequestParam(required = false) Long pilot2Id) {

        try {
            // Vérifications basiques
            if (crewName == null || crewName.trim().isEmpty()) {
                return "redirect:/flyingcrew/add?error=nom-vide";
            }

            if (fighterId == null || pilot1Id == null) {
                return "redirect:/flyingcrew/add?error=donnees-manquantes";
            }

            flyingCrewService.createCrewWithAssignments(crewName.trim(), fighterId, pilot1Id, pilot2Id);
            return "redirect:/flyingcrew?success=creation";
        } catch (Exception e) {
            return "redirect:/flyingcrew/add?error=creation-echec";
        }
    }

}
