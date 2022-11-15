package com.myshortener.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.myshortener.config.TestConfig;
import com.myshortener.controller.dto.MemberDto;
import com.myshortener.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class MemberMapperTest {

    ApplicationContext applicationContext;
    MemberMapper memberMapper;

    @DisplayName("IoC 컨테이너로부터 MemberMapper bean을 주입받는다.")
    @BeforeEach
    void initApplicationContext() {
        applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        memberMapper = applicationContext.getBean("memberMapper", MemberMapper.class);
    }

    @Test
    void checkInitApplicationContext() {
        assertThat(applicationContext).isNotNull();
        assertThat(memberMapper).isNotNull();
    }

    @DisplayName("MemberDto를 Member entity로 변환한다.")
    @Test
    void testToMember() {
        // Given
        Member manualConversion = Member.builder()
            .email("test@gmail.com")
            .name("test")
            .password("!test")
            .build();

        MemberDto memberDto = MemberDto.builder()
            .email(manualConversion.getEmail())
            .name(manualConversion.getName())
            .password(manualConversion.getPassword())
            .build();

        // When
        Member memberThroughMapper = memberMapper.toMember(memberDto);

        // Then
        assertThat(memberThroughMapper)
            .usingRecursiveComparison()
            .isEqualTo(manualConversion);
    }

    @DisplayName("Member entity를 MemberDto로 변환한다.")
    @Test
    void testToMemberDto() {
        // Given
        MemberDto manualConversion = MemberDto.builder()
            .email("test@gmail.com")
            .name("test")
            .password("!test")
            .build();

        Member member = Member.builder()
            .email(manualConversion.getEmail())
            .name(manualConversion.getName())
            .password(manualConversion.getPassword())
            .build();

        // When
        MemberDto memberDtoThroughMapper = memberMapper.toMemberDto(member);

        // Then
        assertThat(memberDtoThroughMapper)
            .usingRecursiveComparison()
            .isEqualTo(manualConversion);
    }

}