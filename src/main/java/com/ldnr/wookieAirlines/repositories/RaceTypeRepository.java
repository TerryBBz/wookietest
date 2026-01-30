package com.ldnr.wookieAirlines.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldnr.wookieAirlines.models.RaceType;


@Repository
public interface RaceTypeRepository extends JpaRepository<RaceType, Long> {

}

