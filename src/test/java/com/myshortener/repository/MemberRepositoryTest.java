package com.myshortener.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.myshortener.entity.Member;
import com.myshortener.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.JdbcUtils;

@Slf4j
public class MemberRepositoryTest {

    static Connection conn;
    static PreparedStatement pstmt;

    @BeforeAll
    static void createTable() {
        String ddl = "CREATE TABLE members ( "
            + "id bigint not null auto_increment, "
            + "email varchar(50) not null unique, "
            + "name varchar(20) not null, "
            + "password varchar(15) not null, "
            + "primary key (id))";

        try {
            conn = DBConnectionUtils.getConnection();
            pstmt = conn.prepareStatement(ddl);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.info("{}", e.getMessage());
        }
    }

    @AfterAll
    static void closeResources() throws SQLException {
        JdbcUtils.closeStatement(pstmt);
        JdbcUtils.closeConnection(conn);

        log.info("Is connection closed? -> {}", conn.isClosed());
    }

    static class MemberRepositoryTestConfig {

        @Bean
        public MemberRepository memberRepository() {
            return new JdbcMemberRepository();
        }

    }

    ApplicationContext applicationContext;
    MemberRepository memberRepository;

    @BeforeEach
    void initApplicationContext() {
        applicationContext = new AnnotationConfigApplicationContext(MemberRepositoryTestConfig.class);
        memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);
    }

    @AfterEach
    void clear() {
        memberRepository.deleteAll();
    }

    @DisplayName("DI ????????????????????? MemberRepository bean??? ?????????????????? ????????????.")
    @Test
    void checkInitApplicationContext() {
        assertThat(applicationContext).isNotNull();
        assertThat(memberRepository).isNotNull();
    }

    @DisplayName("????????? ????????? ???????????? ???????????? ?????? ????????????.")
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