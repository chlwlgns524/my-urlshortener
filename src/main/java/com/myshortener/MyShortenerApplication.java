package com.myshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan(basePackages =
    "com.myshortener.controller"
)
@ComponentScan(basePackages = {
    "com.myshortener.config",
    "com.myshortener.controller",
    "com.myshortener.filter"}
)
@SpringBootApplication
public class MyShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShortenerApplication.class, args);
    }

}
