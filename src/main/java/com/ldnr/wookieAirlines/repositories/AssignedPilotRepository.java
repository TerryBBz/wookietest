package com.ldnr.wookieAirlines.repositories;

import com.ldnr.wookieAirlines.models.AssignedPilot;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssignedPilotRepository extends JpaRepository<AssignedPilot, Long> {

    List<AssignedPilot> findByStatus(AssignedPilotStatusEnum status);
    Page<AssignedPilot> findByStatus(AssignedPilotStatusEnum status, Pageable pageable);
}
