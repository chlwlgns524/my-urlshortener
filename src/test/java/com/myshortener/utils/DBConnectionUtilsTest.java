package com.myshortener.utils;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class DBConnectionUtilsTest {

    @DisplayName("커넥션 객체를 얻는다.")
    @Test
    void testGettingConnection() {
        Connection conn = DBConnectionUtils.getConnection();
        assertThat(conn).isNotNull();
    }

}