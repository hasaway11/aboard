package com.example.aboard;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;

@EnableMethodSecurity(prePostEnabled=true, securedEnabled=true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity config) throws Exception {
    config.csrf(c->c.disable());
    config.formLogin(c-> c.loginPage("/member/login").loginProcessingUrl("/member/login"));
    config.logout(c-> c.logoutUrl("/member/logout").logoutSuccessUrl("/"));
    return config.build();
  }
}
