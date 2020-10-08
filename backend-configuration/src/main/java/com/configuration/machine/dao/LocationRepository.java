package com.configuration.machine.dao;


import com.configuration.machine.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM Locations l WHERE l.owner_id = :id", nativeQuery = true)
    List<Location> getAllUserLocationsByUserId(@Param("id") Long id);
}
