package com.ldnr.wookieAirlines.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ldnr.wookieAirlines.filters.FighterFilter;
import com.ldnr.wookieAirlines.models.Fighter;
import com.ldnr.wookieAirlines.models.FighterType;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;
import com.ldnr.wookieAirlines.services.FighterService;
import com.ldnr.wookieAirlines.repositories.FighterTypeRepository;

import java.util.List;

@Controller
@RequestMapping("/fighter")
public class FighterController {

    private final FighterService fighterService;
    private final FighterTypeRepository fighterTypeRepository;

    public FighterController(FighterService fighterService,
                           FighterTypeRepository fighterTypeRepository) {
        this.fighterService = fighterService;
        this.fighterTypeRepository = fighterTypeRepository;
    }

    // Objet utilisé pour le filtre
    @ModelAttribute("fighterFilter")
    public FighterFilter fighterFilter() {
        return new FighterFilter();
    }

    @GetMapping
    public String listFighters(
            @ModelAttribute FighterFilter filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Fighter> fighterPage;

        if (filter.getStatus() != null) {
            fighterPage = fighterService.findByStatus(filter.getStatus(), pageable);
        } else {
            fighterPage = fighterService.findPaginated(pageable);
        }

        model.addAttribute("fighterPage", fighterPage);
        model.addAttribute("filter", filter);
        model.addAttribute("statusOptions", FighterStatusEnum.values());

        return "fighter";
    }

    @GetMapping("/create")
    public String createFighter(Model model) {
        List<FighterType> fighterTypes = fighterTypeRepository.findAll();
        model.addAttribute("fighter", new Fighter());
        model.addAttribute("fighterTypes", fighterTypes);
        return "fighter-create-form";
    }

    @PostMapping("/save")
    public String saveFighter(@ModelAttribute Fighter fighter,
                             @RequestParam(required = false) Long typeId) {

        // Gérer la relation type si un ID est fourni
        if (typeId != null && typeId > 0) {
            FighterType fighterType = fighterTypeRepository.findById(typeId).orElse(null);
            fighter.setType(fighterType);
        }

        fighter.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterService.save(fighter);
        return "redirect:/fighter";
    }

    @GetMapping("/view/{id}")
    public String viewFighter(@PathVariable Long id, Model model) {
        List<FighterType> fighterTypes = fighterTypeRepository.findAll();
        Fighter fighter = fighterService.getById(id);

        model.addAttribute("fighter", fighter);
        model.addAttribute("fighterTypes", fighterTypes);
        return "fighter-update-form";
    }

    @PostMapping("/update")
    public String updateFighter(@ModelAttribute Fighter fighter,
                               @RequestParam(required = false) Long typeId) {

        // Gérer la relation type si un ID est fourni
        if (typeId != null && typeId > 0) {
            FighterType fighterType = fighterTypeRepository.findById(typeId).orElse(null);
            fighter.setType(fighterType);
        }

        fighterService.update(fighter);
        return "redirect:/fighter";
    }

    @GetMapping("/edit/{id}")
    public String editFighter(@PathVariable Long id, Model model) {
        List<FighterType> fighterTypes = fighterTypeRepository.findAll();
        model.addAttribute("fighter", fighterService.getById(id));
        model.addAttribute("fighterTypes", fighterTypes);
        return "fighter-create-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFighter(@PathVariable Long id) {
        fighterService.deleteById(id);
        return "redirect:/fighter";
    }
}