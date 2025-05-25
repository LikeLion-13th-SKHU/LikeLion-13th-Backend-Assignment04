package com.likelion.likelionassignmentcrud.client.domain;


import com.likelion.likelionassignmentcrud.client.api.dto.request.ClientUpdateRequestDto;
import com.likelion.likelionassignmentcrud.delivery.domain.Delivery;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Payment payment;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveryList = new ArrayList<>();

    @Builder
    private Client(String name, int age, Payment payment){
        this.name = name;
        this.age = age;
        this.payment = payment;

    }

    public void update(ClientUpdateRequestDto clientUpdateRequestDto){
        this.name = clientUpdateRequestDto.name();
        this.age = clientUpdateRequestDto.age();
    }


}
