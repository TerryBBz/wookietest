package com.ldnr.wookieAirlines.models;
import jakarta.persistence.*;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;
import com.ldnr.wookieAirlines.models.enums.CrewStatusEnum;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Terry
 */

@Entity
@Table(name = "flyingCrew")
public class FlyingCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crewName", nullable = false)
    private String crewName;

    @Enumerated(EnumType.STRING)
    @Column(name = "crewStatus", nullable = false, length = 30)
    private CrewStatusEnum crewStatus;

    @ManyToOne
    @JoinColumn(name = "idFighter", nullable = false)
    private Fighter fighter;

    @Enumerated(EnumType.STRING)
    @Column(name = "fighterStatusAfterMission", nullable = true, length = 30)
    private FighterStatusEnum fighterStatusAfterMission;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFlyingCrew")
    private Set<AssignedPilot> assignedPilotList = new HashSet<>();

    // Constructeurs
    public FlyingCrew() {}

    public FlyingCrew(String crewName, CrewStatusEnum crewStatus, Fighter fighter, FighterStatusEnum fighterStatusAfterMission) {
        this.crewName = crewName;
        this.crewStatus = crewStatus;
        this.fighter = fighter;
        this.fighterStatusAfterMission = fighterStatusAfterMission;
    }

    public FlyingCrew(Fighter fighter, FighterStatusEnum fighterStatusAfterMission) {
        this.fighter = fighter;
        this.fighterStatusAfterMission = fighterStatusAfterMission;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public CrewStatusEnum getCrewStatus() {
        return crewStatus;
    }

    public void setCrewStatus(CrewStatusEnum crewStatus) {
        this.crewStatus = crewStatus;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public FighterStatusEnum getFighterStatusAfterMission() {
        return fighterStatusAfterMission;
    }

    public void setFighterStatusAfterMission(FighterStatusEnum fighterStatusAfterMission) {
        this.fighterStatusAfterMission = fighterStatusAfterMission;
    }

    public Set<AssignedPilot> getAssignedPilotList() {
        return assignedPilotList;
    }

    public void setAssignedPilotList(Set<AssignedPilot> assignedPilotList) {
        this.assignedPilotList = assignedPilotList;
    }

    private String flyingCrewName;

public String getFlyingCrewName() {
    return flyingCrewName;
}

public void setFlyingCrewName(String flyingCrewName) {
    this.flyingCrewName = flyingCrewName;
}
}