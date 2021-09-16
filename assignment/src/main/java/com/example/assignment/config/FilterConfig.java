package com.example.assignment.config;

import com.example.assignment.error.ExceptionHandlerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Override
    public void configure(HttpSecurity builder) throws Exception {

    }

}
