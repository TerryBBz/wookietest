package com.ldnr.wookieAirlines.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPilot;

    private String firstName;
    private String lastName;

    private int subscriptionAge;
    private LocalDate subscriptionDate;

    private int totalFlightTime;

    @Column(name = "totalMissions", nullable = false)
    private int totalMissions = 0;

    @Column(name = "totalCombatMissions", nullable = false)
    private int totalCombatMissions = 0;

    @ManyToOne
    private RaceType race; 

    @OneToMany(mappedBy = "pilot", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<AssignedPilot> assignedPilotList = new HashSet<>(); 
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private PilotStatusEnum pilotStatus; 

   
    public Long getIdPilot() {
        return idPilot;
    }

    public void setIdPilot(Long idPilot) {
        this.idPilot = idPilot;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSubscriptionAge() {
        return subscriptionAge;
    }

    public void setSubscriptionAge(int subscriptionAge) {
        this.subscriptionAge = subscriptionAge;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public int getTotalFlightTime() {
        return totalFlightTime;
    }

    public void setTotalFlightTime(int totalFlightTime) {
        this.totalFlightTime = totalFlightTime;
    }

    public int getTotalMissions() {
        return totalMissions;
    }

    public void setTotalMissions(int totalMissions) {
        this.totalMissions = totalMissions;
    }

    public int getTotalCombatMissions() {
        return totalCombatMissions;
    }

    public void setTotalCombatMissions(int totalCombatMissions) {
        this.totalCombatMissions = totalCombatMissions;
    }

    public RaceType getRace() {
        return race;
    }

    public void setRace(RaceType race) {
        this.race = race;
    }

    public Set<AssignedPilot> getAssignedPilotList() {
        return assignedPilotList;
    }

    public void setAssignedPilotList(Set<AssignedPilot> assignedPilotList) {
        this.assignedPilotList = assignedPilotList;
    }

    public PilotStatusEnum getPilotStatus() {
        return pilotStatus;
    }

    public void setPilotStatus(PilotStatusEnum pilotStatus) {
        this.pilotStatus = pilotStatus;
    }
}
