package org.monopatin.cuentaservice.config;

import org.monopatin.security.AuthotityConstant;
import org.monopatin.security.jwt.JwtFilter;
import org.monopatin.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()

                .requestMatchers("/viajes/**").hasAuthority(AuthotityConstant._USUARIO)
                
                .requestMatchers(HttpMethod.GET, "/cuenta").hasAuthority(AuthotityConstant._USUARIO)
                .requestMatchers(HttpMethod.POST, "/cuenta").hasAuthority(AuthotityConstant._USUARIO)

                .requestMatchers(HttpMethod.PUT, "/cuenta").hasAuthority(AuthotityConstant._ADMIN)
                .requestMatchers(HttpMethod.PATCH, "/cuenta").hasAuthority(AuthotityConstant._ADMIN)

                .requestMatchers(HttpMethod.GET, "/tarifa").hasAuthority(AuthotityConstant._USUARIO)
                .requestMatchers(HttpMethod.POST, "/tarifa").hasAuthority(AuthotityConstant._ADMIN)

                .requestMatchers("/reportes/**").hasAuthority(AuthotityConstant._MANTENIMIENTO)

                .requestMatchers("/api/estudiantes/**").hasAuthority(AuthotityConstant._USUARIO)
                .requestMatchers("/api/inscripciones/**").hasAuthority(AuthotityConstant._MANTENIMIENTO)

                .requestMatchers("/admin/**").hasAuthority(AuthotityConstant._ADMIN)
                
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtFilter(this.tokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
