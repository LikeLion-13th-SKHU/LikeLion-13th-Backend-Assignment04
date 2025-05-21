package com.likelion.springcrud.review.domain.repository;

import com.likelion.springcrud.member.domain.Member;
import com.likelion.springcrud.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMember(Member member);
    Page<Review> findPageByMemberId(Long memberId, Pageable pageable);
}
