package com.ldnr.wookieAirlines.models;

import jakarta.persistence.*;
import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;
/**
 *
 * @author Terry
 */

@Entity
@Table(name = "assignedPilot")
public class AssignedPilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pilotId", nullable = false)
    private Pilot pilot;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private AssignedPilotStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "pilot_status_after_mission", length = 30)
    private PilotStatusEnum pilotStatusAfterMission;

    // Constructeurs
    public AssignedPilot() {}

    public AssignedPilot(Pilot pilot, AssignedPilotStatusEnum status) {
        this.pilot = pilot;
        this.status = status;
    }

    public AssignedPilot(Pilot pilot) {
        this.pilot = pilot;
        this.status = AssignedPilotStatusEnum.WAITING; // Par défaut : En attente
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public AssignedPilotStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AssignedPilotStatusEnum status) {
        this.status = status;
    }

    public PilotStatusEnum getPilotStatusAfterMission() {
        return pilotStatusAfterMission;
    }

    public void setPilotStatusAfterMission(PilotStatusEnum pilotStatusAfterMission) {
        this.pilotStatusAfterMission = pilotStatusAfterMission;
    }
}