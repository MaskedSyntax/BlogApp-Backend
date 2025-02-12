package com.maskedsyntax.queriously.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequests -> {

                // authorizeRequests.requestMatchers(HttpMethod.POST, "/api/**")
                //                  .hasRole("ADMIN");
                // authorizeRequests.requestMatchers(HttpMethod.PUT, "/api/**")
                //                  .hasRole("ADMIN");
                // authorizeRequests.requestMatchers(HttpMethod.DELETE, "/api/**")
                //                  .hasRole("ADMIN");
                // authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**")
                //                  .hasAnyRole("ADMIN", "USER");
                authorizeRequests.requestMatchers("/api/auth/**")
                                 .permitAll();
                authorizeRequests
                        .anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
