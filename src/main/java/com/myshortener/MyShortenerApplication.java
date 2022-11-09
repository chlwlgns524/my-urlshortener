package com.myshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MyShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShortenerApplication.class, args);
    }

}
