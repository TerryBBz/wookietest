
package com.ldnr.wookieAirlines.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ldnr.wookieAirlines.models.AssignedPilot;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import java.util.List;

public interface AssignedPilotService {

    AssignedPilot create(AssignedPilot assignedPilot);

    AssignedPilot getById(Long id);

    List<AssignedPilot> getAll();

    Page<AssignedPilot> findPaginated(Pageable pageable);

    List<AssignedPilot> getByStatus(AssignedPilotStatusEnum status);

    Page<AssignedPilot> findByStatus(AssignedPilotStatusEnum status, Pageable pageable);

    List<AssignedPilot> getAllAvailablePilots();

    List<AssignedPilot> getAllInMissionPilots();

    AssignedPilot update(Long id, AssignedPilot assignedPilot);

    void delete(Long id);
}

