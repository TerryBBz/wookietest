package com.ldnr.wookieAirlines.repositories;

import com.ldnr.wookieAirlines.models.FighterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FighterTypeRepository extends JpaRepository<FighterType, Long> {

}
