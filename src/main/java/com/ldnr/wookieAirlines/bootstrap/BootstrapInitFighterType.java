package com.ldnr.wookieAirlines.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ldnr.wookieAirlines.models.FighterType;
import com.ldnr.wookieAirlines.repositories.FighterTypeRepository;

@Component
@Order(2)
public class BootstrapInitFighterType implements CommandLineRunner {

    private final FighterTypeRepository fighterTypeRepository;

    public BootstrapInitFighterType(FighterTypeRepository fighterTypeRepository) {
        this.fighterTypeRepository = fighterTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrap Init FighterType...");

        // Créer et sauvegarder X-Wing (1 pilote)
        FighterType xwing = new FighterType();
        xwing.setName("X-Wing");
        xwing.setNbPilot(1);
        fighterTypeRepository.save(xwing);

        // Créer et sauvegarder Y-Wing (2 pilotes)
        FighterType ywing = new FighterType();
        ywing.setName("Y-Wing");
        ywing.setNbPilot(2);
        fighterTypeRepository.save(ywing);

        System.out.println("Bootstrap Init FighterType terminé !");
    }
}