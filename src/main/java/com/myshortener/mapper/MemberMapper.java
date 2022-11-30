package com.myshortener.mapper;

import com.myshortener.controller.dto.MemberDto;
import com.myshortener.entity.Member;

public class MemberMapper {

    public Member toMember(MemberDto memberDto) {
        return Member.builder()
            .email(memberDto.getEmail())
            .name(memberDto.getName())
            .password(memberDto.getPassword())
            .build();
    }

    public MemberDto toMemberDto(Member member) {
        return MemberDto.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .build();
    }

}
