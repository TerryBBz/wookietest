package com.ldnr.wookieAirlines.bootstrap;

import com.ldnr.wookieAirlines.models.MissionType;
import com.ldnr.wookieAirlines.repositories.MissionTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class BootstrapInitMissionType implements CommandLineRunner {

    private final MissionTypeRepository missionTypeRepository;

    public BootstrapInitMissionType(MissionTypeRepository missionTypeRepository) {
        this.missionTypeRepository = missionTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrap Init MissionType...");

        // Type de mission 1 - Entrainement
        MissionType entrainementType = new MissionType();
        entrainementType.setName("Entrainement");
        missionTypeRepository.save(entrainementType);

        // Type de mission 2 - Combat
        MissionType combatType = new MissionType();
        combatType.setName("Combat");
        missionTypeRepository.save(combatType);

        System.out.println("Bootstrap Init MissionType terminé !");
    }
}