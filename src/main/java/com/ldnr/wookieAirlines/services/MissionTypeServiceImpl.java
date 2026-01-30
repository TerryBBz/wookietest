package com.ldnr.wookieAirlines.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ldnr.wookieAirlines.models.MissionType;
import com.ldnr.wookieAirlines.repositories.MissionTypeRepository;

@Service
public class MissionTypeServiceImpl implements MissionTypeService{


    private final MissionTypeRepository missionTypeRepository;

    public MissionTypeServiceImpl(MissionTypeRepository missionTypeRepository) {
        this.missionTypeRepository = missionTypeRepository;
    }

    @Override
    public Iterable<MissionType> findAll() {
        return missionTypeRepository.findAll(Pageable.unpaged()).getContent();
    }

    @Override
    public MissionType save(MissionType missionType) {
        return missionTypeRepository.save(missionType);
    }

    @Override
    public void deleteById(Long id) {
        missionTypeRepository.deleteById(id);
    }

    @Override
    public MissionType findById(Long id) {
        return missionTypeRepository.findById(id).orElseThrow();
    }
    
}
