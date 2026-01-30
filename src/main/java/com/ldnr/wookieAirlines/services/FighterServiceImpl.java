package com.ldnr.wookieAirlines.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ldnr.wookieAirlines.models.Fighter;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;
import com.ldnr.wookieAirlines.repositories.FighterRepository;

@Service
public class FighterServiceImpl implements FighterService {

    private final FighterRepository fighterRepository;

    public FighterServiceImpl(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    @Override
    public Iterable<Fighter> findAll() {
        return fighterRepository.findAll();
    }

    @Override
    public Page<Fighter> findPaginated(Pageable pageable) {
        return fighterRepository.findAll(pageable);
    }

    @Override
    public Fighter save(Fighter fighter) {
        return fighterRepository.save(fighter);
    }

    @Override
    public Fighter update(Fighter fighter) {
        return fighterRepository.save(fighter);
    }

    @Override
    public Fighter getById(Long id) {
        return fighterRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean existsById(Long id) {
        return fighterRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        fighterRepository.deleteById(id);
    }

    @Override
    public void delete(Fighter fighter) {
        fighterRepository.delete(fighter);
    }

    @Override
    public long count() {
        return fighterRepository.count();
    }

    @Override
    public void updateFighterStatus(Long fighterId, FighterStatusEnum status) {
        Fighter fighter = getById(fighterId);
        fighter.setFighterStatus(status);
        save(fighter);
    }

    @Override
    public Page<Fighter> findByStatus(FighterStatusEnum status, Pageable pageable) {
        return fighterRepository.findByStatus(status, pageable);
    }
}
