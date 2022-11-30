package com.myshortener.service;

import static com.myshortener.repository.MemberRepositoryTest.getMemberInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.myshortener.controller.dto.MemberDto;
import com.myshortener.entity.Member;
import com.myshortener.mapper.MemberMapper;
import com.myshortener.repository.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberRepository mockedMemberRepository;
    MemberMapper mockedMemberMapper;
    MemberService memberService;

    @BeforeEach
    void mockMemberService() {
        mockedMemberRepository = mock(MemberRepository.class);
        mockedMemberMapper = mock(MemberMapper.class);

        memberService = new MemberService(mockedMemberRepository, mockedMemberMapper);

        Member memberInstance = getMemberInstance();
        when(mockedMemberRepository.findByEmail(memberInstance.getEmail())).thenReturn(Optional.of(memberInstance));
        when(mockedMemberMapper.toMemberDto(memberInstance)).thenReturn(
            MemberDto.builder()
                .id(1L)
                .email(memberInstance.getEmail())
                .name(memberInstance.getName())
                .password(memberInstance.getPassword())
                .build());
    }

    @DisplayName("mocked dependency를 이용하여 MemberService bean이 생성되었음을 확인한다.")
    @Test
    void checkMemberService() {
        assertThat(mockedMemberMapper).isNotNull();
        assertThat(mockedMemberRepository).isNotNull();
        assertThat(memberService).isNotNull();
    }

    @DisplayName("이메일을 통해 저장된 회원을 조회한다.")
    @Test
    void testFindMemberByEmail() {
        // Given
        Member memberInstance = getMemberInstance();

        // When
        MemberDto foundMember = memberService.findMemberByEmail(memberInstance.getEmail());

        // Then
        assertThat(foundMember.getEmail()).isEqualTo(memberInstance.getEmail());
    }

    @DisplayName("정상적인 회원을 인증하고 로그인을 수행한다.")
    @Test
    void testLegalLogin() {
        // Given
        Member memberInstance = getMemberInstance();

        // When
        MemberDto loginMember = memberService.login(memberInstance.getEmail(),
            memberInstance.getPassword());

        // Then
        assertThat(loginMember.getEmail()).isEqualTo(memberInstance.getEmail());
        assertThat(loginMember.getPassword()).isEqualTo(memberInstance.getPassword());
    }

    @DisplayName("존재하지 않는 회원이 로그인을 시도한 경우 예외를 발생시킨다.")
    @Test
    void testNonExistentMemberLogin() {
        // Given
        Member memberInstance = getMemberInstance();
        memberInstance.setEmail("illegal@illegal.org");

        // When, Then
        assertThatThrownBy(() -> memberService.login(memberInstance.getEmail(), memberInstance.getPassword()))
            .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("잘못된 비밀번호로 로그인을 시도한 경우 예외를 발생시킨다.")
    @Test
    void testWrongPasswordLogin() {
        // Given
        Member memberInstance = getMemberInstance();
        memberInstance.setPassword("wrongPassword");

        // When, Then
        assertThatThrownBy(() -> memberService.login(memberInstance.getEmail(), memberInstance.getPassword()))
            .isInstanceOf(RuntimeException.class);
    }

}