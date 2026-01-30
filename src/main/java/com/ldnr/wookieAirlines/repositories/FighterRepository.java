package com.ldnr.wookieAirlines.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldnr.wookieAirlines.models.Fighter;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Long> {
    Page<Fighter> findByStatus(FighterStatusEnum status, Pageable pageable);
}