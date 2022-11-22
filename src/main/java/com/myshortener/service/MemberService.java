package com.myshortener.service;

import com.myshortener.controller.dto.MemberDto;
import com.myshortener.mapper.MemberMapper;
import com.myshortener.entity.Member;
import com.myshortener.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public void createNewMember(MemberDto memberDto) {
        memberRepository.save(memberMapper.toMember(memberDto));
    }

    public MemberDto findMemberByEmail(String email) {
        return memberMapper.toMemberDto(
            memberRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new));
    }

    public MemberDto login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        if (member.getPassword().equals(password))
            return memberMapper.toMemberDto(member);
        else
            throw new RuntimeException();
    }

}
