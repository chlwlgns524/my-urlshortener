package com.myshortener.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.myshortener.controller.dto.MemberDto;
import com.myshortener.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class MemberMapperTest {

    static class MemberMapperTestConfig {

        @Bean
        public MemberMapper memberMapper() {
            return new MemberMapper();
        }

    }

    ApplicationContext applicationContext;
    MemberMapper memberMapper;

    @BeforeEach
    void initApplicationContext() {
        applicationContext = new AnnotationConfigApplicationContext(MemberMapperTestConfig.class);
        memberMapper = applicationContext.getBean("memberMapper", MemberMapper.class);
    }

    @DisplayName("DI 컨테이너로부터 MemberMapper bean을 주입받았음을 확인한다.")
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
            .id(1L)
            .email("test@gmail.com")
            .name("test")
            .password("!test")
            .build();

        Member member = Member.builder()
            .id(1L)
            .email(manualConversion.getEmail())
            .name(manualConversion.getName())
            .password(manualConversion.getPassword())
            .build();

        // When
        MemberDto memberDtoThroughMapper = memberMapper.toMemberDto(member);

        // Then
        assertThat(memberDtoThroughMapper)
            .usingRecursiveComparison()
            .ignoringFields("password")
            .isEqualTo(manualConversion);
    }

}