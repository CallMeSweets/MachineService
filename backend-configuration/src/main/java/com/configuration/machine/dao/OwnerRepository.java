package com.configuration.machine.dao;


import com.configuration.machine.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
