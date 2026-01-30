package com.ldnr.wookieAirlines.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ldnr.wookieAirlines.models.Mission;
import com.ldnr.wookieAirlines.models.enums.MissionStatusEnum;
import com.ldnr.wookieAirlines.filters.MissionFilter;


public interface MissionService {
    
    Iterable<Mission> findAll();
    Page<Mission> findPaginated(Pageable pageable);

    Mission save(Mission mission);
    void deleteById(Long id);
    Mission findById(Long id);

    long count();
    void saveMissionStatus(Long missionId, MissionStatusEnum status);
    Page<Mission> findByStatus(MissionStatusEnum status, Pageable pageable);
    Page<Mission> findPaginatedWithFilter(MissionFilter filter, Pageable pageable);
    void finishMission(Long id, int flightTime, String fighterStatus, String pilotStatus, double successRate);
    void finishMissionWithIndividualStatuses(Long id, int flightTime, double successRate, java.util.Map<String, String> allParams);

}

