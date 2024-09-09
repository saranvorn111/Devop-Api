package com.example.devopapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

//    @Bean
//    public PasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//        UserDetails account = User.builder()
//                .username("account")
//                .password(encoder.encode("123"))
//                .roles("ACCOUNT")
//                .build();
//
//        userDetailsManager.createUser(admin);
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(account);
//        return userDetailsManager;
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        return daoAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(token -> token.disable());

        http.httpBasic();

//        http.authorizeHttpRequests(request -> {
//
//            request
//                    .requestMatchers("/api/v1/users/**")
//                    .hasRole("ADMIN");
//            request.requestMatchers("/api/v1/account-types/**", "/api/v1/files/**")
//                    .hasAnyRole("USER", "ACCOUNT");
//            request.anyRequest().permitAll();
//        });
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/v1/auths/**").permitAll();
            auth.requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyRole("ADMIN", "SYSTEM");
            auth.requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasRole("SYSTEM");
            auth.anyRequest().authenticated();
        });

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //male security stateless
        );
        return http.build();

    }
}