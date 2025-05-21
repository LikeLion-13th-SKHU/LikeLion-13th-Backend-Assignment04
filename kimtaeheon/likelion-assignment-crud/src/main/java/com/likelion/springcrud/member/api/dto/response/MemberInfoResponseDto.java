package com.likelion.springcrud.member.api.dto.response;

import com.likelion.springcrud.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberInfoResponseDto(
        String name,
        int age
) {
    public static MemberInfoResponseDto from(Member member) {
        return MemberInfoResponseDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .build();
    }
}
