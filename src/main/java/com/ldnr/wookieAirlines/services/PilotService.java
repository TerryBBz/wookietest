package com.ldnr.wookieAirlines.services;

import com.ldnr.wookieAirlines.models.Pilot;
import com.ldnr.wookieAirlines.models.Mission;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

public interface PilotService {
    Iterable<Pilot> findAll();
    Page<Pilot> findPaginated(PageRequest pageRequest);

    Pilot save(Pilot pilot);
    Pilot update(Pilot pilot);

    Pilot getById(Long id);

    void deleteById(Long id);

    Page<Pilot> findPaginated(Pageable pageable);

    Page<Pilot> findByStatus(PilotStatusEnum status, Pageable pageable);
    List<Pilot> findAvailablePilots();
    void addFlightHours(Long pilotId, int additionalHours);
    void updatePilotStatus(Long pilotId, PilotStatusEnum status);
    String calculateGrade(Pilot pilot);
    Page<Pilot> findByNameContaining(String search, Pageable pageable);
    Page<Pilot> findByStatusAndNameContaining(PilotStatusEnum status, String search, Pageable pageable);
    List<Mission> getMissionsForPilot(Long pilotId);
}
