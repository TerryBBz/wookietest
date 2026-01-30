package com.ldnr.wookieAirlines.services;

import com.ldnr.wookieAirlines.models.Pilot;
import com.ldnr.wookieAirlines.models.Mission;
import com.ldnr.wookieAirlines.models.AssignedPilot;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;
import com.ldnr.wookieAirlines.repositories.PilotRepository;
import com.ldnr.wookieAirlines.repositories.MissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;
    private final MissionRepository missionRepository;

    public PilotServiceImpl(PilotRepository pilotRepository, MissionRepository missionRepository) {
        this.pilotRepository = pilotRepository;
        this.missionRepository = missionRepository;
    }

    @Override
    public List<Pilot> findAll() {
        return pilotRepository.findAll();
    }

    @Override
    public Page<Pilot> findPaginated(PageRequest pageRequest) {
        return pilotRepository.findAll(pageRequest);
    }

    @Override
    public Pilot save(Pilot pilot) {
        return pilotRepository.save(pilot);
    }

    @Override
    public Pilot update(Pilot pilot) {
        return pilotRepository.save(pilot);
    }

    @Override
    public Pilot getById(Long id) {
        return pilotRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        pilotRepository.deleteById(id);
    }

    @Override
    public Page<Pilot> findPaginated(Pageable pageable) {
        return pilotRepository.findAll(pageable);
    }

    @Override
    public Page<Pilot> findByStatus(PilotStatusEnum status, Pageable pageable) {
        return pilotRepository.findByPilotStatus(status, pageable);
    }

    @Override
    public List<Pilot> findAvailablePilots() {
        return pilotRepository.findByPilotStatus(PilotStatusEnum.AVAILABLE);
    }

    @Override
    public void updatePilotStatus(Long pilotId, PilotStatusEnum status) {
        Pilot pilot = getById(pilotId);
        pilot.setPilotStatus(status);
        pilotRepository.save(pilot);
    }

    @Override
    public void addFlightHours(Long pilotId, int additionalHours) {
        Pilot pilot = getById(pilotId);
        pilot.setTotalFlightTime(pilot.getTotalFlightTime() + additionalHours);
        pilotRepository.save(pilot);
    }

    @Override
    public String calculateGrade(Pilot pilot) {
        int hours = pilot.getTotalFlightTime();
        int missions = pilot.getTotalMissions();
        int combatMissions = pilot.getTotalCombatMissions();

        if (hours >= 4000 && missions >= 10) {
            return "Commandant";
        }
        if (hours >= 1500 && missions >= 3) {
            return "Major";
        }
        if (hours >= 1500 && combatMissions >= 1) {
            return "Capitaine";
        }
        if (hours >= 500) {
            return "Lieutenant";
        }
        if (hours >= 100) {
            return "Officier de vol";
        }
        return "Apprenti pilote";
    }

    @Override
    public Page<Pilot> findByNameContaining(String search, Pageable pageable) {
        return pilotRepository.findByFirstNameContaining(search, search, pageable);
    }

    @Override
    public Page<Pilot> findByStatusAndNameContaining(PilotStatusEnum status, String search, Pageable pageable) {
        return pilotRepository.findByPilotStatusAndFirstNameContaining(status, search, status, search, pageable);
    }

    @Override
    public List<Mission> getMissionsForPilot(Long pilotId) {
        List<Mission> missions = new ArrayList<>();

        // Récupération de toutes les missions
        List<Mission> allMissions = missionRepository.findAll();

        // Pour chaque mission, vérifier si le pilote y a participé
        for (Mission mission : allMissions) {
            // Parcourir les FlyingCrew de la mission
            for (var flyingCrew : mission.getFlyingCrewList()) {
                // Parcourir les AssignedPilot du FlyingCrew
                for (AssignedPilot assignedPilot : flyingCrew.getAssignedPilotList()) {
                    // Si c'est notre pilote, ajouter la mission
                    if (assignedPilot.getPilot().getIdPilot().equals(pilotId)) {
                        missions.add(mission);
                        break; // Mission déjà ajoutée, passer à la suivante
                    }
                }
            }
        }

        return missions;
    }
}