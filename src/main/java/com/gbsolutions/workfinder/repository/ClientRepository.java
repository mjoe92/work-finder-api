package com.gbsolutions.workfinder.repository;

import com.gbsolutions.workfinder.model.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {
    List<Client> findAll();
}
