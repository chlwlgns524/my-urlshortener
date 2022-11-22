package com.myshortener.repository;

import com.myshortener.entity.Member;
import com.myshortener.utils.DBConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

@Slf4j
public class JdbcMemberRepository implements MemberRepository {

    @Override
    public void save(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO members(email, name, password) VALUES(?, ?, ?)");
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.info("Saving new member in DB failed.");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeStatement(pstmt);
            JdbcUtils.closeConnection(conn);
        }
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM members WHERE email = ?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(Member.builder()
                    .id(rs.getLong("id"))
                    .email(rs.getString("email"))
                    .name(rs.getString("name"))
                    .password(rs.getString("password"))
                    .build());
            }
            else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            log.info("Finding member by email failed.");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(pstmt);
            JdbcUtils.closeConnection(conn);
        }
    }

    public void deleteAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("DELETE FROM members");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.info("Deleting members failed.");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.closeStatement(pstmt);
            JdbcUtils.closeConnection(conn);
        }
    }

    private Connection getConnection() {
        return DBConnectionUtils.getConnection();
    }

}
