package com.ldnr.wookieAirlines.services;

import com.ldnr.wookieAirlines.models.FlyingCrew;
import com.ldnr.wookieAirlines.filters.CrewFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlyingCrewService {
    FlyingCrew save(FlyingCrew flyingCrew);
    FlyingCrew createCrewWithAssignments(String crewName, Long fighterId, Long pilot1Id, Long pilot2Id);
    FlyingCrew getById(Long id);
    void deleteById(Long id);
    Page<FlyingCrew> findPaginated(Pageable pageable);
    Page<FlyingCrew> findPaginatedWithFilter(CrewFilter filter, Pageable pageable);
    List<FlyingCrew> findAll();
}
