package com.klaisapp.bookclub.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

public class CustomJdbcUserDetailsManager extends JdbcUserDetailsManager {

    public CustomJdbcUserDetailsManager(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        List<UserDetails> users = super.loadUsersByUsername(username);

        // Log the loaded users
        System.out.println("Loaded users for username '" + username + "':");
        for (UserDetails user : users) {
            System.out.println(user);
        }

        return users;
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        List<GrantedAuthority> authorities = super.loadUserAuthorities(username);

        // Log the loaded authorities
        System.out.println("Loaded authorities for username '" + username + "':");
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority);
        }

        return authorities;
    }
}
