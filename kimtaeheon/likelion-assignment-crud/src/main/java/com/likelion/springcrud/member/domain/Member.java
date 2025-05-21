package com.likelion.springcrud.member.domain;

import com.likelion.springcrud.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.springcrud.review.domain.Review;
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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @Builder
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 변경감지를 이용한 업데이트
    public void update(MemberUpdateRequestDto memberUpdateRequestDto) {
        this.name = memberUpdateRequestDto.name();
        this.age = memberUpdateRequestDto.age();
    }
}
