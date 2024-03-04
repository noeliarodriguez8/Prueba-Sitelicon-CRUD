package com.example.siteliconPrueba;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByNameContainsIgnoreCase(String name);

    List<UserEntity> findByLastNameContainsIgnoreCase(String lastName);

    List<UserEntity> findByNameContainsOrLastNameContainsAllIgnoreCase(String name,
            String lastName);

    // List<UserEntity> deleteByID(Integer userID);

    @Query("SELECT c FROM UserEntity c WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%')) AND UPPER(c.lastName) LIKE UPPER(CONCAT('%', :lastName, '%'))")
    List<UserEntity> findByFullName(String name, String lastName);
}
