package com.ldnr.wookieAirlines.repositories;

import com.ldnr.wookieAirlines.models.FlyingCrew;
import com.ldnr.wookieAirlines.models.enums.CrewStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlyingCrewRepository extends JpaRepository<FlyingCrew, Long> {

    Page<FlyingCrew> findByCrewStatus(CrewStatusEnum status, Pageable pageable);

    Page<FlyingCrew> findByCrewNameContainingIgnoreCase(String search, Pageable pageable);

    @Query("SELECT fc FROM FlyingCrew fc WHERE " +
           "(:status IS NULL OR fc.crewStatus = :status) AND " +
           "(:search IS NULL OR :search = '' OR LOWER(fc.crewName) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<FlyingCrew> findWithFilter(@Param("status") CrewStatusEnum status,
                                   @Param("search") String search,
                                   Pageable pageable);
}
