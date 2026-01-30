package com.ldnr.wookieAirlines.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldnr.wookieAirlines.models.Pilot;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;

/**
 * 
 * @author Terry
 */
@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {
    Page<Pilot> findByPilotStatus(PilotStatusEnum status, Pageable pageable);
    List<Pilot> findByPilotStatus(PilotStatusEnum status);

    // Recherche par nom (prénom ou nom de famille)
    Page<Pilot> findByFirstNameContaining(
        String firstName, String lastName, Pageable pageable);

    // Recherche par statut ET nom
    Page<Pilot> findByPilotStatusAndFirstNameContaining(
        PilotStatusEnum status1, String firstName, PilotStatusEnum status2, String lastName, Pageable pageable);
}

