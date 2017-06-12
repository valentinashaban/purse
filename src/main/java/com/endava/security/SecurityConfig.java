package com.endava.security;

import com.endava.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by vsaban on 6/12/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select login, password from users where login=?")
                .authoritiesByUsernameQuery(
                        "select login, role from users where login=?");
    }

    @Override
    protected void configure(HttpSecurity http) {
        try {
            http
                    .authorizeRequests()
                    .antMatchers("/login*").anonymous()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    //.loginPage("/login.html")
                    .successForwardUrl("/success")
                    //.failureUrl("/login.html?error=true")
                    .and()
                    .logout().logoutSuccessUrl("/login.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

