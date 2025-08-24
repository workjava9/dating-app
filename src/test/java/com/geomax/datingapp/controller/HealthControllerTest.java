package com.geomax.datingapp.controller;

import com.geomax.datingapp.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HealthController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(TestSecurityConfig.class)
class HealthControllerTest {

  @Autowired
  private MockMvc mvc;
  @MockitoBean com.geomax.datingapp.security.JwtService jwtService;
  @MockitoBean com.geomax.datingapp.security.CustomUserDetailsService customUserDetailsService;

  @Test
  void healthIsUp() throws Exception {
    mvc.perform(get("/health")).andExpect(status().isOk());
  }
}
