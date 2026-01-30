package com.ldnr.wookieAirlines.repositories;

import com.ldnr.wookieAirlines.models.MissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionTypeRepository extends JpaRepository<MissionType, Long> {

}
