package com.ldnr.wookieAirlines.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ldnr.wookieAirlines.models.Fighter;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;

public interface FighterService {
    Iterable<Fighter> findAll();
    Page<Fighter> findPaginated(Pageable pageable);
    Fighter save(Fighter fighter);
    Fighter update(Fighter fighter);
    Fighter getById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    void delete(Fighter fighter);
    long count();
    void updateFighterStatus(Long fighterId, FighterStatusEnum status);
    Page<Fighter> findByStatus(FighterStatusEnum status, Pageable pageable);
}
