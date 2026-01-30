package com.ldnr.wookieAirlines.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalNavController {

    @ModelAttribute("activeUrl")
    public String activeUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("navItems")
    public List<Map<String, String>> navItems() {
        return List.of(
            Map.of("url", "/pilot", "label", "✈️ Pilotes"),
            Map.of("url", "/assignedpilot", "label", "👨‍✈️ Engagés"),
            Map.of("url", "/fighter", "label", "🚀 Chasseurs"),
            Map.of("url", "/flyingcrew", "label", "🎯 Equipages"),
            Map.of("url", "/mission", "label", "🎯 Missions"),
            Map.of("url", "/memorial", "label", "🕊️ Memorial")
        );
    }
}