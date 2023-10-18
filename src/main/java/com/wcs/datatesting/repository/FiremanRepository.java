package com.wcs.datatesting.repository;

import com.wcs.datatesting.entity.Fireman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FiremanRepository extends JpaRepository<Fireman, Long> {
    @Query("SELECT fm FROM Fireman fm WHERE SIZE(fm.fires) = (SELECT max(SIZE(subFm.fires)) FROM Fireman subFm)")
    Optional<Fireman> getVeteran();
}
