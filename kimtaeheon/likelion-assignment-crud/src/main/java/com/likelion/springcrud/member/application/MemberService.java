package com.likelion.springcrud.member.application;

import com.likelion.springcrud.common.error.ErrorCode;
import com.likelion.springcrud.common.exception.BusinessException;
import com.likelion.springcrud.member.api.dto.request.MemberSaveRequestDto;
import com.likelion.springcrud.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.springcrud.member.api.dto.response.MemberInfoResponseDto;
import com.likelion.springcrud.member.api.dto.response.MemberListResponseDto;
import com.likelion.springcrud.member.domain.Member;
import com.likelion.springcrud.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void memberSave(MemberSaveRequestDto requestDto) {
        Member member = Member.builder()
                .name(requestDto.name())
                .age(requestDto.age())
                .build();
        memberRepository.save(member);
    }

    public MemberInfoResponseDto memberFindOne(Long id) {
        Member member = getMember(id);
        return MemberInfoResponseDto.from(member);
    }

    // 페이지로 조회
    public MemberListResponseDto memberFindAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        List<MemberInfoResponseDto> memberInfoResponseDtoList = members.stream()
                .map(MemberInfoResponseDto::from)
                .toList();
        return MemberListResponseDto.from(memberInfoResponseDtoList);
    }

    // 사용자 정보 수정
    @Transactional
    public void memberUpdate(Long id, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = getMember(id);
        member.update(memberUpdateRequestDto);
    }

    // 사용자 삭제
    @Transactional
    public void memberDelete(Long id) {
        Member member = getMember(id);
        memberRepository.delete(member);
    }

    private Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBER_NOT_FOUND_EXCEPTION.getMessage() + id));
    }
}
