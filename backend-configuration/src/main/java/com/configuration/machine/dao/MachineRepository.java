package com.configuration.machine.dao;


import com.configuration.machine.models.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MachineRepository extends JpaRepository<Machine, Long> {

    @Query(value = "SELECT * FROM Machines m WHERE m.owner_id = :id", nativeQuery = true)
    List<Machine> getAllOwnerMachinesByOwnerId(@Param("id") Long id);

}
