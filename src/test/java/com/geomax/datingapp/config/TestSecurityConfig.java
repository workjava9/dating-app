package com.geomax.datingapp.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.geomax.datingapp.security.JwtAuthFilter;
import org.mockito.Mockito;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestSecurityConfig {
    @Bean @Primary
    public JwtAuthFilter jwtAuthFilter() {
        return Mockito.mock(JwtAuthFilter.class);
    }
}

