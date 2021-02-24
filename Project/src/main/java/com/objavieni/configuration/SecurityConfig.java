package com.objavieni.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        http
                .authorizeRequests()
                .antMatchers("/register","/registerUser", "/login","/loginUser","/css/**").permitAll()
                .antMatchers("/profile").hasRole("USER")
                .anyRequest().authenticated()
//                .and()
//                .formLogin().defaultSuccessUrl("/profile")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("michal")
//                .password("{bcrypt}$2y$12$jJxmEGmb1ZKtRkqjtIFvb.UkVFFkTN3Satt0qTK9YksqwZMI2Pj2q") //michal
//                .roles("USER");
//
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{bcrypt}$2y$12$XoAKvWLW3CIwlO3IfAWSEes1M4W3VZJwoSXb4t91sOhUJiVPz72M6") //admin
//                .roles("ADMIN");
//    }
}