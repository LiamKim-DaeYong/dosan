package com.samin.dosan.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("system")
                .password(passwordEncoder().encode("1111"))
                .roles("SYSTEM")
                .and()

                .withUser("admin")
                .password(passwordEncoder().encode("1111"))
                .roles("ADMIN")
                .and()

                .withUser("manager")
                .password(passwordEncoder().encode("1111"))
                .roles("MANAGER")
                .and()

                .withUser("user")
                .password(passwordEncoder().encode("1111"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/error/**").permitAll()
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()

                .logout()
                .permitAll()
                .and()

                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
