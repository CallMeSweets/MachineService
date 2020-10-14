package com.configuration.machine.dao;

import com.configuration.machine.models.MachineProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineProductRepository extends JpaRepository<MachineProduct, Long> {

    @Modifying
    @Query(value = "DELETE FROM machine_products mp WHERE mp.product_id = :id", nativeQuery = true)
    void deleteProductFromMachineByProductId(@Param("id") Long id);

    @Query(value = "SELECT * FROM machine_products mp WHERE mp.machine_id = :id", nativeQuery = true)
    List<MachineProduct> getAllUserMachineProductByUserId(@Param("id") Long id);


}
