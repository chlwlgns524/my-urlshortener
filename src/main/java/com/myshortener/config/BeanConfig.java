package com.myshortener.config;

import com.myshortener.mapper.MemberMapper;
import com.myshortener.repository.JdbcMemberRepository;
import com.myshortener.repository.MemberRepository;
import com.myshortener.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    
    // DriverManagerDataSource bean 정의

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository();
    }

    @Bean
    public MemberMapper memberMapper() {
        return new MemberMapper();
    }

    @Bean
    public MemberService memberService(
        MemberRepository memberRepository,
        MemberMapper memberMapper
    ) {
        return new MemberService(memberRepository, memberMapper);
    }

}
