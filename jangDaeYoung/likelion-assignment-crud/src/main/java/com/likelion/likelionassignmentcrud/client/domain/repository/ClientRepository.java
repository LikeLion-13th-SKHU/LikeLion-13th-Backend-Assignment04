package com.likelion.likelionassignmentcrud.client.domain.repository;

import com.likelion.likelionassignmentcrud.client.domain.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findAll(Pageable pageable);

}
