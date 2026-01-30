package com.ldnr.wookieAirlines.services;

import com.ldnr.wookieAirlines.models.AssignedPilot;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;
import com.ldnr.wookieAirlines.repositories.AssignedPilotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedPilotServiceImpl implements AssignedPilotService {

    private final AssignedPilotRepository assignedPilotRepository;
    private final PilotService pilotService;

    public AssignedPilotServiceImpl(AssignedPilotRepository assignedPilotRepository, PilotService pilotService) {
        this.assignedPilotRepository = assignedPilotRepository;
        this.pilotService = pilotService;
    }

    @Override
    public AssignedPilot create(AssignedPilot assignedPilot) {
        // Mettre à jour le statut du pilot quand l'AssignedPilot est créé
        if (assignedPilot.getPilot() != null && assignedPilot.getStatus() == AssignedPilotStatusEnum.WAITING) {
            pilotService.updatePilotStatus(assignedPilot.getPilot().getIdPilot(), PilotStatusEnum.ASSIGNED);
        }
        return assignedPilotRepository.save(assignedPilot);
    }

    @Override
    public AssignedPilot getById(Long id) {
        return assignedPilotRepository.findById(id).orElseThrow();
    }

    @Override
    public List<AssignedPilot> getAll() {
        return assignedPilotRepository.findAll();
    }

    @Override
    public Page<AssignedPilot> findPaginated(Pageable pageable) {
        return assignedPilotRepository.findAll(pageable);
    }

    @Override
    public List<AssignedPilot> getByStatus(AssignedPilotStatusEnum status) {
        return assignedPilotRepository.findByStatus(status);
    }

    @Override
    public Page<AssignedPilot> findByStatus(AssignedPilotStatusEnum status, Pageable pageable) {
        return assignedPilotRepository.findByStatus(status, pageable);
    }

    @Override
    public List<AssignedPilot> getAllAvailablePilots() {
        return assignedPilotRepository.findByStatus(AssignedPilotStatusEnum.WAITING);
    }

    @Override
    public List<AssignedPilot> getAllInMissionPilots() {
        return assignedPilotRepository.findByStatus(AssignedPilotStatusEnum.ASSIGNED);
    }

    @Override
    public AssignedPilot update(Long id, AssignedPilot updated) {
        AssignedPilot existing = getById(id);

        existing.setPilot(updated.getPilot());
        existing.setStatus(updated.getStatus());

        // Mettre à jour le statut du pilot en fonction du statut de l'assignedpilot
        if (existing.getPilot() != null) {
            PilotStatusEnum newPilotStatus;
            if (updated.getStatus() == AssignedPilotStatusEnum.WAITING) {
                // AssignedPilot en attente -> Pilot assigné en mission
                newPilotStatus = PilotStatusEnum.ASSIGNED;
            } else if (updated.getStatus() == AssignedPilotStatusEnum.ASSIGNED) {
                // AssignedPilot assigné à un crew -> Pilot en mission
                newPilotStatus = PilotStatusEnum.IN_MISSION;
            } else {
                // Autre cas -> retour disponible
                newPilotStatus = PilotStatusEnum.AVAILABLE;
            }
            pilotService.updatePilotStatus(existing.getPilot().getIdPilot(), newPilotStatus);
        }

        return assignedPilotRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        assignedPilotRepository.deleteById(id);
    }
}
