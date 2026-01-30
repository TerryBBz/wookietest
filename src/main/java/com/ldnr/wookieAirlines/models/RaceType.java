package com.ldnr.wookieAirlines.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRaceType;

    private String name;

    public Long getIdRaceType() {
        return idRaceType;
    }

    public void setIdRaceType(Long idRaceType) {
        this.idRaceType = idRaceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
