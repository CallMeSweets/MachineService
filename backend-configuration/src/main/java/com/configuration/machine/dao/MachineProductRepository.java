package com.configuration.machine.dao;

import com.configuration.machine.models.MachineProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineProductRepository extends JpaRepository<MachineProducts, Long> {

    @Modifying
    @Query(value = "DELETE FROM machine_products mp WHERE mp.product_id = :id", nativeQuery = true)
    void deleteProductFromMachineByProductId(@Param("id") Long id);

}
