package com.myshortener.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.myshortener.entity.Member;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class MemberRepositoryTest {

    static class MemberRepositoryTestConfig {

        @Bean
        public MemberRepository memberRepository() {
            return new JdbcMemberRepository();
        }

    }

    ApplicationContext applicationContext;
    MemberRepository memberRepository;

    @BeforeEach
    public void initApplicationContext() {
        applicationContext = new AnnotationConfigApplicationContext(MemberRepositoryTestConfig.class);
        memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);
    }

    @AfterEach
    public void clear() {
        memberRepository.deleteAll();
    }

    @DisplayName("DI 컨테이너로부터 MemberRepository bean을 주입받았음을 확인한다.")
    @Test
    void checkInitApplicationContext() {
        assertThat(applicationContext).isNotNull();
        assertThat(memberRepository).isNotNull();
    }

    @DisplayName("새로운 회원을 저장하고 이메일을 통해 확인한다.")
    @Test
    void testSaveAndfindByEmail() {
        // Given
        Member testMember = getMemberInstance();

        // When
        memberRepository.save(testMember);
        Optional<Member> foundMember = memberRepository.findByEmail(testMember.getEmail());

        // Then
        assertThat(foundMember.isPresent()).isTrue();
        assertThat(foundMember.get().getEmail()).isEqualTo(testMember.getEmail());
        assertThat(foundMember.get().getId()).isNotNull();
    }

    public static Member getMemberInstance() {
        return Member.builder()
            .email("test@gmail.com")
            .name("test")
            .password("1234")
            .build();
    }

}