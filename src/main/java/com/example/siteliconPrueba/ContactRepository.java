package com.example.siteliconPrueba;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    List<ContactEntity> findByNameContainsIgnoreCase(String name);

    // List<ContactEntity> deleteByID(Integer ContactID);

}
