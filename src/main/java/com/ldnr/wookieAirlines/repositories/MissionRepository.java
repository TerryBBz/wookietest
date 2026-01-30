package com.ldnr.wookieAirlines.repositories;

import com.ldnr.wookieAirlines.models.Mission;
import com.ldnr.wookieAirlines.models.enums.MissionStatusEnum;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findByStatus(MissionStatusEnum status, Pageable pageable);
    List<Mission> findByStatus(MissionStatusEnum status);
}
