package com.klaisapp.bookclub.security;

import com.klaisapp.bookclub.security.handlers.LoginFailureHandler;
import com.klaisapp.bookclub.security.handlers.LoginSuccessHandler;
import com.klaisapp.bookclub.security.handlers.LogoutSuccessHandler;
import com.klaisapp.bookclub.security.handlers.RegistrationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;

    private final LoginSuccessHandler loginSuccessHandler;

    private final LogoutSuccessHandler logoutSuccessHandler;

    private final RegistrationSuccessHandler registrationSuccessHandler;
    @Autowired
    public SecurityConfig(DataSource dataSource,
                          LoginSuccessHandler loginSuccessHandler,
                          LogoutSuccessHandler logoutSuccessHandler,
                          RegistrationSuccessHandler registrationSuccessHandler) {
        this.dataSource = dataSource;
        this.loginSuccessHandler = loginSuccessHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.registrationSuccessHandler = registrationSuccessHandler;
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Query to retrieve a user by username
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, active FROM users WHERE username=?");

        // Query to retrieve the authorities by username
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, a.authority_name " +
                        "FROM users u " +
                        "JOIN user_authorities ua ON u.user_id = ua.user_id " +
                        "JOIN authorities a ON ua.authority_id = a.authority_id " +
                        "WHERE u.username=?");

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/userLogin").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/userLogin")
                        .loginProcessingUrl("/authenticateTheUser")
                        .failureHandler(new LoginFailureHandler())
                        .successHandler(new LoginSuccessHandler())
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(new LogoutSuccessHandler())
                        .permitAll()
                )
                .csrf().disable();

        return http.build();
    }
}