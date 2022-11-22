package com.myshortener.utils;

import static com.myshortener.utils.DBConnectionConst.PASSWORD;
import static com.myshortener.utils.DBConnectionConst.URL;
import static com.myshortener.utils.DBConnectionConst.USERNAME;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DBConnectionUtils {

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("커넥션을 얻는데 성공하였습니다.");
            log.info("conn: {}, class: {}", conn, conn.getClass());
            return conn;
        } catch (SQLException e) {
            log.info("커넥션을 얻는데 실패하였습니다.");
            throw new RuntimeException(e);
        }
    }

}