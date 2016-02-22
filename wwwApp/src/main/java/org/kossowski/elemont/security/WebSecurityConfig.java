/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.security;

/**
 *
 * @author jkossow
 */
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    @Autowired
    protected DataSource dataSource;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        
        http
            
                
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/faces/budowa/*").hasRole("budowa")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/loginjk.xhtml")
                .permitAll()
                .and()
            .logout()
                
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(dataSource);
                
            //.inMemoryAuthentication()
            //    .withUser("user").password("password").roles("USER","budowa");
    }
}