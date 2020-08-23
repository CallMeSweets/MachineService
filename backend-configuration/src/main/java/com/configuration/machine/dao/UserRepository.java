package com.configuration.machine.dao;


import com.configuration.machine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM Users u WHERE u.username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

}
