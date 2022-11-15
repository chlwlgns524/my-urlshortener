package com.myshortener.config;

import com.myshortener.mapper.MemberMapper;
import com.myshortener.repository.JdbcMemberRepository;
import com.myshortener.repository.MemberRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public MemberMapper memberMapper() {
        return new MemberMapper();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository();
    }

}
