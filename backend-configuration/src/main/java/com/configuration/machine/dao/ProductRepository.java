package com.configuration.machine.dao;

import com.configuration.machine.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM Products p WHERE p.owner_id = :id", nativeQuery = true)
    List<Product> getAllOwnerProductsByOwnerId(@Param("id") Long id);

}
