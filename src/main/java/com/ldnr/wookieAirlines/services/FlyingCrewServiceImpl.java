package com.ldnr.wookieAirlines.services;

import com.ldnr.wookieAirlines.models.FlyingCrew;
import com.ldnr.wookieAirlines.models.Fighter;
import com.ldnr.wookieAirlines.models.AssignedPilot;
import com.ldnr.wookieAirlines.models.enums.CrewStatusEnum;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import com.ldnr.wookieAirlines.filters.CrewFilter;
import com.ldnr.wookieAirlines.repositories.FlyingCrewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
public class FlyingCrewServiceImpl implements FlyingCrewService {

    private final FlyingCrewRepository flyingCrewRepository;
    private final FighterService fighterService;
    private final AssignedPilotService assignedPilotService;

    public FlyingCrewServiceImpl(FlyingCrewRepository flyingCrewRepository,
                                FighterService fighterService,
                                AssignedPilotService assignedPilotService) {
        this.flyingCrewRepository = flyingCrewRepository;
        this.fighterService = fighterService;
        this.assignedPilotService = assignedPilotService;
    }

    @Override
    public FlyingCrew save(FlyingCrew flyingCrew) {
        return flyingCrewRepository.save(flyingCrew);
    }

    @Override
    @Transactional
    public FlyingCrew createCrewWithAssignments(String crewName, Long fighterId, Long pilot1Id, Long pilot2Id) {
        // Récupérer le fighter
        Fighter fighter = fighterService.getById(fighterId);

        // Récupérer les pilots assignés
        AssignedPilot pilot1 = assignedPilotService.getById(pilot1Id);
        AssignedPilot pilot2 = null;

        if (pilot2Id != null) {
            pilot2 = assignedPilotService.getById(pilot2Id);
        }

        // Créer le crew
        FlyingCrew crew = new FlyingCrew();
        crew.setCrewName(crewName);
        crew.setCrewStatus(CrewStatusEnum.AVAILABLE);
        crew.setFighter(fighter);

        // Ajouter les pilots au crew
        Set<AssignedPilot> pilots = new HashSet<>();
        pilots.add(pilot1);
        if (pilot2 != null) {
            pilots.add(pilot2);
        }
        crew.setAssignedPilotList(pilots);

        // Sauvegarder le crew
        FlyingCrew savedCrew = flyingCrewRepository.save(crew);

        // Mettre à jour les statuts
        pilot1.setStatus(AssignedPilotStatusEnum.ASSIGNED);
        assignedPilotService.update(pilot1Id, pilot1);

        if (pilot2 != null) {
            pilot2.setStatus(AssignedPilotStatusEnum.ASSIGNED);
            assignedPilotService.update(pilot2Id, pilot2);
        }

        fighterService.updateFighterStatus(fighterId, FighterStatusEnum.ASSIGNED);

        return savedCrew;
    }

    @Override
    public FlyingCrew getById(Long id) {
        return flyingCrewRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        flyingCrewRepository.deleteById(id);
    }

    @Override
    public Page<FlyingCrew> findPaginated(Pageable pageable) {
        return flyingCrewRepository.findAll(pageable);
    }

    @Override
    public List<FlyingCrew> findAll() {
        return flyingCrewRepository.findAll();
    }

    @Override
    public Page<FlyingCrew> findPaginatedWithFilter(CrewFilter filter, Pageable pageable) {
        String search = filter.getSearch();
        if (search != null && search.trim().isEmpty()) {
            search = null;
        }

        return flyingCrewRepository.findWithFilter(filter.getStatus(), search, pageable);
    }
}
