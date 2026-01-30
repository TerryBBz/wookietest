package com.ldnr.wookieAirlines.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ldnr.wookieAirlines.models.FighterType;
import com.ldnr.wookieAirlines.repositories.FighterTypeRepository;

@Controller
@RequestMapping("/fightertype")
public class FighterTypeController {

    private final FighterTypeRepository fighterTypeRepository;

    public FighterTypeController(FighterTypeRepository fighterTypeRepository) {
        this.fighterTypeRepository = fighterTypeRepository;
    }

    @GetMapping("/add")
    public String addFighterType(Model model) {
        model.addAttribute("fighterType", new FighterType());
        return "fightertype-create-form";
    }

    @PostMapping("/save")
    public String saveFighterType(@ModelAttribute FighterType fighterType) {
        fighterTypeRepository.save(fighterType);
        return "redirect:/fighter/create";
    }

}