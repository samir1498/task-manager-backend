package com.samir.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
public class SessionRepoConfig {

    @Bean
    public HttpSessionSecurityContextRepository sessionRep(){
        return new HttpSessionSecurityContextRepository();
    }}
