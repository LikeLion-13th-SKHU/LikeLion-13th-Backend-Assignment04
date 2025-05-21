package com.likelion.springcrud.review.domain;

import com.likelion.springcrud.member.domain.Member;
import com.likelion.springcrud.review.api.dto.request.ReviewUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private int rating;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Review(int rating, String content, Member member) {
        this.rating = rating;
        this.content = content;
        this.member = member;
    }

    // 변경감지를 이용한 업데이트
    public void update(ReviewUpdateRequestDto reviewUpdateRequestDto) {
        this.rating = reviewUpdateRequestDto.rating();
        this.content = reviewUpdateRequestDto.content();
    }
}
