package com.ldnr.wookieAirlines.services;

import com.ldnr.wookieAirlines.models.MissionType;



public interface MissionTypeService {

    Iterable<MissionType> findAll();

    MissionType save(MissionType missionType);
    void deleteById(Long id);
    MissionType findById(Long id);
    
}
