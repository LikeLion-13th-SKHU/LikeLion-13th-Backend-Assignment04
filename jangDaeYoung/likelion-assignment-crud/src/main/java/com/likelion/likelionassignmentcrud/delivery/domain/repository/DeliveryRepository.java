package com.likelion.likelionassignmentcrud.delivery.domain.repository;

import com.likelion.likelionassignmentcrud.client.domain.Client;
import com.likelion.likelionassignmentcrud.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByClient(Client client);
}
