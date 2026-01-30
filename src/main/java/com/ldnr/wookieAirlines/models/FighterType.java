package com.ldnr.wookieAirlines.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 *
 * @author Terry
 */


@Entity
@Table(name = "FighterType")
public class FighterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "nbPilot", nullable = false)
    private Integer nbPilot;

    // Constructeurs
    public FighterType() {
    }

    public FighterType(String name, Integer nbPilot) {
        this.name = name;
        this.nbPilot = nbPilot;
    }

    // Getters et Setters
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

    public Integer getNbPilot() {
        return nbPilot;
    }

    public void setNbPilot(Integer nbPilot) {
        this.nbPilot = nbPilot;
    }

    @Override
    public String toString() {
        return "FighterType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nbPilot=" + nbPilot +
                '}';
    }
}