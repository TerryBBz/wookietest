package com.ldnr.wookieAirlines.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import com.ldnr.wookieAirlines.models.Mission;
import com.ldnr.wookieAirlines.models.FlyingCrew;
import com.ldnr.wookieAirlines.models.Fighter;
import com.ldnr.wookieAirlines.models.AssignedPilot;
import com.ldnr.wookieAirlines.models.Pilot;
import com.ldnr.wookieAirlines.models.enums.MissionStatusEnum;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;
import com.ldnr.wookieAirlines.models.enums.CrewStatusEnum;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import com.ldnr.wookieAirlines.filters.MissionFilter;
import com.ldnr.wookieAirlines.repositories.MissionRepository;

@Service
public class MissionServiceImpl implements MissionService {


    private final MissionRepository missionRepository;

    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public Iterable<Mission> findAll(){
        return missionRepository.findAll(Pageable.unpaged()).getContent();
    }

    @Override
    public Page<Mission> findPaginated(Pageable pageable) {
        return missionRepository.findAll(pageable);
    }


    @Override
    public Mission save(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public void deleteById(Long id) {
        missionRepository.deleteById(id);
    }

    @Override
    public Mission findById(Long id) {
        return missionRepository.findById(id).orElseThrow();
    }

    @Override
    public long count() {
        return missionRepository.count();
    }

    @Override
    public void saveMissionStatus(Long missionId, MissionStatusEnum status) {
        Mission mission = findById(missionId);
        mission.setStatus(status);
        missionRepository.save(mission);
    }

    @Override
    public Page<Mission> findByStatus(MissionStatusEnum status, Pageable pageable) {
        return missionRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Mission> findPaginatedWithFilter(MissionFilter filter, Pageable pageable) {
        if (filter.getStatus() != null) {
            return missionRepository.findByStatus(filter.getStatus(), pageable);
        } else {
            return missionRepository.findAll(pageable);
        }
    }

    @Override
    @Transactional
    public void finishMission(Long id, int flightTime, String fighterStatus, String pilotStatus, double successRate) {
        Mission mission = findById(id);
        if (mission == null || mission.getStatus() != MissionStatusEnum.IN_PROGRESS) {
            return;
        }

        // Convertir les strings en enums
        FighterStatusEnum fighterStatusEnum = FighterStatusEnum.valueOf(fighterStatus);
        PilotStatusEnum pilotStatusEnum = PilotStatusEnum.valueOf(pilotStatus);

        // update mission
        mission.setStatus(MissionStatusEnum.DONE);
        mission.setSuccessRate(successRate);
        mission.setFlightTime(flightTime);

        // update les crew
        for (FlyingCrew crew : mission.getFlyingCrewList()) {
            // update fighterstatus
            Fighter fighter = crew.getFighter();
            fighter.setFighterStatus(fighterStatusEnum);
            crew.setFighterStatusAfterMission(fighterStatusEnum);

            // update assignedpilot
            for (AssignedPilot assignedPilot : crew.getAssignedPilotList()) {
                Pilot pilot = assignedPilot.getPilot();

                // Sauvegarder le statut du pilote après mission (pour l'historique)
                assignedPilot.setPilotStatusAfterMission(pilotStatusEnum);

                // Mettre à jour le pilote
                pilot.setPilotStatus(pilotStatusEnum);
                pilot.setTotalFlightTime(pilot.getTotalFlightTime() + flightTime);

                // Le status de l'assignedPilot reste indépendant du pilote
                // Il garde son statut de mission (ASSIGNED) pour l'historique
                // On ne le change que si le pilote devient disponible pour de futures missions
                if (pilotStatusEnum == PilotStatusEnum.AVAILABLE) {
                    assignedPilot.setStatus(AssignedPilotStatusEnum.WAITING);
                }
                // Si le pilote est blessé/mort, l'assignedPilot garde son statut ASSIGNED pour cette mission

                // Incrémenter les compteurs de mission
                pilot.setTotalMissions(pilot.getTotalMissions() + 1);
                // Si c'est une mission de combat, incrémenter aussi
                if (mission.getType() != null && mission.getType().getName().contains("Combat")) {
                    pilot.setTotalCombatMissions(pilot.getTotalCombatMissions() + 1);
                }
            }

            // DISSOUDRE l'équipage après la mission
            crew.setCrewStatus(CrewStatusEnum.DISSOLVED);
        }

        missionRepository.save(mission);
    }

    @Override
    @Transactional
    public void finishMissionWithIndividualStatuses(Long id, int flightTime, double successRate, Map<String, String> allParams) {
        Mission mission = findById(id);
        if (mission == null || mission.getStatus() != MissionStatusEnum.IN_PROGRESS) {
            return;
        }


        // update mission
        mission.setStatus(MissionStatusEnum.DONE);
        mission.setSuccessRate(successRate);
        mission.setFlightTime(flightTime);

        // update les crew avec statuts individuels
        for (FlyingCrew crew : mission.getFlyingCrewList()) {
            // Récupérer le statut individuel du fighter depuis les paramètres
            String fighterStatusParam = allParams.get("fighterStatus_" + crew.getId());
            if (fighterStatusParam != null) {
                FighterStatusEnum fighterStatusEnum = FighterStatusEnum.valueOf(fighterStatusParam);

                // update fighterstatus
                Fighter fighter = crew.getFighter();
                fighter.setFighterStatus(fighterStatusEnum);
                crew.setFighterStatusAfterMission(fighterStatusEnum);
            }

            // update assignedpilot avec statuts individuels
            for (AssignedPilot assignedPilot : crew.getAssignedPilotList()) {
                // Récupérer le statut individuel du pilote depuis les paramètres
                String pilotStatusParam = allParams.get("pilotStatus_" + assignedPilot.getId());
                if (pilotStatusParam != null) {
                    PilotStatusEnum pilotStatusEnum = PilotStatusEnum.valueOf(pilotStatusParam);
                    Pilot pilot = assignedPilot.getPilot();

                    // Sauvegarder le statut du pilote après mission (pour l'historique)
                    assignedPilot.setPilotStatusAfterMission(pilotStatusEnum);

                    // Mettre à jour le pilote
                    pilot.setPilotStatus(pilotStatusEnum);
                    pilot.setTotalFlightTime(pilot.getTotalFlightTime() + flightTime);

                    // Le status de l'assignedPilot reste indépendant du pilote
                    // Il garde son statut de mission (ASSIGNED) pour l'historique
                 
                    if (pilotStatusEnum == PilotStatusEnum.AVAILABLE) {
                        assignedPilot.setStatus(AssignedPilotStatusEnum.WAITING);
                    }

                    // Incrémenter les compteurs de mission
                    pilot.setTotalMissions(pilot.getTotalMissions() + 1);
                    // Si c'est une mission de combat, incrémenter aussi
                    if (mission.getType() != null && mission.getType().getName().contains("Combat")) {
                        pilot.setTotalCombatMissions(pilot.getTotalCombatMissions() + 1);
                    }
                }
            }

            // DISSOUDRE l'équipage après la mission
            crew.setCrewStatus(CrewStatusEnum.DISSOLVED);
        }

        missionRepository.save(mission);
    }
}
