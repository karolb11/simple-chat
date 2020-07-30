package com.example.websockets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.cors();
    }

    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user1")
                .password("user1")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("user2")
                .password("user2")
                .roles("USER", "ADMIN")
                .build();
        UserDetails user3 = users
                .username("user3")
                .password("user3")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin, user3);
    }
}
