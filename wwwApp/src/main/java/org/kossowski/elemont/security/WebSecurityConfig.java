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
                .antMatchers("/rest/**").permitAll()
                .antMatchers("/faces/serwis/index.xhtml").permitAll()
                .antMatchers("/faces/magazyn/*").hasRole("MAGAZYN")
                .antMatchers("/faces/budowa/*").hasRole("PROJEKT")
                .antMatchers("/faces/admin/*").hasRole("ADMIN")
                .antMatchers("/faces/serwis/*").hasRole("SERWIS")
                .antMatchers("/faces/sandbox/*").hasRole("SERWIS")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/loginjk.xhtml").failureUrl("/loginjk.xhtml?error")
                
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/loginjk.xhtml?logout")
                .permitAll()
                .and()
            .exceptionHandling().accessDeniedPage("/403.xhtml");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select login,password, aktywny from users where login=?")
                .authoritiesByUsernameQuery(
                        "select login, role from roles where login=?" )
            ;
            //.inMemoryAuthentication()
            //    .withUser("user").password("password").roles("USER","budowa");
    }
}