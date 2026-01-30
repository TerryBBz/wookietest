package com.ldnr.wookieAirlines.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ldnr.wookieAirlines.models.RaceType;
import com.ldnr.wookieAirlines.repositories.RaceTypeRepository;

@Component
@Order(1)
public class BootstrapInitRaceType implements CommandLineRunner {

    private final RaceTypeRepository raceTypeRepository;

    public BootstrapInitRaceType(RaceTypeRepository raceTypeRepository) {
        this.raceTypeRepository = raceTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrap Init RaceType...");

        // Créer et sauvegarder chaque race
        RaceType chalacteens = new RaceType();
        chalacteens.setName("Chalactéens");
        raceTypeRepository.save(chalacteens);

        RaceType chiss = new RaceType();
        chiss.setName("Chiss");
        raceTypeRepository.save(chiss);

        RaceType humains = new RaceType();
        humains.setName("Humains");
        raceTypeRepository.save(humains);

        RaceType ithoriens = new RaceType();
        ithoriens.setName("Ithoriens");
        raceTypeRepository.save(ithoriens);

        RaceType mirialans = new RaceType();
        mirialans.setName("Mirialans");
        raceTypeRepository.save(mirialans);

        RaceType keldor = new RaceType();
        keldor.setName("Kel'Dor");
        raceTypeRepository.save(keldor);

        RaceType kiffars = new RaceType();
        kiffars.setName("Kiffars");
        raceTypeRepository.save(kiffars);

        RaceType miralukas = new RaceType();
        miralukas.setName("Miralukas");
        raceTypeRepository.save(miralukas);

        RaceType nagais = new RaceType();
        nagais.setName("Nagais");
        raceTypeRepository.save(nagais);

        RaceType neimoidiens = new RaceType();
        neimoidiens.setName("Neimoidiens");
        raceTypeRepository.save(neimoidiens);

        RaceType niktos = new RaceType();
        niktos.setName("Niktos");
        raceTypeRepository.save(niktos);

        RaceType noghris = new RaceType();
        noghris.setName("Noghris");
        raceTypeRepository.save(noghris);

        RaceType ongrees = new RaceType();
        ongrees.setName("Ongrees");
        raceTypeRepository.save(ongrees);

        RaceType pauans = new RaceType();
        pauans.setName("Pau'ans");
        raceTypeRepository.save(pauans);

        RaceType quermiens = new RaceType();
        quermiens.setName("Quermiens");
        raceTypeRepository.save(quermiens);

        RaceType rakata = new RaceType();
        rakata.setName("Rakata");
        raceTypeRepository.save(rakata);

        RaceType rodiens = new RaceType();
        rodiens.setName("Rodiens");
        raceTypeRepository.save(rodiens);

        RaceType thisspasiens = new RaceType();
        thisspasiens.setName("Thisspasiens");
        raceTypeRepository.save(thisspasiens);

        RaceType togrutas = new RaceType();
        togrutas.setName("Togrutas");
        raceTypeRepository.save(togrutas);

        RaceType wookiee = new RaceType();
        wookiee.setName("Wookiee");
        raceTypeRepository.save(wookiee);

        RaceType wronians = new RaceType();
        wronians.setName("Wronians");
        raceTypeRepository.save(wronians);

        RaceType zabraks = new RaceType();
        zabraks.setName("Zabraks");
        raceTypeRepository.save(zabraks);

        System.out.println("Bootstrap Init RaceType terminé !");
    }
}