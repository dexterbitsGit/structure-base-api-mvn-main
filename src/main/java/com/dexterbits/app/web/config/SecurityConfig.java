package com.dexterbits.app.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  private final JwtFilter jwtFilter;

  @Autowired
  public SecurityConfig(JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf().disable().cors().and()
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // aca hago que el servicio de seguridad sea sin estado.
        .authorizeHttpRequests().requestMatchers("/auth/**").permitAll()
        .requestMatchers("/purchases/**").hasAnyRole("ADMIN", "CUSTOMER")
        .requestMatchers(HttpMethod.GET, "/products/**").hasAnyRole("ADMIN", "CUSTOMER")
        .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN").requestMatchers("/purchases/all")
        .hasAuthority("especific_role").requestMatchers("/purchases/**").hasRole("ADMIN")
        .anyRequest().authenticated().and().addFilterBefore(jwtFilter,
            UsernamePasswordAuthenticationFilter.class); // añado al contexto de seguridad el filtro de jwt

    return http.build();
  }

  @Bean
  public PasswordEncoder paswwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }

}
