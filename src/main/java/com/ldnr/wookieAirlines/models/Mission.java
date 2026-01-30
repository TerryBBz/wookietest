package com.ldnr.wookieAirlines.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import com.ldnr.wookieAirlines.models.enums.MissionStatusEnum;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mission_id")
    private Set<FlyingCrew> flyingCrewList = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private MissionStatusEnum status;

    @ManyToOne
    private MissionType type;

    private double successRate;
    private int flightTime;
    
    public Mission() {
        
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<FlyingCrew> getFlyingCrewList() {
        return flyingCrewList;
    }
    public void setFlyingCrewList(Set<FlyingCrew> flyingCrewList) {
        this.flyingCrewList = flyingCrewList;
    }
    public MissionStatusEnum getStatus() {
        return status;
    }
    public void setStatus(MissionStatusEnum status) {
        this.status = status;
    }
    public MissionType getType() {
        return type;
    }
    public void setType(MissionType type) {
        this.type = type;
    }
    public double getSuccessRate() {
        return successRate;
    }
    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }
    public int getFlightTime() {
        return flightTime;
    }
    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((flyingCrewList == null) ? 0 : flyingCrewList.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        long temp;
        temp = Double.doubleToLongBits(successRate);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + flightTime;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mission other = (Mission) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (flyingCrewList == null) {
            if (other.flyingCrewList != null)
                return false;
        } else if (!flyingCrewList.equals(other.flyingCrewList))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (Double.doubleToLongBits(successRate) != Double.doubleToLongBits(other.successRate))
            return false;
        if (flightTime != other.flightTime)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Mission [id=" + id + ", name=" + name + ", description=" + description + ", flyingCrewList="
                + flyingCrewList + ", status=" + status + ", type=" + type + ", successRate=" + successRate
                + ", flightTime=" + flightTime + "]";
    }


}
