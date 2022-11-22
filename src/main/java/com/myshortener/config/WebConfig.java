package com.myshortener.config;

import com.myshortener.filter.TestFilter;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // filter test
    @Bean
    public FilterRegistrationBean<TestFilter> testFilter() {
        FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TestFilter());
        registrationBean.setOrder(1);
        registrationBean.setUrlPatterns(List.of("/pages/signup"));
        return registrationBean;
    }

}
